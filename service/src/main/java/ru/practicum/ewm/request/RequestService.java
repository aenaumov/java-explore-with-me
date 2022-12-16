package ru.practicum.ewm.request;

import ru.practicum.ewm.request.model.dto.ParticipationRequestDto;

import java.util.List;

/**
 * Интерфейс для работы с Event
 */
public interface RequestService {
    /**
     * <p>метод для получения всех запросов авторизованного пользователя</p>
     *
     * @param userId {@code Long} id пользователя
     * @return список {@code List<ParticipationRequestDto>}
     * {@link ru.practicum.ewm.request.model.dto.ParticipationRequestDto}
     */
    List<ParticipationRequestDto> getUserRequests(Long userId);

    /**
     * <p>метод для добавления авторизованным пользователем запроса на участие в событии</p>
     *
     * @param userId  {@code Long} id пользователя
     * @param eventId {@code Long} id события
     * @return {@code ParticipationRequestDto}
     * {@link ru.practicum.ewm.request.model.dto.ParticipationRequestDto}
     */
    ParticipationRequestDto postUserRequest(Long userId, Long eventId);

    /**
     * <p>метод для отмены запроса на участие в событии</p>
     *
     * @param userId    {@code Long}  id пользователя
     * @param requestId {@code Long} id запроса на участие в событии
     * @return {@code ParticipationRequestDto}
     * {@link ru.practicum.ewm.request.model.dto.ParticipationRequestDto}
     */
    ParticipationRequestDto cancelUserRequest(Long userId, Long requestId);

    /**
     * <p>метод для получения всех запросов на участие в событии пользователем создавшим событие</p>
     *
     * @param userId  {@code Long} id пользователя
     * @param eventId {@code Long} id события
     * @return список {@code List<ParticipationRequestDto>}
     * {@link ru.practicum.ewm.request.model.dto.ParticipationRequestDto}
     */
    List<ParticipationRequestDto> getAllRequestsInEventByOwner(Long userId, Long eventId);

    /**
     * <p>метод для одобрения заявки на участие в событии пользователем создавшим событие</p>
     *
     * @param userId  {@code Long} id пользователя
     * @param eventId {@code Long} id события
     * @param reqId   {@code Long} id запроса на участие в событии
     * @return {@code ParticipationRequestDto}
     * {@link ru.practicum.ewm.request.model.dto.ParticipationRequestDto}
     */
    ParticipationRequestDto approveParticipationInEventByOwner(Long userId, Long eventId, Long reqId);

    /**
     * <p>метод для отклонения заявки на участие в событии пользователем создавшим событие</p>
     *
     * @param userId  {@code Long} id пользователя
     * @param eventId {@code Long} id события
     * @param reqId   {@code Long} id запроса на участие в событии
     * @return {@code ParticipationRequestDto}
     * {@link ru.practicum.ewm.request.model.dto.ParticipationRequestDto}
     */
    ParticipationRequestDto rejectParticipationInEventByOwner(Long userId, Long eventId, Long reqId);
}

