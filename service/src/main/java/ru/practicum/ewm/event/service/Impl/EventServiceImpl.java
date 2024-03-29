package ru.practicum.ewm.event.service.Impl;

import com.querydsl.core.BooleanBuilder;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.client.EventWebClientTest;
import ru.practicum.ewm.category.CategoryRepository;
import ru.practicum.ewm.category.model.Category;
import ru.practicum.ewm.client.dto.EndPointHitDto;
import ru.practicum.ewm.client.dto.ViewStatsDto;
import ru.practicum.ewm.event.enums.EventState;
import ru.practicum.ewm.event.mapper.EventMapper;
import ru.practicum.ewm.event.mapper.LocationMapper;
import ru.practicum.ewm.event.model.Event;
import ru.practicum.ewm.event.model.EventParams;
import ru.practicum.ewm.event.model.Location;
import ru.practicum.ewm.event.model.QEvent;
import ru.practicum.ewm.event.model.dto.*;
import ru.practicum.ewm.event.repository.EventRepository;
import ru.practicum.ewm.event.repository.LocationRepository;
import ru.practicum.ewm.event.service.EventService;
import ru.practicum.ewm.exception.NotCorrectConditionException;
import ru.practicum.ewm.exception.NotFoundException;
import ru.practicum.ewm.place.PlaceRepository;
import ru.practicum.ewm.place.model.Place;
import ru.practicum.ewm.user.UserRepository;
import ru.practicum.ewm.user.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    private final LocationRepository locationRepository;

    private final PlaceRepository placeRepository;

    private final EventWebClientTest eventWebClient;

    private static final Long aONE_HOUR_DELAY = 1L;

    private static final Long aTWO_HOURS_DELAY = 2L;

    private static final String aNAME_SERVICE = "ewmservice";

    private static final String aPATH = "/events/";

    private static final DateTimeFormatter aFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<EventFullDto> getAllEventsByAdmin(EventParams params, Pageable pageable) {
        List<Event> events = eventRepository.findAll(getBooleanExp(params), pageable).toList();
        return EventMapper.toEventFullDtoList(events);
    }


    @Override
    @Transactional
    public EventFullDto updateEventByAdmin(Long eventId, EventDto eventDto) {
        Event storedEvent = getEventById(eventId);
        Event updatedEvent = toEvent(storedEvent.getInitiator().getId(), eventDto);
        return EventMapper.toEventFullDto(updateEvent(storedEvent, updatedEvent));
    }

    @Override
    @Transactional
    public EventFullDto publishEventByAdmin(Long eventId) {
        Event event = getEventById(eventId);
        if (!event.getState().equals(EventState.PENDING)) {
            throw new NotCorrectConditionException(
                    "событие должно быть в состоянии ожидания публикации");
        }
        LocalDateTime timeNow = LocalDateTime.now();
        checkDelay(event.getEventDate(), timeNow, aONE_HOUR_DELAY);
        event.setPublishedOn(timeNow);
        event.setState(EventState.PUBLISHED);
        return EventMapper.toEventFullDto(event);
    }

    @Override
    @Transactional
    public EventFullDto rejectEventByAdmin(Long eventId) {
        Event event = getEventById(eventId);
        if (event.getState().equals(EventState.PUBLISHED)) {
            throw new NotCorrectConditionException(
                    "событие не должно быть опубликовано.");
        }
        event.setState(EventState.CANCELED);
        return EventMapper.toEventFullDto(event);
    }

    @Override
    public List<EventShortDto> getAllEventsByUser(Long userId, Pageable pageable) {
        final List<Event> events = eventRepository.findAll(pageable).toList();
        return EventMapper.toEventShortDtoList(events);
    }

    @Override
    @Transactional
    public EventFullDto updateEventByOwner(Long userId, EventDto eventDto) {
        Event storedEvent = getEventById(eventDto.getEventId());
        Event updatedEvent = toEvent(userId, eventDto);
        checkUserIsOwnerOfEvent(userId, storedEvent);
        if (storedEvent.getState().equals(EventState.PUBLISHED)) {
            throw new NotCorrectConditionException(
                    "изменить можно только отмененные события или события в состоянии ожидания модерации");
        }
        return EventMapper.toEventFullDto(updateEvent(storedEvent, updatedEvent));
    }


    @Override
    @Transactional
    public EventFullDto addEventByUser(Long userId, EventDto eventDto) {
        Event event = toEvent(userId, eventDto);
        checkDelay(event.getEventDate(), event.getCreatedOn(), aTWO_HOURS_DELAY);
        return EventMapper.toEventFullDto(eventRepository.save(event));
    }

    @Override
    public EventFullDto getEventByOwner(Long userId, Long eventId) {
        Event event = getEventById(eventId);
        checkUserIsOwnerOfEvent(userId, event);
        return EventMapper.toEventFullDto(event);
    }

    @Override
    @Transactional
    public EventFullDto cancelEventByOwner(Long userId, Long eventId) {
        Event event = getEventById(eventId);
        checkUserIsOwnerOfEvent(userId, event);
        if (!event.getState().equals(EventState.PENDING)) {
            throw new NotCorrectConditionException(
                    "Отменить можно только событие в состоянии ожидания модерации.");
        }
        event.setState(EventState.CANCELED);
        return EventMapper.toEventFullDto(event);
    }

    @Override
    public List<EventShortDto> getAllEventsPublic(EventParams params, Pageable pageable,
                                                  String requestUri, String remoteAddress) {

        List<Event> events = eventRepository.findAll(getBooleanExp(params), pageable).toList();
        List<String> uris = makeUris(events);
        List<ViewStatsDto> viewStats = getStats(uris);
        events = compliteEvents(events, viewStats);
        hitToStats(requestUri, remoteAddress);
        return EventMapper.toEventShortDtoList(events);
    }

    @Override
    public EventFullDto getEventPublic(Long id, String requestUri, String remoteAddress) {
        Event event = getEventById(id);
        List<String> uris = makeUris(List.of(event));
        List<ViewStatsDto> viewStats = getStats(uris);
        event.setViews(viewStats.size());
        hitToStats(requestUri, remoteAddress);
        return EventMapper.toEventFullDto(event);
    }

    @Override
    public List<EventFullDto> getAllEventsInPlaceByAdmin(Long id, LocalDateTime time, Pageable pageable) {
        Place place = getPlaceById(id);
        if (time == null) {
            time = LocalDateTime.now();
        }
        List<Event> eventsInPlace = eventRepository
                .getEventsInPlace(place.getLat(), place.getLon(), place.getRad(), time, pageable);
        return EventMapper.toEventFullDtoList(eventsInPlace);
    }

    private BooleanBuilder getBooleanExp(EventParams params) {
        QEvent event = QEvent.event;
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (params.getOnlyAvailable() != null && params.getOnlyAvailable()) {
            booleanBuilder.and(event.confirmedRequests.gt(event.participantLimit));
        }

        if (params.getPaid() != null) {
            booleanBuilder.and(event.paid.eq(params.getPaid()));
        }

        if (params.getText() != null) {
            booleanBuilder.and(event.annotation.likeIgnoreCase("%" + params.getText() + "%")
                    .or(event.description.likeIgnoreCase("%" + params.getText() + "%")));
        }

        if (params.getUsers() != null) {
            booleanBuilder.and(event.initiator.id.in(params.getUsers()));
        }

        if (params.getStates() != null && !params.getStates().isEmpty()) {
            booleanBuilder.and(event.state.in(params.getStates()));
        }

        if (params.getCategories() != null) {
            booleanBuilder.and(event.category.id.in(params.getCategories()));
        }

        if (params.getRangeStart() != null) {
            booleanBuilder.and(event.eventDate.goe(params.getRangeStart()));
        }

        if (params.getRangeEnd() != null) {
            booleanBuilder.and(event.eventDate.loe(params.getRangeEnd()));
        }

        return booleanBuilder;
    }

    private List<String> makeUris(List<Event> events) {
        return events.stream().map(event -> aPATH + event.getId()).collect(Collectors.toList());
    }

    private List<ViewStatsDto> getStats(List<String> uris) {
        return eventWebClient.getStatsSync(
                LocalDateTime.now().minusYears(1L).format(aFORMATTER),
                LocalDateTime.now().format(aFORMATTER),
                uris,
                false
        );
    }

    private void hitToStats(String requestUri, String remoteAddress) {
        eventWebClient.postHitSync(new EndPointHitDto(
                null,
                aNAME_SERVICE,
                requestUri,
                remoteAddress,
                LocalDateTime.now()
        ));
    }

    private List<Event> compliteEvents(List<Event> events, List<ViewStatsDto> viewStats) {
        for (Event event : events) {
            String uri = aPATH + event.getId();
            long views = viewStats.stream()
                    .filter(h -> h.getUri().equals(uri))
                    .mapToLong(ViewStatsDto::getHits)
                    .sum();
            event.setViews(views);
        }
        return events;
    }

    private Event toEvent(Long userId, EventDto eventDto) {
        final User initiator = getUserById(userId);
        final Category category = getCategoryById(eventDto.getCategory());
        return EventMapper.toEvent(eventDto, category, initiator,
                eventDto.getLocation() == null ? null : addLocation(eventDto.getLocation()),
                LocalDateTime.now());
    }

    private Event updateEvent(Event storedEvent, Event updatedEvent) {
        Optional.ofNullable(updatedEvent.getAnnotation()).filter(s -> !s.isBlank())
                .ifPresent(storedEvent::setAnnotation);
        Optional.ofNullable(updatedEvent.getCategory())
                .ifPresent(storedEvent::setCategory);
        Optional.ofNullable(updatedEvent.getDescription()).filter(s -> !s.isBlank())
                .ifPresent(storedEvent::setDescription);
        Optional.ofNullable(updatedEvent.getEventDate())
                .ifPresent(storedEvent::setEventDate);
        final LocalDateTime timeNow = LocalDateTime.now();
        checkDelay(storedEvent.getEventDate(), timeNow, aTWO_HOURS_DELAY);
        Optional.ofNullable(updatedEvent.getLocation())
                .ifPresent(storedEvent::setLocation);
        Optional.ofNullable(updatedEvent.getPaid()).ifPresent(storedEvent::setPaid);
        Optional.ofNullable(updatedEvent.getParticipantLimit())
                .ifPresent(storedEvent::setParticipantLimit);
        if (storedEvent.getState().equals(EventState.CANCELED)) {
            storedEvent.setState(EventState.PENDING);
        }
        Optional.ofNullable(updatedEvent.getTitle()).filter(s -> !s.isBlank())
                .ifPresent(storedEvent::setTitle);
        return storedEvent;
    }

    private void checkUserIsOwnerOfEvent(Long userId, Event event) {
        if (!userId.equals(event.getInitiator().getId())) {
            throw new NotCorrectConditionException(
                    String.format("user with id=%d isn't owner of event with id=%d", userId, event.getId()));
        }
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("User with id=%d was not found", userId)));
    }

    private Category getCategoryById(Long catId) {
        return categoryRepository.findById(catId)
                .orElseThrow(() -> new NotFoundException(String.format("Category with id=%d was not found", catId)));
    }

    private Location addLocation(LocationDto locationDto) {
        return locationRepository.save(LocationMapper.toLocation(locationDto));
    }

    private Event getEventById(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException(String.format("Event with id=%d was not found", eventId)));
    }

    private void checkDelay(LocalDateTime eventTime, LocalDateTime timeNow, Long delay) {

        if (eventTime.isBefore(timeNow.plusHours(delay))) {
            throw new NotCorrectConditionException(
                    String.format("дата и время на которые намечено событие не может быть раньше, " +
                            "чем через %d часа от текущего момента", delay));
        }
    }

    private Place getPlaceById(Long placeId) {
        return placeRepository.findById(placeId)
                .orElseThrow(() -> new NotFoundException(String.format("Place with id=%d was not found", placeId)));
    }
}
