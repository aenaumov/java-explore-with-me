package ru.practicum.ewm.category.model.dto;

import lombok.*;
import ru.practicum.ewm.common.Create;
import ru.practicum.ewm.common.Patch;

import javax.validation.constraints.NotBlank;

/**
 * A DTO for the Category entity
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Long id;

    @NotBlank(groups = {Create.class, Patch.class}, message = ("наименование категории не должно быть пустым"))
    private String name;
}
