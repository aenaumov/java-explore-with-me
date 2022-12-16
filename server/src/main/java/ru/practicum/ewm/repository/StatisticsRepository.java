package ru.practicum.ewm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.practicum.ewm.model.Hit;
import ru.practicum.ewm.model.ViewStats;

import java.time.LocalDateTime;
import java.util.List;


/**
 * Интерфейс для работы с репозиторием Statistic
 */
@RepositoryRestResource(path = "hits")
public interface StatisticsRepository extends JpaRepository<Hit, Long> {

    @Query(value = "SELECT new ru.practicum.ewm.model.ViewStats(hit.app, hit.uri, count(hit.uri)) " +
            "FROM Hit as hit " +
            "WHERE hit.uri IN :uris " +
            "AND hit.timestamp >= :start " +
            "AND hit.timestamp <= :end " +
            "GROUP BY hit.app, hit.uri")
    List<ViewStats> getStats(@Param("uris") List<String> uris,
                             @Param("start") LocalDateTime start,
                             @Param("end") LocalDateTime end);

    @Query(value = "SELECT h.app, h.uri, count(h.uri) " +
            "FROM " +
            "(SELECT DISTINCT (ip, app), app, uri FROM Hits " +
            "WHERE timestamp >= :start AND timestamp <= :end AND uri IN :uris) as h " +
            "GROUP BY h.app, h.uri",
            nativeQuery = true)
    List<Object[]> getStatsDist(@Param("uris") List<String> uris,
                                @Param("start") LocalDateTime start,
                                @Param("end") LocalDateTime end);
}
