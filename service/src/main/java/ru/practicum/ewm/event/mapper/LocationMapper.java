package ru.practicum.ewm.event.mapper;

import ru.practicum.ewm.event.model.Location;
import ru.practicum.ewm.event.model.dto.LocationDto;

/**
 * Класс для конвертации Location {@link ru.practicum.ewm.event.model.Location} в DTO и обратно
 */
public final class LocationMapper {

    private LocationMapper() {
    }

    public static Location toLocation(LocationDto locationDto) {
        return new Location(
                null,
                locationDto.getLat(),
                locationDto.getLon()
        );
    }
}
