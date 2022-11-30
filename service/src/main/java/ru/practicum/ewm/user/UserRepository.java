package ru.practicum.ewm.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.practicum.ewm.user.model.User;

/**
 * Интерфейс для работы с репозиторием User
 */
@RepositoryRestResource(path = "users")
public interface UserRepository extends JpaRepository<User, Long> {
}
