package ru.practicum.ewm.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.practicum.ewm.event.model.Event;

/**
 * Интерфейс для работы с репозиторием Event
 */
@RepositoryRestResource(path = "events")
public interface EventRepository extends JpaRepository<Event, Long>,
        QuerydslPredicateExecutor<Event> {
}
