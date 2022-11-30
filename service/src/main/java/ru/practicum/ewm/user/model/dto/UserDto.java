package ru.practicum.ewm.user.model.dto;

import lombok.*;
import ru.practicum.ewm.common.Create;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * A DTO for the User entity
 */

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    @NotNull(groups = {Create.class}, message = ("требуется ввести имя"))
    private String name;

    @Email(groups = {Create.class}, regexp = "\\w+(\\.?)\\w+@\\w+.\\w+", message = ("email не прошел валидацию"))
    @NotNull(groups = {Create.class}, message = ("требуется ввести e-mail"))
    private String email;
}
