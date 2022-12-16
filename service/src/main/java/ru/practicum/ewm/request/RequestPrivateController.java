package ru.practicum.ewm.request;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.request.model.dto.ParticipationRequestDto;

import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * Controller end-point "/users"
 */
@Slf4j
@Validated
@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
public class RequestPrivateController {

    private final RequestService requestService;

    /**
     * <p>Получение пользователем своих запросов на участие в событиях</p>
     *
     * @param userId {@code Long} id пользователя
     * @return список {@code List<ParticipationRequestDto>}
     * {@link ru.practicum.ewm.request.model.dto.ParticipationRequestDto}
     */
    @GetMapping("/{userId}/requests")
    public List<ParticipationRequestDto> getUserRequests(@PathVariable Long userId) {
        log.info("GET all requests of user {}", userId);
        return requestService.getUserRequests(userId);
    }

    /**
     * <p>Создание пользователем запроса на участие в событии</p>
     *
     * @param userId  {@code Long} id пользователя
     * @param eventId {@code Long} id события
     * @return {@code ParticipationRequestDto} {@link ru.practicum.ewm.request.model.dto.ParticipationRequestDto}
     */
    @PostMapping("/{userId}/requests")
    public ParticipationRequestDto postUserRequest(
            @PathVariable Long userId,
            @PositiveOrZero @RequestParam Long eventId) {
        log.info("POST user {} request for event {}", userId, eventId);
        return requestService.postUserRequest(userId, eventId);
    }

    /**
     * <p>Отмена пользователем запроса на участие в событии</p>
     *
     * @param userId    {@code Long}  id пользователя
     * @param requestId {@code Long} id запроса на участие в событии
     * @return {@code ParticipationRequestDto} {@link ru.practicum.ewm.request.model.dto.ParticipationRequestDto}
     */
    @PatchMapping("/{userId}/requests/{requestId}/cancel")
    public ParticipationRequestDto cancelUserRequest(@PathVariable Long userId,
                                                     @PathVariable Long requestId) {
        log.info("PATCH request {} by user {}", requestId, userId);
        return requestService.cancelUserRequest(userId, requestId);
    }

    /**
     * <p>Получение всех запросов на участие в событии пользователем создавшим это событие</p>
     *
     * @param userId  {@code Long} id пользователя
     * @param eventId {@code Long} id события
     * @return список {@code List<ParticipationRequestDto>}
     * {@link ru.practicum.ewm.request.model.dto.ParticipationRequestDto}
     */
    @GetMapping("/{userId}/events/{eventId}/requests")
    public List<ParticipationRequestDto> getAllRequestsInEventByOwner(@PathVariable Long userId,
                                                                      @PathVariable Long eventId) {
        log.info("GET user {} event {}", userId, eventId);
        return requestService.getAllRequestsInEventByOwner(userId, eventId);
    }

    /**
     * <p>Одобрение запроса на участие в событии пользователем создавшим это событие</p>
     *
     * @param userId  {@code Long} id пользователя
     * @param eventId {@code Long} id события
     * @param reqId   {@code Long} id запроса на участие в событии
     * @return {@code ParticipationRequestDto} {@link ru.practicum.ewm.request.model.dto.ParticipationRequestDto}
     */
    @PatchMapping("/{userId}/events/{eventId}/requests/{reqId}/confirm")
    public ParticipationRequestDto approveParticipationInEventByOwner(@PathVariable Long userId,
                                                                      @PathVariable Long eventId,
                                                                      @PathVariable Long reqId) {
        log.info("PATCH user {} event {} request {}", userId, eventId, reqId);
        return requestService.approveParticipationInEventByOwner(userId, eventId, reqId);
    }

    /**
     * <p>Отклонение запроса на участие в событии пользователем создавшим это событие</p>
     *
     * @param userId  {@code Long} id пользователя
     * @param eventId {@code Long} id события
     * @param reqId   {@code Long} id запроса на участие в событии
     * @return {@code ParticipationRequestDto} {@link ru.practicum.ewm.request.model.dto.ParticipationRequestDto}
     */
    @PatchMapping("/{userId}/events/{eventId}/requests/{reqId}/reject")
    public ParticipationRequestDto rejectParticipationInEventByOwner(@PathVariable Long userId,
                                                                     @PathVariable Long eventId,
                                                                     @PathVariable Long reqId) {
        log.info("PATCH user {} event {} request {}", userId, eventId, reqId);
        return requestService.rejectParticipationInEventByOwner(userId, eventId, reqId);
    }
}
