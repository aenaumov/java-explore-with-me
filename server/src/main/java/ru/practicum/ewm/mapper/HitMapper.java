package ru.practicum.ewm.mapper;

import ru.practicum.ewm.model.Hit;
import ru.practicum.ewm.model.dto.EndPointHitDto;

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
}
