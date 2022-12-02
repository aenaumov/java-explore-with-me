package ru.practicum.ewm.place.mapper;

import ru.practicum.ewm.place.model.LocationType;
import ru.practicum.ewm.place.model.Place;
import ru.practicum.ewm.place.model.dto.PlaceDto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для конвертации Place в DTO и обратно
 */
public final class PlaceMapper {

    private PlaceMapper() {
    }

    public static Place toPlace(PlaceDto placeDto, LocationType locationType) {
        return new Place(
                null,
                locationType,
                placeDto.getName(),
                placeDto.getLat(),
                placeDto.getLon(),
                placeDto.getRad()
        );
    }

    public static PlaceDto toDto(Place place) {
        return new PlaceDto(
                place.getId(),
                place.getLocationType(),
                place.getName(),
                place.getLat(),
                place.getLon(),
                place.getRad());
    }

    public static List<PlaceDto> toPlaceDtoList(List<Place> places) {
        return places.stream()
                .map(PlaceMapper::toDto)
                .collect(Collectors.toList());
    }
}
