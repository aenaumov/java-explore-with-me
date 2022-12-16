package ru.practicum.ewm.event.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.common.Create;
import ru.practicum.ewm.common.EwmPageRequest;
import ru.practicum.ewm.common.Patch;
import ru.practicum.ewm.event.service.EventService;
import ru.practicum.ewm.event.model.dto.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * Controller end-point "/users/{userId}/events"
 */
@Slf4j
@RestController
@RequestMapping(path = "/users/{userId}/events")
@AllArgsConstructor
public class EventPrivateController {

    private final EventService eventService;

    /**
     * <p>Получение всех событий пользователем создавшим эти события</p>
     *
     * @param userId {@code Long} id пользователя
     * @param from   {@code int} с какой позиции возвращать элементы
     * @param size   {@code int} сколько элементов возвращать
     * @return список {@code List<EventShortDto>} {@link ru.practicum.ewm.event.model.dto.EventFullDto}
     */
    @GetMapping
    public List<EventShortDto> getAllEventsByUser(
            @PathVariable Long userId,
            @PositiveOrZero @RequestParam(required = false, defaultValue = "0") int from,
            @Positive @RequestParam(required = false, defaultValue = "10") int size
    ) {
        log.info("GET all events user{}, from {}, size {}", userId, from, size);
        return eventService.getAllEventsByUser(userId, new EwmPageRequest(from, size, Sort.unsorted()));
    }

    /**
     * <p>Обновление события пользователем создавшим это событие/p>
     *
     * @param userId   {@code Long} id пользователя
     * @param eventDto {@code EventDto} {@link ru.practicum.ewm.event.model.dto.EventDto}
     * @return {@code EventFullDto} {@link ru.practicum.ewm.event.model.dto.EventFullDto}
     */
    @PatchMapping
    public EventFullDto updateEventByOwner(@PathVariable Long userId,
                                           @Validated(Patch.class) @RequestBody EventDto eventDto) {
        log.info("PATCH user {} event {}", userId, eventDto);
        return eventService.updateEventByOwner(userId, eventDto);
    }

    /**
     * <p>Создание события авторизованным пользователем</p>
     *
     * @param userId   {@code Long} id пользователя
     * @param eventDto {@code EventDto} {@link ru.practicum.ewm.event.model.dto.EventDto}
     * @return {@code EventFullDto} {@link ru.practicum.ewm.event.model.dto.EventFullDto}
     */
    @PostMapping
    public EventFullDto addEventByUser(@PathVariable Long userId,
                                       @Validated(Create.class) @RequestBody EventDto eventDto) {
        log.info("POST user {} event {}", userId, eventDto);
        return eventService.addEventByUser(userId, eventDto);
    }

    /**
     * <p>Получение события пользователем создавшим это событие</p>
     *
     * @param userId  {@code Long} id пользователя
     * @param eventId {@code Long} id события
     * @return {@code EventFullDto} {@link ru.practicum.ewm.event.model.dto.EventFullDto}
     */
    @GetMapping("/{eventId}")
    public EventFullDto getEventByOwner(@PathVariable Long userId,
                                        @PathVariable Long eventId) {
        log.info("GET user {} event {}", userId, eventId);
        return eventService.getEventByOwner(userId, eventId);
    }

    /**
     * Отмена события владельцем
     *
     * @param userId  {@code Long} id пользователя
     * @param eventId {@code Long} id события
     * @return {@link ru.practicum.ewm.event.model.dto.EventFullDto}
     */
    @PatchMapping("/{eventId}")
    public EventFullDto cancelEventByOwner(@PathVariable Long userId,
                                           @PathVariable Long eventId) {
        log.info("PATCH user {} event {}", userId, eventId);
        return eventService.cancelEventByOwner(userId, eventId);
    }
}
