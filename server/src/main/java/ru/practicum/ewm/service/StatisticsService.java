package ru.practicum.ewm.service;

import ru.practicum.ewm.model.dto.EndPointHitDto;
import ru.practicum.ewm.model.dto.ViewStatsDto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Интерфейс
 */
public interface StatisticsService {

    /**
     * <p>метод для создания записи в статистике</p>
     *
     * @param endPointHitDto {@link ru.practicum.ewm.model.dto.EndPointHitDto}
     * @author Alexander Naumov
     */
    void hit(EndPointHitDto endPointHitDto);

    /**
     * <p>метод для получения статистики</p>
     *
     * @param uris список uri {@code List<String>} по которым нужна статистика
     * @param start дата {@code LocalDateTime} начала выборки статистики
     * @param end дата {@code LocalDateTime} конца выборки статистики
     * @param unique {@code Boolean} если true - получить уникальную статистику
     * @return список {@link ru.practicum.ewm.model.dto.ViewStatsDto}
     * @author Alexander Naumov
     */
    List<ViewStatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique);
}
