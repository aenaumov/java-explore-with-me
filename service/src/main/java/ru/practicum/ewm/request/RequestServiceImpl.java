package ru.practicum.ewm.request;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.event.repository.EventRepository;
import ru.practicum.ewm.event.enums.EventState;
import ru.practicum.ewm.event.model.Event;
import ru.practicum.ewm.exception.NotCorrectConditionException;
import ru.practicum.ewm.exception.NotFoundException;
import ru.practicum.ewm.request.model.Request;
import ru.practicum.ewm.request.model.dto.ParticipationRequestDto;
import ru.practicum.ewm.user.UserRepository;
import ru.practicum.ewm.user.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    public RequestServiceImpl(
            RequestRepository requestRepository,
            UserRepository userRepository,
            EventRepository eventRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public List<ParticipationRequestDto> getUserRequests(Long userId) {
        User requester = getUserById(userId);
        List<Request> requests = requestRepository.findRequestsByRequesterIs(requester);
        return RequestMapper.toParticipationRequestDtoList(requests);
    }

    @Override
    @Transactional
    public ParticipationRequestDto postUserRequest(Long userId, Long eventId) {
        LocalDateTime creationTime = LocalDateTime.now();
        User requester = getUserById(userId);
        Event event = getEventById(eventId);
        if (userId.equals(event.getInitiator().getId())) {
            throw new NotCorrectConditionException(
                    "инициатор события не может добавить запрос на участие в своём событии");
        }
        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new NotCorrectConditionException(
                    "нельзя участвовать в неопубликованном событии");
        }
        checkLimitOfRequests(event);
        Request request = new Request(
                null,
                creationTime,
                event,
                requester,
                event.getRequestModeration() ? RequestStatus.PENDING : RequestStatus.CONFIRMED
        );
        if (request.getStatus().equals(RequestStatus.CONFIRMED)) {
            event.setConfirmedRequests(event.getConfirmedRequests() + 1);
        }
        return RequestMapper.toParticipationRequestDto(requestRepository.save(request));
    }

    @Override
    @Transactional
    public ParticipationRequestDto cancelUserRequest(Long userId, Long requestId) {
        User requester = getUserById(userId);
        Request request = getRequestById(requestId);
        if (!request.getRequester().getId().equals(requester.getId())) {
            throw new NotCorrectConditionException(
                    "запрос не принадлежит юзеру");
        }
        request.setStatus(RequestStatus.CANCELED);
        Event event = getEventById(request.getEvent().getId());
        event.setConfirmedRequests(event.getConfirmedRequests() - 1);
        return RequestMapper.toParticipationRequestDto(request);
    }

    @Override
    public List<ParticipationRequestDto> getAllRequestsInEventByOwner(Long userId, Long eventId) {
        User initiator = getUserById(userId);
        Event event = getEventById(eventId);
        checkUserIsOwnerOfEvent(initiator, event);
        return RequestMapper.toParticipationRequestDtoList(requestRepository.findRequestByEventIs(event));
    }

    @Override
    @Transactional
    public ParticipationRequestDto approveParticipationInEventByOwner(Long userId, Long eventId, Long reqId) {
        User initiator = getUserById(userId);
        Event event = getEventById(eventId);
        Request request = getRequestById(reqId);
        checkUserIsOwnerOfEvent(initiator, event);
        checkRequestForEvent(event, request);
        if (event.getParticipantLimit() == 0 || !event.getRequestModeration()) {
            return RequestMapper.toParticipationRequestDto(request);
        }
        checkLimitOfRequests(event);
        request.setStatus(RequestStatus.CONFIRMED);
        event.setConfirmedRequests(event.getConfirmedRequests() + 1);
        requestRepository.flush();
        try {
            checkLimitOfRequests(event);
        } catch (NotCorrectConditionException e) {
            cancelRestRequests(event);
        }
        return RequestMapper.toParticipationRequestDto(request);
    }

    @Override
    @Transactional
    public ParticipationRequestDto rejectParticipationInEventByOwner(Long userId, Long eventId, Long reqId) {
        User initiator = getUserById(userId);
        Event event = getEventById(eventId);
        Request request = getRequestById(reqId);
        checkUserIsOwnerOfEvent(initiator, event);
        request.setStatus(RequestStatus.REJECTED);
        return RequestMapper.toParticipationRequestDto(request);
    }

    private void cancelRestRequests(Event event) {
        requestRepository.findRequestsByEventAndStatusIs(event, RequestStatus.PENDING)
                .stream()
                .peek(request -> request.setStatus(RequestStatus.CANCELED))
                .collect(Collectors.toList());
    }

    private void checkLimitOfRequests(Event event) {
        if (event.getConfirmedRequests() == event.getParticipantLimit()) {
            throw new NotCorrectConditionException(
                    "у события достигнут лимит запросов на участие");
        }
    }

    private void checkRequestForEvent(Event event, Request request) {
        if (!event.getId().equals(request.getEvent().getId())) {
            throw new NotCorrectConditionException(
                    "запрос для другого события");
        }
    }

    private void checkUserIsOwnerOfEvent(User initiator, Event event) {
        if (!initiator.getId().equals(event.getInitiator().getId())) {
            throw new NotCorrectConditionException(
                    "событие не принадлежит юзеру");
        }
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("User with id=%d was not found", userId)));
    }

    private Event getEventById(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException(String.format("Event with id=%d was not found", eventId)));
    }

    private Request getRequestById(Long requestId) {
        return requestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException(String.format("Request with id=%d was not found", requestId)));
    }
}
