package ru.practicum.ewm.mapper;

import ru.practicum.ewm.model.Hit;
import ru.practicum.ewm.model.dto.EndPointHitDto;
import ru.practicum.ewm.model.dto.ViewStatsDto;

/**
 * Класс для конвертации Hit в DTO и обратно
 */

public final class HitMapper {

    private HitMapper() {
    }

    public static Hit toHit(EndPointHitDto endPointHitDto) {
        return new Hit(
                null,
                endPointHitDto.getApp(),
                endPointHitDto.getUri(),
                endPointHitDto.getIp(),
                endPointHitDto.getTimestamp()
        );
    }

    public static ViewStatsDto toViewStatsDto(String app, String uri, long hits) {
        return new ViewStatsDto(
                app,
                uri,
                hits
        );
    }
}
