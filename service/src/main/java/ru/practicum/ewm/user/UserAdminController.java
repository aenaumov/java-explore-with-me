package ru.practicum.ewm.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.common.Create;
import ru.practicum.ewm.user.model.dto.UserDto;
import ru.practicum.ewm.user.service.UserService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * Controller end-point "/admin/users"
 */
@Slf4j
@Validated
@RestController
@RequestMapping(path = "/admin/users")
public class UserAdminController {

    private final UserService userService;

    public UserAdminController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Получение всех пользователей админом
     */
    @GetMapping
    public List<UserDto> getAllUsers(
            @NotEmpty @RequestParam List<Long> ids,
            @PositiveOrZero @RequestParam(required = false, defaultValue = "0") int from,
            @Positive @RequestParam(required = false, defaultValue = "10") int size
    ) {
        log.info("GET all users, from {}, size {}", from, size);
        return userService.getAllUsers(ids, from, size);
    }

    /**
     * Добавление пользователя админом
     */
    @PostMapping
    public UserDto postUser(@Validated(Create.class) @RequestBody UserDto userDto) {
        log.info("POST user {}", userDto);
        return userService.addUser(userDto);
    }

    /**
     * Удаление пользователя админом
     */
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        log.info("DELETE user by id={}", userId);
        userService.deleteUser(userId);
    }
}

