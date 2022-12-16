package ru.practicum.ewm.compilation.service;

import org.springframework.data.domain.Pageable;
import ru.practicum.ewm.compilation.model.dto.CompilationDto;
import ru.practicum.ewm.compilation.model.dto.NewCompilationDto;

import java.util.List;

/**
 * Интерфейс
 */
public interface CompilationService {
    /**
     * <p>метод для получения подборок не авторизованным пользователем</p>
     *
     * @param pinned {@code Boolean} закреплена (true) или нет (false) подборка на главной странице
     * @param pageable   {@code Pageable} пагинация
     * @return list {@code List<CompilationDto>} of CompilationDto
     * {@link ru.practicum.ewm.compilation.model.dto.CompilationDto}
     */
    List<CompilationDto> getAllCompilationsPublic(Boolean pinned, Pageable pageable);

    /**
     * <p>метод для получения подборки по Id не авторизованым пользователем</p>
     *
     * @param compId {@code Long} id подборки
     * @return {@code CompilationDto} of CompilationDto {@link ru.practicum.ewm.compilation.model.dto.CompilationDto}
     */
    CompilationDto getCompilationPublic(Long compId);

    /**
     * <p>метод для создания подборки админом</p>
     *
     * @param newCompilationDto {@code NewCompilationDto}
     *                          NewCompilationDto {@link ru.practicum.ewm.compilation.model.dto.NewCompilationDto}
     * @return {@code CompilationDto} of CompilationDto {@link ru.practicum.ewm.compilation.model.dto.CompilationDto}
     */
    CompilationDto addCompilationByAdmin(NewCompilationDto newCompilationDto);

    /**
     * <p>метод для удаления подборки админом</p>
     *
     * @param compId {@code Long} id подборки
     */
    void deleteCompilationByAdmin(Long compId);

    /**
     * <p>метод для удаления события из подборки админом</p>
     *
     * @param compId {@code Long} id подборки
     * @param eventId {@code Long} id события
     */
    void deleteEventFromCompilationByAdmin(Long compId, Long eventId);

    /**
     * <p>метод для добавления события в подборку админом</p>
     *
     * @param eventId {@code Long} id события
     * @param compId {@code Long} id подборки
     */
    void addEventToCompilationByAdmin(Long eventId, Long compId);

    /**
     * <p>метод для закрепления подборки на главной странице админом</p>
     *
     * @param compId {@code Long} id подборки
     */
    void deleteCompilationPinByAdmin(Long compId);

    /**
     * <p>метод для снятия закрепления подборки на главной странице админом</p>
     *
     * @param compId {@code Long} id подборки
     */
    void addCompilationPinByAdmin(Long compId);
}
