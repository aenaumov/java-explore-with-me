package ru.practicum.ewm.place;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zalando.logbook.Logbook;
import ru.practicum.ewm.common.Create;
import ru.practicum.ewm.common.EwmPageRequest;
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
@AllArgsConstructor
public class PlaceAdminController {

    private final PlaceService placeService;

//    TODO check!!!
    Logbook logbook = Logbook.create();

    /**
     * <p>Создание типа локации админом</p>
     *
     * @param locationTypeDto {@code LocationTypeDto} {@link ru.practicum.ewm.place.model.dto.LocationTypeDto}
     * @return {@code LocationTypeDto} {@link ru.practicum.ewm.place.model.dto.LocationTypeDto}
     */
    @PostMapping("/types")
    public LocationTypeDto addPlaceTypeByAdmin(@Validated(Create.class) @RequestBody LocationTypeDto locationTypeDto) {
        log.info("=== POST admin add type of place: {} ", locationTypeDto);
        return placeService.addPlaceTypeByAdmin(locationTypeDto);
    }

    /**
     * <p>Создание места админом</p>
     *
     * @param placeDto {@code PlaceDto} {@link ru.practicum.ewm.place.model.dto.PlaceDto}
     * @return {@code PlaceDto} {@link ru.practicum.ewm.place.model.dto.PlaceDto}
     */
    @PostMapping
    public PlaceDto addPlaceByAdmin(@Validated(Create.class) @RequestBody PlaceDto placeDto) {
        log.info("=== POST admin add place: {} ", placeDto);
        return placeService.addPlaceByAdmin(placeDto);
    }

    /**
     * <p>Получение всех мест админом</p>
     *
     * @param ids  {@code Long} список id конкретных локаций
     * @param from {@code int} с какого элемента вывод
     * @param size {@code int} количество элементов в выводе
     * @return список {@code List<PlaceDto>} {@link ru.practicum.ewm.place.model.dto.PlaceDto}
     */
    @GetMapping
    public List<PlaceDto> getAllPlacesByAdmin(
            @NotEmpty @RequestParam List<Long> ids,
            @PositiveOrZero @RequestParam(required = false, defaultValue = "0") int from,
            @Positive @RequestParam(required = false, defaultValue = "10") int size
    ) {
        log.info("=== GET all places with ids: {}, from: {}, size: {}", ids, from, size);
        return placeService.getAllPlacesByAdmin(ids, new EwmPageRequest(from, size, Sort.unsorted()));
    }
}
