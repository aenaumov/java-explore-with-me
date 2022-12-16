package ru.practicum.ewm.place.service;

import org.springframework.data.domain.Pageable;
import ru.practicum.ewm.place.model.dto.LocationTypeDto;
import ru.practicum.ewm.place.model.dto.PlaceDto;

import java.util.List;

public interface PlaceService {

    /**
     * <p>метод для создания типа локации админом</p>
     *
     * @param locationTypeDto {@code LocationTypeDto} {@link ru.practicum.ewm.place.model.dto.LocationTypeDto}
     * @return {@code LocationTypeDto} {@link ru.practicum.ewm.place.model.dto.LocationTypeDto}
     */
    LocationTypeDto addPlaceTypeByAdmin(LocationTypeDto locationTypeDto);

    /**
     * <p>метод для создания места админом</p>
     *
     * @param placeDto {@code PlaceDto} {@link ru.practicum.ewm.place.model.dto.PlaceDto}
     * @return {@code PlaceDto} {@link ru.practicum.ewm.place.model.dto.PlaceDto}
     */
    PlaceDto addPlaceByAdmin(PlaceDto placeDto);

    /**
     * <p>метод для получения всех мест админом</p>
     *
     * @param ids список {@code List<Long>} id конкретных локаций
     * @param pageable   {@code Pageable} пагинация
     * @return список {@code List<PlaceDto>} {@link ru.practicum.ewm.place.model.dto.PlaceDto}
     */
    List<PlaceDto> getAllPlacesByAdmin(List<Long> ids, Pageable pageable);
}
