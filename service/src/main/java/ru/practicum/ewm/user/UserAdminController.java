package ru.practicum.ewm.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.common.Create;
import ru.practicum.ewm.common.EwmPageRequest;
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
@AllArgsConstructor
public class UserAdminController {

    private final UserService userService;

    /**
     * <p>Получение всех пользователей админом</p>
     *
     * @param ids  {@code List<Long>} список id пользователей
     * @param from {@code int} с какого элемента вывод
     * @param size {@code int} количество элементов в выводе
     * @return список {@code List<UserDto>} {@link ru.practicum.ewm.user.model.dto.UserDto}
     */
    @GetMapping
    public List<UserDto> getAllUsers(
            @NotEmpty @RequestParam List<Long> ids,
            @PositiveOrZero @RequestParam(required = false, defaultValue = "0") int from,
            @Positive @RequestParam(required = false, defaultValue = "10") int size
    ) {
        log.info("GET all users, from {}, size {}", from, size);
        return userService.getAllUsers(ids, new EwmPageRequest(from, size, Sort.unsorted()));
    }

    /**
     * <p>Создание пользователя админом</p>
     *
     * @param userDto {@code UserDto} {@link ru.practicum.ewm.user.model.dto.UserDto}
     * @return {@code UserDto} {@link ru.practicum.ewm.user.model.dto.UserDto}
     */
    @PostMapping
    public UserDto postUser(@Validated(Create.class) @RequestBody UserDto userDto) {
        log.info("POST user {}", userDto);
        return userService.addUser(userDto);
    }

    /**
     * <p>Удаление пользователя админом</p>
     *
     * @param userId {@code Long} id пользователя
     */
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        log.info("DELETE user by id={}", userId);
        userService.deleteUser(userId);
    }
}

