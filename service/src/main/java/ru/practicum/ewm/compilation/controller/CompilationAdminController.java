package ru.practicum.ewm.compilation.controller;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class CompilationAdminController {

    private final CompilationService compilationService;

    /**
     * <p>Создание подборки админом</p>
     *
     * @param newCompilationDto {@code NewCompilationDto}
     *                          {@link ru.practicum.ewm.compilation.model.dto.NewCompilationDto}
     * @return {@code CompilationDto} {@link ru.practicum.ewm.compilation.model.dto.CompilationDto}
     */
    @PostMapping
    public CompilationDto addCompilationByAdmin(@Valid @RequestBody NewCompilationDto newCompilationDto) {
        log.info("POST compilation {}", newCompilationDto);
        return compilationService.addCompilationByAdmin(newCompilationDto);
    }

    /**
     * <p>Удаление подборки админом</p>
     *
     * @param compId {@code Long} id подборки
     */
    @DeleteMapping("/{compId}")
    public void deleteCompilationByAdmin(@PathVariable Long compId) {
        log.info("DELETE compilation {}", compId);
        compilationService.deleteCompilationByAdmin(compId);
    }

    /**
     * <p>Удаление события из подборки админом</p>
     *
     * @param compId  {@code Long} id подборки
     * @param eventId {@code Long} id события
     */
    @DeleteMapping("/{compId}/events/{eventId}")
    public void deleteEventFromCompilationByAdmin(@PathVariable Long compId,
                                                  @PathVariable Long eventId) {
        log.info("DELETE event {} from compilation {}", eventId, compId);
        compilationService.deleteEventFromCompilationByAdmin(compId, eventId);
    }

    /**
     * <p>Добавление события в подборку админом</p>
     *
     * @param compId  {@code Long} id подборки
     * @param eventId {@code Long} id события
     */
    @PatchMapping("/{compId}/events/{eventId}")
    public void addEventToCompilationByAdmin(@PathVariable Long compId,
                                             @PathVariable Long eventId) {
        log.info("PATCH add event {} to compilation {}", eventId, compId);
        compilationService.addEventToCompilationByAdmin(eventId, compId);
    }

    /**
     * <p>Закрепление подборки админом</p>
     *
     * @param compId  {@code Long} id подборки
     */
    @DeleteMapping("/{compId}/pin")
    public void deleteCompilationPinByAdmin(@PathVariable Long compId) {
        log.info("DELETE pin compilation {}", compId);
        compilationService.deleteCompilationPinByAdmin(compId);
    }

    /**
     * <p>Открепление подборки админом</p>
     *
     * @param compId  {@code Long} id подборки
     */
    @PatchMapping("/{compId}/pin")
    public void addCompilationPinByAdmin(@PathVariable Long compId) {
        log.info("PATCH pin compilation {}", compId);
        compilationService.addCompilationPinByAdmin(compId);
    }
}
