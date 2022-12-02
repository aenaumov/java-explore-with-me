package ru.practicum.ewm.event.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.event.service.EventService;
import ru.practicum.ewm.event.enums.EventState;
import ru.practicum.ewm.event.model.dto.EventFullDto;
import ru.practicum.ewm.event.model.dto.EventDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller end-point "/admin/events"
 */
@Slf4j
@Validated
@RestController
@RequestMapping(path = "/admin/events")
public class EventAdminController {

    private final EventService eventService;

    public EventAdminController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Получение всех событий админом с возможностью фильтрации
     */
    @GetMapping
    public List<EventFullDto> getAllEventsByAdmin(
            @RequestParam(required = false) List<Long> users,
            @RequestParam(required = false) List<EventState> states,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) LocalDateTime rangeStart,
            @RequestParam(required = false) LocalDateTime rangeEnd,
            @PositiveOrZero @RequestParam(required = false, defaultValue = "0") int from,
            @Positive @RequestParam(required = false, defaultValue = "10") int size
    ) {
        log.info("GET all events users{}, state {}, categories {}, rangeStart {}, rangeEnd {}, from {}, size {}",
                users, states, categories, rangeStart, rangeEnd, from, size);
        return eventService.getAllEventsByAdmin(users, states, categories, rangeStart, rangeEnd, from, size);
    }

    /**
     * Обновление события админом
     */
    @PutMapping("/{eventId}")
    public EventFullDto updateEventByAdmin(@PathVariable Long eventId,
                                           @RequestBody EventDto eventDto) {
        log.info("PUT event {} body {}", eventId, eventDto);
        return eventService.updateEventByAdmin(eventId, eventDto);
    }

    /**
     * Публикация события админом
     */
    @PatchMapping("/{eventId}/publish")
    public EventFullDto publishEventByAdmin(@PathVariable Long eventId) {
        log.info("PATCH event {}", eventId);
        return eventService.publishEventByAdmin(eventId);
    }

    /**
     * Отмена события админом
     */
    @PatchMapping("/{eventId}/reject")
    public EventFullDto rejectEventByAdmin(@PathVariable Long eventId) {
        log.info("PATCH event {}", eventId);
        return eventService.rejectEventByAdmin(eventId);
    }

    /**
     * Получение событий по месту админом
     */
    @GetMapping("places")
    public List<EventFullDto> getAllEventsInPlaceByAdmin(
            @NotNull @RequestParam Long placeId,
            @RequestParam(required = false) LocalDateTime time,
            @PositiveOrZero @RequestParam(required = false, defaultValue = "0") int from,
            @Positive @RequestParam(required = false, defaultValue = "10") int size
    ) {
        log.info("GET all events in place id {}, from {}, size {}, time after {}", placeId, from, size, time);
        return eventService.getAllEventsInPlaceByAdmin(placeId, from, size, time);
    }
}
