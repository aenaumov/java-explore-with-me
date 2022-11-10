package ru.practicum.ewm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.practicum.ewm.model.Hit;


/**
 * Интерфейс для работы с репозиторием Statistic
 */
@RepositoryRestResource(path = "hits")
public interface StatisticsRepository extends JpaRepository<Hit, Long>,
        QuerydslPredicateExecutor<Hit> {
}
