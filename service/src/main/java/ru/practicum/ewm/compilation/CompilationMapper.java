package ru.practicum.ewm.compilation;

import ru.practicum.ewm.compilation.model.Compilation;
import ru.practicum.ewm.compilation.model.dto.CompilationDto;
import ru.practicum.ewm.compilation.model.dto.NewCompilationDto;
import ru.practicum.ewm.event.model.Event;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для конвертации Compilation в DTO и обратно
 */
public final class CompilationMapper {

    private CompilationMapper() {
    }

    public static Compilation toCompilation(NewCompilationDto newCompilationDto, List<Event> events) {
        return new Compilation(
                null,
                events,
                newCompilationDto.getPinned() == null ? false : newCompilationDto.getPinned(),
                newCompilationDto.getTitle()
        );
    }

    public static CompilationDto toCompilationDto(Compilation compilation) {
        return new CompilationDto(
                compilation.getId(),
                toEventShortDtoList(compilation.getEvents()),
                compilation.getPinned(),
                compilation.getTitle()
        );
    }

    public static List<CompilationDto> toCompilationDtoList(List<Compilation> compilations) {
        return compilations.stream()
                .map(CompilationMapper::toCompilationDto)
                .collect(Collectors.toList());
    }

    private static List<CompilationDto.EventShortDto> toEventShortDtoList(List<Event> events) {
        return events.stream()
                .map(CompilationMapper::toEventShortDto)
                .collect(Collectors.toList());
    }

    private static CompilationDto.EventShortDto toEventShortDto(Event event) {
        return new CompilationDto.EventShortDto(
                event.getId(),
                event.getAnnotation(),
                new CompilationDto.CategoryEventDto(
                        event.getCategory().getId(),
                        event.getCategory().getName()
                ),
                event.getConfirmedRequests(),
                event.getEventDate(),
                new CompilationDto.UserEventDto(
                        event.getInitiator().getId(),
                        event.getInitiator().getName()
                ),
                event.getPaid(),
                event.getTitle(),
                event.getViews()
        );
    }
}
