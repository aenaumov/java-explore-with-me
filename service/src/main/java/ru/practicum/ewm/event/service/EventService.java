package ru.practicum.ewm.event.service;

import ru.practicum.ewm.event.enums.EventSort;
import ru.practicum.ewm.event.enums.EventState;
import ru.practicum.ewm.event.model.dto.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Интерфейс для работы с Event
 */
public interface EventService {

    /**
     * Получить все события админом с фильтрами
     */
    List<EventFullDto> getAllEventsByAdmin(List<Long> users, List<EventState> states, List<Long> categories,
                                           LocalDateTime rangeStart, LocalDateTime rangeEnd, int from, int size);

    /**
     * Обновление события админом
     */
    EventFullDto updateEventByAdmin(Long eventId, EventDto eventDto);

    /**
     * Опубликовать событие админом
     */
    EventFullDto publishEventByAdmin(Long eventId);

    /**
     * Отменить событие админом
     */
    EventFullDto rejectEventByAdmin(Long eventId);

    /**
     * Получить все события пользователем
     */
    List<EventShortDto> getAllEventsByUser(Long userId, int from, int size);

    /**
     * Обновить событие владельцем
     */
    EventFullDto updateEventByOwner(Long userId, EventDto eventDto);

    /**
     * Добавить событие пользователем
     */
    EventFullDto addEventByUser(Long userId, EventDto eventDto);

    /**
     * Получить событие владельцем
     */
    EventFullDto getEventByOwner(Long userId, Long eventId);

    /**
     * Отменить событие владельцем
     */
    EventFullDto cancelEventByOwner(Long userId, Long eventId);

    /**
     * Получить все события пользователем с фильтрами
     */
    List<EventShortDto> getAllEventsPublic(String text, List<Long> categories, Boolean paid,
                                           LocalDateTime rangeStart, LocalDateTime rangeEnd, Boolean onlyAvailable,
                                           EventSort sort, int from, int size, String requestUri, String remoteAddress);

    /**
     * Получить событие
     */
    EventFullDto getEventPublic(Long id, String requestUri, String remoteAddress);
}

