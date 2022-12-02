package ru.practicum.ewm.place.service;

import ru.practicum.ewm.place.model.dto.LocationTypeDto;
import ru.practicum.ewm.place.model.dto.PlaceDto;

import java.util.List;

public interface PlaceService {

    /**
     * Создать тип локации админом
     */
    LocationTypeDto addPlaceTypeByAdmin(LocationTypeDto locationTypeDto);

    /**
     * Создать место админом
     */
    PlaceDto addPlaceByAdmin(PlaceDto placeDto);

    /**
     * Получить все места админом
     */
    List<PlaceDto> getAllPlacesByAdmin(List<Long> ids, int from, int size);

}
