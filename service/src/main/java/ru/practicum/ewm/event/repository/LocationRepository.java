package ru.practicum.ewm.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.practicum.ewm.event.model.Location;

/**
 * Интерфейс для работы с репозиторием Location
 */
@RepositoryRestResource(path = "locations")
public interface LocationRepository extends JpaRepository<Location, Long> {
}
