package ru.practicum.ewm.event.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.practicum.ewm.event.model.Event;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Интерфейс для работы с репозиторием Event
 */
@RepositoryRestResource(path = "events")
public interface EventRepository extends JpaRepository<Event, Long>,
        QuerydslPredicateExecutor<Event> {

    @Query(value = "SELECT e FROM Event AS e " +
            "WHERE :rad > distance(:lat1, :lon1, e.location.lat, e.location.lon) " +
            "AND e.eventDate > :time")
    List<Event> getEventsInPlace(@Param("lat1") Double lat1,
                                 @Param("lon1") Double lon1,
                                 @Param("rad") Double rad,
                                 @Param("time") LocalDateTime time,
                                 Pageable pageable);
}
