package ru.practicum.ewm.event.mapper;

import ru.practicum.ewm.category.model.Category;
import ru.practicum.ewm.event.enums.EventState;
import ru.practicum.ewm.event.model.Event;
import ru.practicum.ewm.event.model.Location;
import ru.practicum.ewm.event.model.dto.EventFullDto;
import ru.practicum.ewm.event.model.dto.EventShortDto;
import ru.practicum.ewm.event.model.dto.EventDto;
import ru.practicum.ewm.user.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для конвертации Event {@link ru.practicum.ewm.event.model.Event} в DTO и обратно
 */
public final class EventMapper {

    private EventMapper() {
    }

    public static EventFullDto toEventFullDto(Event event) {
        return new EventFullDto(
                event.getId(),
                event.getAnnotation(),
                new EventFullDto.CategoryEventDto(
                        event.getCategory().getId(),
                        event.getCategory().getName()
                ),
                event.getConfirmedRequests(),
                event.getCreatedOn(),
                event.getDescription(),
                event.getEventDate(),
                new EventFullDto.UserEventDto(
                        event.getInitiator().getId(),
                        event.getInitiator().getName()
                ),
                new EventFullDto.LocationEventDto(
                        event.getLocation().getLat(),
                        event.getLocation().getLon()
                ),
                event.getPaid(),
                event.getParticipantLimit(),
                event.getPublishedOn(),
                event.getRequestModeration(),
                event.getState(),
                event.getTitle(),
                event.getViews()
        );
    }

    public static List<EventFullDto> toEventFullDtoList(List<Event> events) {
        return events.stream()
                .map(EventMapper::toEventFullDto)
                .collect(Collectors.toList());
    }

    public static EventShortDto toEventShortDto(Event event) {
        return new EventShortDto(
                event.getId(),
                event.getAnnotation(),
                new EventShortDto.CategoryEventDto(
                        event.getCategory().getId(),
                        event.getCategory().getName()
                ),
                event.getConfirmedRequests(),
                event.getEventDate(),
                new EventShortDto.UserEventDto(
                        event.getInitiator().getId(),
                        event.getInitiator().getName()
                ),
                event.getPaid(),
                event.getTitle(),
                event.getViews()
        );
    }

    public static List<EventShortDto> toEventShortDtoList(List<Event> events) {
        return events.stream()
                .map(EventMapper::toEventShortDto)
                .collect(Collectors.toList());
    }

    public static Event toEvent(
            EventDto eventDto, Category category, User initiator, Location location, LocalDateTime createdOn
    ) {
        return new Event(
                null,
                eventDto.getAnnotation(),
                category,
                0,
                createdOn,
                eventDto.getDescription(),
                eventDto.getEventDate(),
                initiator,
                location,
                eventDto.getPaid() == null ? false : eventDto.getPaid(),
                eventDto.getParticipantLimit() == null ? 0 : eventDto.getParticipantLimit(),
                null,
                eventDto.getRequestModeration() == null ? true : eventDto.getRequestModeration(),
                EventState.PENDING,
                eventDto.getTitle(),
                0
        );
    }
}
