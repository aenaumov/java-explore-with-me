package ru.practicum.ewm.event.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.common.EwmPageRequest;
import ru.practicum.ewm.event.model.EventParams;
import ru.practicum.ewm.event.service.EventService;
import ru.practicum.ewm.event.enums.EventSort;
import ru.practicum.ewm.event.model.dto.EventFullDto;
import ru.practicum.ewm.event.model.dto.EventShortDto;
import ru.practicum.ewm.common.ValueOfEnum;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller end-point "/events"
 */
@Slf4j
@Validated
@RestController
@RequestMapping(path = "/events")
@AllArgsConstructor
public class EventPublicController {

    private final EventService eventService;

    /**
     * <p>Получение всех событий с возможностью фильтрации</p>
     *
     * @param text          {@code String} текст для поиска
     * @param categories    {@code List<Long>} список категорий
     * @param paid          {@code Boolean} платное/бесплатное участие в событии
     * @param rangeStart    {@code LocalDateTime} дата начала отбора
     * @param rangeEnd      {@code LocalDateTime} дата конца отбора
     * @param onlyAvailable {@code Boolean} только доступные
     * @param sort          {@code EventSort} сортировка
     * @param from          {@code int} с какой позиции возвращать элементы
     * @param size          {@code int} сколько элементов возвращать
     * @param request       {@code HttpServletRequest} данные запроса
     * @return список {@code List<EventShortDto>} {@link ru.practicum.ewm.event.model.dto.EventShortDto}
     */
    @GetMapping
    public List<EventShortDto> getAllEventsPublic(
            HttpServletRequest request,
            @RequestParam(required = false) String text,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) Boolean paid,
            @RequestParam(required = false) LocalDateTime rangeStart,
            @RequestParam(required = false) LocalDateTime rangeEnd,
            @RequestParam(required = false, defaultValue = "false") Boolean onlyAvailable,
            @RequestParam(required = false) String sort,
            @PositiveOrZero @RequestParam(required = false, defaultValue = "0") int from,
            @Positive @RequestParam(required = false, defaultValue = "10") int size

    ) {
        log.info("GET all events text {}, categories {}, paid {}, rangeStart {}, rangeEnd {}, " +
                        "onlyAvailable {}, sort {}, from {}, size {}",
                text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);
        log.info("client ip: {}", request.getRemoteAddr());
        log.info("endpoint path: {}", request.getRequestURI());

        Sort eventSort = Sort.unsorted();
        if (sort != null) {
            @ValueOfEnum(enumClass = EventSort.class) EventSort es = EventSort.valueOf(sort);
            eventSort = Sort.by(Sort.Direction.DESC, es.getShortName());
        }

        final EventParams params = EventParams.builder()
                .text(text)
                .categories(categories)
                .paid(paid)
                .rangeStart(rangeStart)
                .rangeEnd(rangeEnd)
                .onlyAvailable(onlyAvailable)
                .build();

        return eventService.getAllEventsPublic(params, new EwmPageRequest(from, size, eventSort),
                request.getRequestURI(), request.getRemoteAddr());
    }

    /**
     * <p>Получение конкретного события</p>
     *
     * @param id      {@code Long} id события
     * @param request {@code HttpServletRequest} данные запроса
     * @return {@code EventFullDto} {@link ru.practicum.ewm.event.model.dto.EventFullDto}
     */
    @GetMapping("/{id}")
    public EventFullDto getEventPublic(@PathVariable Long id, HttpServletRequest request) {
        log.info("GET event {}", id);
        log.info("client ip: {}", request.getRemoteAddr());
        log.info("endpoint path: {}", request.getRequestURI());
        return eventService.getEventPublic(id, request.getRequestURI(), request.getRemoteAddr());
    }
}
