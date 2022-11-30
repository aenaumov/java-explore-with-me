package ru.practicum.ewm.compilation.service;

import ru.practicum.ewm.compilation.model.dto.CompilationDto;
import ru.practicum.ewm.compilation.model.dto.NewCompilationDto;

import java.util.List;

public interface CompilationService {
    List<CompilationDto> getAllCompilationsPublic(Boolean pinned, int from, int size);

    CompilationDto getCompilationPublic(Long compId);

    CompilationDto addCompilationByAdmin(NewCompilationDto newCompilationDto);

    void deleteCompilationByAdmin(Long compId);

    void deleteEventFromCompilationByAdmin(Long compId, Long eventId);

    void addEventToCompilationByAdmin(Long eventId, Long compId);

    void deleteCompilationPinByAdmin(Long compId);

    void addCompilationPinByAdmin(Long compId);
}
