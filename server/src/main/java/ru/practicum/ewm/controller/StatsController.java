package ru.practicum.ewm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.model.dto.ViewStatsDto;
import ru.practicum.ewm.service.StatisticsService;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller end-point "/stats"
 */
@Slf4j
@Validated
@RestController
@RequestMapping(path = "/stats")
public class StatsController {

    private final StatisticsService statisticsService;

    public StatsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    /**
     * Получение статистики по посещениям
     */
    @GetMapping
    public List<ViewStatsDto> getStats(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
            @NotEmpty @RequestParam List<String> uris,
            @RequestParam(defaultValue = "false") Boolean unique
    ) {
        log.info("GET stats starts {}, end {}, uris {}, unique {}", start, end, uris, unique);
        return statisticsService.getStats(start, end, uris, unique);
    }
}
