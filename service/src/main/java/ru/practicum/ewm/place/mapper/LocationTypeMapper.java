package ru.practicum.ewm.place.mapper;

import ru.practicum.ewm.place.model.LocationType;
import ru.practicum.ewm.place.model.dto.LocationTypeDto;

/**
 * Класс для конвертации LocationType в DTO и обратно
 */
public final class LocationTypeMapper {

    private LocationTypeMapper() {
    }

    public static LocationTypeDto toDto(LocationType locationType) {
        return new LocationTypeDto(
                locationType.getId(),
                locationType.getName());
    }

    public static LocationType toLocationType(LocationTypeDto locationTypeDto) {
        return new LocationType(
                null,
                locationTypeDto.getName());
    }
}
