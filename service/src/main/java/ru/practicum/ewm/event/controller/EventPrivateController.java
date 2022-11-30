package ru.practicum.ewm.event.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.common.Create;
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
public class EventPrivateController {

    private final EventService eventService;

    public EventPrivateController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Получение всех событий владельцем
     */
    @GetMapping
    public List<EventShortDto> getAllEventsByUser(
            @PathVariable Long userId,
            @PositiveOrZero @RequestParam(required = false, defaultValue = "0") int from,
            @Positive @RequestParam(required = false, defaultValue = "10") int size
    ) {
        log.info("GET all events user{}, from {}, size {}", userId, from, size);
        return eventService.getAllEventsByUser(userId, from, size);
    }

    /**
     * Обновление события владельцем
     */
    @PatchMapping
    public EventFullDto updateEventByOwner(@PathVariable Long userId,
                                           @Validated(Patch.class) @RequestBody EventDto eventDto) {
        log.info("PATCH user {} event {}", userId, eventDto);
        return eventService.updateEventByOwner(userId, eventDto);
    }

    /**
     * Добавление события владельцем
     */
    @PostMapping
    public EventFullDto addEventByUser(@PathVariable Long userId,
                                       @Validated(Create.class) @RequestBody EventDto eventDto) {
        log.info("POST user {} event {}", userId, eventDto);
        return eventService.addEventByUser(userId, eventDto);
    }

    /**
     * Получение события владельцем
     */
    @GetMapping("/{eventId}")
    public EventFullDto getEventByOwner(@PathVariable Long userId,
                                        @PathVariable Long eventId) {
        log.info("GET user {} event {}", userId, eventId);
        return eventService.getEventByOwner(userId, eventId);
    }

    /**
     * Отмена события владельцем
     */
    @PatchMapping("/{eventId}")
    public EventFullDto cancelEventByOwner(@PathVariable Long userId,
                                           @PathVariable Long eventId) {
        log.info("PATCH user {} event {}", userId, eventId);
        return eventService.cancelEventByOwner(userId, eventId);
    }
}
