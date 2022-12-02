package ru.practicum.ewm.place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.practicum.ewm.place.model.LocationType;

/**
 * Интерфейс для работы с репозиторием LocationType
 */
@RepositoryRestResource(path = "location_types")
public interface LocationTypeRepository extends JpaRepository<LocationType, Long> {
}
