package ru.practicum.ewm.place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.practicum.ewm.place.model.Place;

/**
 * Интерфейс для работы с репозиторием Place
 */
@RepositoryRestResource(path = "places")
public interface PlaceRepository extends JpaRepository<Place, Long> {

}
