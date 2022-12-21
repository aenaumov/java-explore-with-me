package ru.practicum.ewm.event.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zalando.logbook.Logbook;
import ru.practicum.ewm.common.EwmPageRequest;
import ru.practicum.ewm.common.ValueOfEnum;
import ru.practicum.ewm.event.model.EventParams;
import ru.practicum.ewm.event.service.EventService;
import ru.practicum.ewm.event.enums.EventState;
import ru.practicum.ewm.event.model.dto.EventFullDto;
import ru.practicum.ewm.event.model.dto.EventDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller end-point "/admin/events"
 */
@Slf4j
@Validated
@RestController
@RequestMapping(path = "/admin/events")
@AllArgsConstructor
public class EventAdminController {

    Logbook logbook;

    private final EventService eventService;

    /**
     * <p>Получение всех событий админом с возможностью фильтрации</p>
     *
     * @param users      {@code List<Long>} список id пользователей
     * @param states     {@code List<EventState>} список состояний событий
     * @param categories {@code List<Long>} список категорий
     * @param rangeStart {@code LocalDateTime} дата начала отбора
     * @param rangeEnd   {@code LocalDateTime} дата конца отбора
     * @param from       {@code int} с какой позиции возвращать элементы
     * @param size       {@code int} сколько элементов возвращать
     * @return список {@code List<EventFullDto>} {@link ru.practicum.ewm.event.model.dto.EventFullDto}
     */
    @GetMapping
    public List<EventFullDto> getAllEventsByAdmin(
            @RequestParam(required = false) List<Long> users,
            @RequestParam(required = false) List<String> states,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) LocalDateTime rangeStart,
            @RequestParam(required = false) LocalDateTime rangeEnd,
            @PositiveOrZero @RequestParam(required = false, defaultValue = "0") int from,
            @Positive @RequestParam(required = false, defaultValue = "10") int size
    ) {
        log.info("=== GET all events users: {}, state: {}, categories: {}," +
                        " rangeStart: {}, rangeEnd: {}, from: {}, size: {}",
                users, states, categories, rangeStart, rangeEnd, from, size);

        @ValueOfEnum(enumClass = EventState.class) List<EventState> es = new ArrayList<>();

        if (states != null) {
            es = states.stream()
                    .map(EventState::valueOf).collect(Collectors.toList());
        }

        final EventParams params = EventParams.builder()
                .users(users)
                .states(es)
                .categories(categories)
                .rangeStart(rangeStart)
                .rangeEnd(rangeEnd)
                .build();

        return eventService.getAllEventsByAdmin(params,
                new EwmPageRequest(from, size, Sort.unsorted()));
    }

    /**
     * <p>Обновление события админом</p>
     *
     * @param eventId  {@code Long} id события
     * @param eventDto {@code EventDto} {@link ru.practicum.ewm.event.model.dto.EventDto}
     * @return {@code EventFullDto} {@link ru.practicum.ewm.event.model.dto.EventFullDto}
     */
    @PutMapping("/{eventId}")
    public EventFullDto updateEventByAdmin(@PathVariable Long eventId,
                                           @RequestBody EventDto eventDto) {
        log.info("=== PUT event: {} body: {}", eventId, eventDto);
        return eventService.updateEventByAdmin(eventId, eventDto);
    }

    /**
     * <p>Публикация события админом</p>
     *
     * @param eventId {@code Long} id события
     * @return {@code EventFullDto} {@link ru.practicum.ewm.event.model.dto.EventFullDto}
     */
    @PatchMapping("/{eventId}/publish")
    public EventFullDto publishEventByAdmin(@PathVariable Long eventId) {
        log.info("=== PATCH event: {}", eventId);
        return eventService.publishEventByAdmin(eventId);
    }

    /**
     * <p>Отклонение события админом</p>
     *
     * @param eventId {@code Long} id события
     * @return {@code EventFullDto} {@link ru.practicum.ewm.event.model.dto.EventFullDto}
     */
    @PatchMapping("/{eventId}/reject")
    public EventFullDto rejectEventByAdmin(@PathVariable Long eventId) {
        log.info("=== PATCH event: {}", eventId);
        return eventService.rejectEventByAdmin(eventId);
    }

    /**
     * <p>Получение событий по месту админом</p>
     *
     * @param placeId {@code Long} id конкретного места
     * @param from    {@code int}  с какой позиции возвращать элементы
     * @param size    {@code int}  сколько элементов возвращать
     * @param time    {@code LocalDateTime}  с какой даты показывать события
     * @return список {@code List<EventFullDto>} {@link ru.practicum.ewm.event.model.dto.EventFullDto}
     */
    @GetMapping("places")
    public List<EventFullDto> getAllEventsInPlaceByAdmin(
            @NotNull @RequestParam Long placeId,
            @RequestParam(required = false) LocalDateTime time,
            @PositiveOrZero @RequestParam(required = false, defaultValue = "0") int from,
            @Positive @RequestParam(required = false, defaultValue = "10") int size) {
        log.info("=== GET all events in place id: {}, from: {}, size: {}, time after: {}", placeId, from, size, time);
        return eventService.getAllEventsInPlaceByAdmin(placeId, time, new EwmPageRequest(from, size, Sort.unsorted()));
    }
}
