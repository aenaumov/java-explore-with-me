package ru.practicum.ewm.mapper;

import ru.practicum.ewm.model.Hit;
import ru.practicum.ewm.model.dto.EndPointHitDto;

/**
 * Утилитный класс для конвертации Dto в Hit {@link ru.practicum.ewm.model.Hit}
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
