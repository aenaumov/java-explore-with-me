package ru.practicum.ewm.compilation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.compilation.service.CompilationService;
import ru.practicum.ewm.compilation.model.dto.CompilationDto;
import ru.practicum.ewm.compilation.model.dto.NewCompilationDto;

import javax.validation.Valid;

/**
 * Controller end-point "/admin/compilations"
 */
@Slf4j
@Validated
@RestController
@RequestMapping(path = "/admin/compilations")
public class CompilationAdminController {

    private final CompilationService compilationService;

    public CompilationAdminController(CompilationService compilationService) {
        this.compilationService = compilationService;
    }

    /**
     * Добавление подборки админом
     */
    @PostMapping
    public CompilationDto addCompilationByAdmin(@Valid @RequestBody NewCompilationDto newCompilationDto) {
        log.info("POST compilation {}", newCompilationDto);
        return compilationService.addCompilationByAdmin(newCompilationDto);
    }

    /**
     * Удаление подборки админом
     */
    @DeleteMapping("/{compId}")
    public void deleteCompilationByAdmin(@PathVariable Long compId) {
        log.info("DELETE compilation {}", compId);
        compilationService.deleteCompilationByAdmin(compId);
    }

    /**
     * Удаление события из подборки админом
     */
    @DeleteMapping("/{compId}/events/{eventId}")
    public void deleteEventFromCompilationByAdmin(@PathVariable Long compId,
                                                  @PathVariable Long eventId) {
        log.info("DELETE event {} from compilation {}", eventId, compId);
        compilationService.deleteEventFromCompilationByAdmin(compId, eventId);
    }

    /**
     * Добавление события в подборку админом
     */
    @PatchMapping("/{compId}/events/{eventId}")
    public void addEventToCompilationByAdmin(@PathVariable Long compId,
                                             @PathVariable Long eventId) {
        log.info("PATCH add event {} to compilation {}", eventId, compId);
        compilationService.addEventToCompilationByAdmin(eventId, compId);
    }

    /**
     * Закрепление подборки админом
     */
    @DeleteMapping("/{compId}/pin")
    public void deleteCompilationPinByAdmin(@PathVariable Long compId) {
        log.info("DELETE pin compilation {}", compId);
        compilationService.deleteCompilationPinByAdmin(compId);
    }

    /**
     * Открепление подборки админом
     */
    @PatchMapping("/{compId}/pin")
    public void addCompilationPinByAdmin(@PathVariable Long compId) {
        log.info("PATCH pin compilation {}", compId);
        compilationService.addCompilationPinByAdmin(compId);
    }
}
