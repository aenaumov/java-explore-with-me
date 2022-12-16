package ru.practicum.ewm.compilation.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.compilation.CompilationMapper;
import ru.practicum.ewm.compilation.CompilationRepository;
import ru.practicum.ewm.compilation.model.Compilation;
import ru.practicum.ewm.compilation.model.dto.CompilationDto;
import ru.practicum.ewm.compilation.model.dto.NewCompilationDto;
import ru.practicum.ewm.compilation.service.CompilationService;
import ru.practicum.ewm.event.repository.EventRepository;
import ru.practicum.ewm.event.model.Event;
import ru.practicum.ewm.exception.NotFoundException;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class CompilationServiceImpl implements CompilationService {

    private final CompilationRepository compilationRepository;

    private final EventRepository eventRepository;

    @Override
    public List<CompilationDto> getAllCompilationsPublic(Boolean pinned, Pageable pageable) {
        List<Compilation> compilations = compilationRepository.findAllByPinnedIs(pinned, pageable);
        return CompilationMapper.toCompilationDtoList(compilations);
    }

    @Override
    public CompilationDto getCompilationPublic(Long compId) {
        Compilation compilation = getCompilationById(compId);
        return CompilationMapper.toCompilationDto(compilation);
    }

    @Override
    @Transactional
    public CompilationDto addCompilationByAdmin(NewCompilationDto newCompilationDto) {
        List<Event> events = eventRepository.findAllById(newCompilationDto.getEvents());
        Compilation compilation = CompilationMapper.toCompilation(newCompilationDto, events);
        return CompilationMapper.toCompilationDto(compilationRepository.save(compilation));
    }

    @Override
    @Transactional
    public void deleteCompilationByAdmin(Long compId) {
        compilationRepository.deleteById(compId);

    }

    @Override
    @Transactional
    public void deleteEventFromCompilationByAdmin(Long compId, Long eventId) {

        Compilation compilation = getCompilationById(compId);
        Event event = getEventById(eventId);
        compilation.getEvents().remove(event);

    }

    @Override
    @Transactional
    public void addEventToCompilationByAdmin(Long eventId, Long compId) {
        Compilation compilation = getCompilationById(compId);
        Event event = getEventById(eventId);
        compilation.getEvents().add(event);
    }

    @Override
    @Transactional
    public void deleteCompilationPinByAdmin(Long compId) {
        Compilation compilation = getCompilationById(compId);
        compilation.setPinned(false);
    }

    @Override
    @Transactional
    public void addCompilationPinByAdmin(Long compId) {
        Compilation compilation = getCompilationById(compId);
        compilation.setPinned(true);
    }

    private Compilation getCompilationById(Long compId) {
        return compilationRepository.findById(compId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Compilation with id=%d was not found", compId)));
    }

    private Event getEventById(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException(String.format("Event with id=%d was not found", eventId)));
    }
}
