package ru.practicum.ewm.compilation;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.practicum.ewm.compilation.model.Compilation;

import java.util.List;

/**
 * Интерфейс для работы с репозиторием Compilation
 */
@RepositoryRestResource(path = "compilation")
public interface CompilationRepository extends JpaRepository<Compilation, Long> {

    List<Compilation> findAllByPinnedIs(Boolean pinned, Pageable pageable);
}
