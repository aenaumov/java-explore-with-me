package ru.practicum.ewm.request;

import ru.practicum.ewm.request.model.dto.ParticipationRequestDto;

import java.util.List;

/**
 * Интерфейс для работы с Event
 */
public interface RequestService {
    /**
     * Получить запросы пользователя
     */
    List<ParticipationRequestDto> getUserRequests(Long userId);

    /**
     * Добавить запрос на участие в событии
     */
    ParticipationRequestDto postUserRequest(Long userId, Long eventId);

    /**
     * Отменить запрос на участие в событии
     */
    ParticipationRequestDto cancelUserRequest(Long userId, Long requestId);

    /**
     * Получить все запросы на участие в событии владельцем
     */
    List<ParticipationRequestDto> getAllRequestsInEventByOwner(Long userId, Long eventId);

    /**
     * Одобрить заявку на участие в событии владельцем
     */
    ParticipationRequestDto approveParticipationInEventByOwner(Long userId, Long eventId, Long reqId);

    /**
     * Отказать в заявке на участие в событии владельцем
     */
    ParticipationRequestDto rejectParticipationInEventByOwner(Long userId, Long eventId, Long reqId);
}

