package ru.practicum.ewm.event.service;

import org.springframework.data.domain.Pageable;
import ru.practicum.ewm.event.model.EventParams;
import ru.practicum.ewm.event.model.dto.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Интерфейс для работы с Event
 */
public interface EventService {

    /**
     * <p>метод для получения всех событий админом с фильтрами</p>
     *
     * @param params   {@code EventParams} параметры для фильтрации {@link ru.practicum.ewm.event.model.EventParams}
     * @param pageable {@code Pageable} пагинация
     * @return список {@link ru.practicum.ewm.event.model.dto.EventFullDto}
     */
    List<EventFullDto> getAllEventsByAdmin(EventParams params, Pageable pageable);

    /**
     * <p>метод для обновления события админом</p>
     *
     * @param eventId  {@code Long} id события
     * @param eventDto {@code EventDto} {@link ru.practicum.ewm.event.model.dto.EventDto}
     * @return {@link ru.practicum.ewm.event.model.dto.EventFullDto}
     */
    EventFullDto updateEventByAdmin(Long eventId, EventDto eventDto);

    /**
     * <p>метод для публикации события админом</p>
     *
     * @param eventId {@code Long} id события
     * @return {@link ru.practicum.ewm.event.model.dto.EventFullDto}
     */
    EventFullDto publishEventByAdmin(Long eventId);

    /**
     * <p>метод для отклонения события админом</p>
     *
     * @param eventId {@code Long} id события
     * @return {@link ru.practicum.ewm.event.model.dto.EventFullDto}
     */
    EventFullDto rejectEventByAdmin(Long eventId);

    /**
     * <p>метод для получения всех событий авторизованным пользователем</p>
     *
     * @param userId {@code Long} id пользователя
     * @param pageable      {@code Pageable} пагинация с сортировкой
     * @return список {@link ru.practicum.ewm.event.model.dto.EventFullDto}
     */
    List<EventShortDto> getAllEventsByUser(Long userId, Pageable pageable);

    /**
     * <p>метод для обновления события пользователем создавшим это событие</p>
     *
     * @param userId   {@code Long} id пользователя
     * @param eventDto {@code EventDto} {@link ru.practicum.ewm.event.model.dto.EventDto}
     * @return {@link ru.practicum.ewm.event.model.dto.EventFullDto}
     */
    EventFullDto updateEventByOwner(Long userId, EventDto eventDto);

    /**
     * <p>метод для создания события авторизованным пользователем</p>
     *
     * @param userId   {@code Long} id пользователя
     * @param eventDto {@code EventDto} {@link ru.practicum.ewm.event.model.dto.EventDto}
     * @return {@link ru.practicum.ewm.event.model.dto.EventFullDto}
     */
    EventFullDto addEventByUser(Long userId, EventDto eventDto);

    /**
     * <p>метод для получения события пользователем создавшим это событие</p>
     *
     * @param userId  {@code Long} id пользователя
     * @param eventId {@code Long} id события
     * @return {@link ru.practicum.ewm.event.model.dto.EventFullDto}
     */
    EventFullDto getEventByOwner(Long userId, Long eventId);

    /**
     * <p>метод для отмены события пользователем создавшим это событие</p>
     *
     * @param userId  {@code Long} id пользователя
     * @param eventId {@code Long} id события
     * @return {@link ru.practicum.ewm.event.model.dto.EventFullDto}
     */
    EventFullDto cancelEventByOwner(Long userId, Long eventId);

    /**
     * <p>метод для получения всех событий не авторизованным пользователем с фильтрами</p>
     *
     * @param params        {@code EventParams} параметры для фильтрации
     *                      {@link ru.practicum.ewm.event.model.EventParams}
     * @param pageable      {@code Pageable} пагинация с сортировкой
     * @param requestUri    {@code String} uri события
     * @param remoteAddress {@code String} ip пользователя
     * @return список {@code List<EventShortDto>} {@link ru.practicum.ewm.event.model.dto.EventShortDto}
     */
    List<EventShortDto> getAllEventsPublic(EventParams params, Pageable pageable,
                                           String requestUri, String remoteAddress);

    /**
     * <p>метод для получения события не авторизованным пользователем</p>
     *
     * @param id            {@code Long} id события
     * @param requestUri    {@code String} uri события
     * @param remoteAddress {@code String} ip пользователя
     * @return {@link ru.practicum.ewm.event.model.dto.EventFullDto}
     */
    EventFullDto getEventPublic(Long id, String requestUri, String remoteAddress);

    /**
     * <p>метод для получения всех событий в конкретном месте админом</p>
     *
     * @param id       {@code Long} id конкретного места
     * @param time     {@code LocalDateTime} с какой даты показывать события
     * @param pageable {@code Pageable} пагинация
     * @return список {@link ru.practicum.ewm.event.model.dto.EventFullDto}
     */
    List<EventFullDto> getAllEventsInPlaceByAdmin(Long id, LocalDateTime time, Pageable pageable);
}

