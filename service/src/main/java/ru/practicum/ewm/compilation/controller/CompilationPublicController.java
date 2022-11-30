package ru.practicum.ewm.compilation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.compilation.service.CompilationService;
import ru.practicum.ewm.compilation.model.dto.CompilationDto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * Controller end-point "/compilations"
 */
@Slf4j
@Validated
@RestController
@RequestMapping(path = "/compilations")
public class CompilationPublicController {

    private final CompilationService compilationService;

    public CompilationPublicController(CompilationService compilationService) {
        this.compilationService = compilationService;
    }

    /**
     * Получение всех подборок
     */
    @GetMapping
    public List<CompilationDto> getAllCompilationsPublic(
            @RequestParam(required = false) Boolean pinned,
            @PositiveOrZero @RequestParam(required = false, defaultValue = "0") int from,
            @Positive @RequestParam(required = false, defaultValue = "10") int size
    ) {
        log.info("GET all compilations pinned {}, from {}, size {}", pinned, from, size);
        return compilationService.getAllCompilationsPublic(pinned, from, size);
    }

    /**
     * Получение конкретной подборки
     */
    @GetMapping("/{compId}")
    public CompilationDto getCompilationPublic(@PathVariable Long compId) {
        log.info("GET compilation {}", compId);
        return compilationService.getCompilationPublic(compId);
    }
}
