package ru.practicum.ewm.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.model.dto.EndPointHitDto;
import ru.practicum.ewm.service.StatisticsService;

import javax.validation.Valid;

/**
 * Controller end-point "/hit"
 */
@Slf4j
@Validated
@RestController
@RequestMapping(path = "/hit")
@AllArgsConstructor
public class HitController {

    private final StatisticsService statisticsService;

    /**
     * <p>Сохранение информации о том, что на uri конкретного сервиса был отправлен запрос пользователем</p>
     *
     * @param endPointHitDto {@code EndPointHitDto} {@link ru.practicum.ewm.model.dto.EndPointHitDto}
     */
    @PostMapping
    public void hit(@Valid @RequestBody EndPointHitDto endPointHitDto) {
        log.info("POST hit {}", endPointHitDto);
        statisticsService.hit(endPointHitDto);
    }
}
