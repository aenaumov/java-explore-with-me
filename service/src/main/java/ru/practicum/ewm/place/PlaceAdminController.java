package ru.practicum.ewm.place;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.common.Create;
import ru.practicum.ewm.place.model.dto.LocationTypeDto;
import ru.practicum.ewm.place.model.dto.PlaceDto;
import ru.practicum.ewm.place.service.PlaceService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * Controller end-point "/admin/places"
 */
@Slf4j
@Validated
@RestController
@RequestMapping(path = "/admin/places")
public class PlaceAdminController {

    private final PlaceService placeService;

    public PlaceAdminController(PlaceService placeService) {
        this.placeService = placeService;
    }

    /**
     * Создание типа локации админом
     */
    @PostMapping("/types")
    public LocationTypeDto addPlaceTypeByAdmin(@Validated(Create.class) @RequestBody LocationTypeDto locationTypeDto) {
        log.info("POST admin add type of place {} ", locationTypeDto);
        return placeService.addPlaceTypeByAdmin(locationTypeDto);
    }

    /**
     * Создание места админом
     */
    @PostMapping
    public PlaceDto addPlaceByAdmin(@Validated(Create.class) @RequestBody PlaceDto placeDto) {
        log.info("POST admin add place {} ", placeDto);
        return placeService.addPlaceByAdmin(placeDto);
    }

    /**
     * Получение всех мест админом
     */
    @GetMapping
    public List<PlaceDto> getAllPlacesByAdmin(
            @NotEmpty @RequestParam List<Long> ids,
            @PositiveOrZero @RequestParam(required = false, defaultValue = "0") int from,
            @Positive @RequestParam(required = false, defaultValue = "10") int size
    ) {
        log.info("GET all places with ids {}, from {}, size {}", ids, from, size);
        return placeService.getAllPlacesByAdmin(ids, from, size);
    }
}
