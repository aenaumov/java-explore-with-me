package ru.practicum.ewm.service;

import ru.practicum.ewm.model.dto.EndPointHitDto;
import ru.practicum.ewm.model.dto.ViewStatsDto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Интерфейс для работы с Statistic
 */
public interface StatisticsService {

    /**
     * Записать статистику
     */
    void hit(EndPointHitDto endPointHitDto);

    /**
     * Получить статистику
     */
    List<ViewStatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique);
}
