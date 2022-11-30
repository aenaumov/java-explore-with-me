package ru.practicum.ewm.request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.practicum.ewm.event.model.Event;
import ru.practicum.ewm.request.model.Request;
import ru.practicum.ewm.user.model.User;

import java.util.List;

/**
 * Интерфейс для работы с репозиторием Request
 */
@RepositoryRestResource(path = "requests")
public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findRequestsByRequesterIs(User requester);

    List<Request> findRequestByEventIs(Event event);

    List<Request> findRequestsByEventAndStatusIs(Event event, RequestStatus status);
}
