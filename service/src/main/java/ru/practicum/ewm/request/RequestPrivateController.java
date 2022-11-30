package ru.practicum.ewm.request;

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

public class RequestPrivateController {

    private final RequestService requestService;

    public RequestPrivateController(RequestService requestService) {
        this.requestService = requestService;
    }

    /**
     * Получение пользователем своих запросов
     */
    @GetMapping("/{userId}/requests")
    public List<ParticipationRequestDto> getUserRequests(@PathVariable Long userId) {
        log.info("GET all requests of user {}", userId);
        return requestService.getUserRequests(userId);
    }

    /**
     * Добавление пользователем запроса
     */
    @PostMapping("/{userId}/requests")
    public ParticipationRequestDto postUserRequest(
            @PathVariable Long userId,
            @PositiveOrZero @RequestParam Long eventId) {
        log.info("POST user {} request for event {}", userId, eventId);
        return requestService.postUserRequest(userId, eventId);
    }

    /**
     * Отмена пользователем запроса
     */
    @PatchMapping("/{userId}/requests/{requestId}/cancel")
    public ParticipationRequestDto cancelUserRequest(@PathVariable Long userId,
                                                     @PathVariable Long requestId) {
        log.info("PATCH request {} by user {}", requestId, userId);
        return requestService.cancelUserRequest(userId, requestId);
    }

    /**
     * Получение всех запросов владельцем события
     */
    @GetMapping("/{userId}/events/{eventId}/requests")
    public List<ParticipationRequestDto> getAllRequestsInEventByOwner(@PathVariable Long userId,
                                                                      @PathVariable Long eventId) {
        log.info("GET user {} event {}", userId, eventId);
        return requestService.getAllRequestsInEventByOwner(userId, eventId);
    }

    /**
     * Одобрение запроса владельцем события
     */
    @PatchMapping("/{userId}/events/{eventId}/requests/{reqId}/confirm")
    public ParticipationRequestDto approveParticipationInEventByOwner(@PathVariable Long userId,
                                                                      @PathVariable Long eventId,
                                                                      @PathVariable Long reqId) {
        log.info("PATCH user {} event {} request {}", userId, eventId, reqId);
        return requestService.approveParticipationInEventByOwner(userId, eventId, reqId);
    }

    /**
     * Отмена запроса владельцем события
     */
    @PatchMapping("/{userId}/events/{eventId}/requests/{reqId}/reject")
    public ParticipationRequestDto rejectParticipationInEventByOwner(@PathVariable Long userId,
                                                                     @PathVariable Long eventId,
                                                                     @PathVariable Long reqId) {
        log.info("PATCH user {} event {} request {}", userId, eventId, reqId);
        return requestService.rejectParticipationInEventByOwner(userId, eventId, reqId);
    }
}
