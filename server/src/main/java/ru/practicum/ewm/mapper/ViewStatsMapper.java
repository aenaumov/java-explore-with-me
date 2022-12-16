package ru.practicum.ewm.mapper;

import ru.practicum.ewm.model.ViewStats;
import ru.practicum.ewm.model.dto.ViewStatsDto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Утилитный класс для конвертации ViewStats {@link ru.practicum.ewm.model.ViewStats} в DTO
 */

public final class ViewStatsMapper {

    private ViewStatsMapper() {
    }

    public static ViewStatsDto toDto(ViewStats viewStats) {
        return new ViewStatsDto(
                viewStats.getApp(),
                viewStats.getUri(),
                viewStats.getHits()
        );
    }

    public static List<ViewStatsDto> toDtoList(List<ViewStats> viewStats) {
        return viewStats.stream()
                .map(ViewStatsMapper::toDto)
                .collect(Collectors.toList());
    }
}
