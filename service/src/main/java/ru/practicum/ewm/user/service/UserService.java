package ru.practicum.ewm.user.service;

import ru.practicum.ewm.user.model.dto.UserDto;

import java.util.List;

/**
 * Интерфейс для работы с User
 */
public interface UserService {
    /**
     * Получить всех пользователей
     */
    List<UserDto> getAllUsers(List<Long> ids, int from, int size);

    /**
     * Добавить пользователя
     */
    UserDto addUser(UserDto userDto);

    /**
     * Удалить пользователя
     */
    void deleteUser(Long userId);
}
