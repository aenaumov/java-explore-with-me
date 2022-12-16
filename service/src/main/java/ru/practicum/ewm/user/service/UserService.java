package ru.practicum.ewm.user.service;

import org.springframework.data.domain.Pageable;
import ru.practicum.ewm.user.model.dto.UserDto;

import java.util.List;

/**
 * Интерфейс для работы с User
 */
public interface UserService {
    /**
     * <p>метод для получения всех пользователей админом</p>
     *
     * @param ids {@code List<Long>} список id пользователей
     * @param pageable   {@code Pageable} пагинация
     * @return список {@code List<UserDto>} {@link ru.practicum.ewm.user.model.dto.UserDto}
     */
    List<UserDto> getAllUsers(List<Long> ids, Pageable pageable);

    /**
     * <p>метод для создания пользователя админом</p>
     *
     * @param userDto {@code UserDto} {@link ru.practicum.ewm.user.model.dto.UserDto}
     * @return {@code UserDto} {@link ru.practicum.ewm.user.model.dto.UserDto}
     */
    UserDto addUser(UserDto userDto);

    /**
     * <p>метод для удаления пользователя админом</p>
     *
     * @param userId {@code Long} id пользователя
     */
    void deleteUser(Long userId);
}
