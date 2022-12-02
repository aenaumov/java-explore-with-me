package ru.practicum.ewm.place.model.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import ru.practicum.ewm.common.Create;
import ru.practicum.ewm.place.model.LocationType;

import javax.validation.constraints.*;

/**
 * A DTO for the Place entity
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDto {

    private Long id;

    @NotNull(groups = {Create.class}, message = ("требуется ввести тип локации"))
    private LocationType locationType;

    @NotNull(groups = {Create.class}, message = "не заполнено название места")
    @Length(groups = {Create.class}, max = 200, min = 5,
            message = "знаков должно быть больше 5,но меньше 200")
    private String name;

    @Max(groups = {Create.class}, value = 90)
    @Min(groups = {Create.class}, value = -90)
    @NotNull(groups = {Create.class}, message = "не указана широта")
    private double lat;

    @Max(groups = {Create.class}, value = 180)
    @Min(groups = {Create.class}, value = -180)
    @NotNull(groups = {Create.class}, message = "не указана долгота")
    private double lon;

    @NotNull(groups = {Create.class}, message = "не указан радиус")
    private double rad;

}
