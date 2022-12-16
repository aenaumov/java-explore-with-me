package ru.practicum.ewm.user;

import ru.practicum.ewm.user.model.User;
import ru.practicum.ewm.user.model.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Утилитный класс для конвертации User {@link ru.practicum.ewm.user.model.User} в DTO и обратно
 */
public final class UserMapper {

    private UserMapper() {
    }

    public static UserDto toDto(User user) {
        final Long id = user.getId();
        final String name = user.getName();
        final String email = user.getEmail();
        return new UserDto(id, name, email);
    }

    public static User toUser(UserDto userDto) {
        final String name = userDto.getName();
        final String email = userDto.getEmail();
        return new User(null, name, email);
    }

    public static List<UserDto> toUserDtoList(List<User> users) {
        return users.stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }
}
