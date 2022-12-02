package ru.practicum.ewm.place.model.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import ru.practicum.ewm.common.Create;

import javax.validation.constraints.NotNull;

/**
 * A DTO for the LocationType entity
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LocationTypeDto {

    private Long id;

    @NotNull(groups = {Create.class}, message = "не заполнен тип места")
    @Length(groups = {Create.class}, max = 50, min = 5,
            message = "знаков должно быть больше 5,но меньше 50")
    private String name;
}
