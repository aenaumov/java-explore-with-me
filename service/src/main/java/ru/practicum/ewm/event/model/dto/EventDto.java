package ru.practicum.ewm.event.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import ru.practicum.ewm.common.Create;
import ru.practicum.ewm.common.Patch;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * A DTO for the Event entity
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

    @NotNull(groups = {Patch.class}, message = ("требуется ввести id события"))
    private Long eventId;

    @NotBlank(groups = {Patch.class}, message = "не заполнена аннотация")
    @NotNull(groups = {Create.class}, message = "не заполнена аннотация")
    @Length(groups = {Create.class, Patch.class}, max = 2000, min = 20,
            message = "знаков должно быть больше 20,но меньше 2000")
    private String annotation;

    @NotNull(groups = {Create.class, Patch.class}, message = ("требуется ввести id категории"))
    private Long category;

    @NotBlank(groups = {Patch.class}, message = "не заполнено описание")
    @NotNull(groups = {Create.class}, message = "не заполнено описание")
    @Length(groups = {Create.class, Patch.class}, max = 7000, min = 20,
            message = "знаков должно быть больше 20,но меньше 7000")
    private String description;

    @NotNull(groups = {Create.class, Patch.class}, message = ("требуется ввести дату"))
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;

    @NotNull(groups = {Create.class}, message = ("требуется ввести локацию"))
    private LocationDto location;

    private Boolean paid;

    private Integer participantLimit;

    private Boolean requestModeration;

    @NotBlank(groups = {Patch.class}, message = "не заполнен титл")
    @NotNull(groups = {Create.class}, message = "не заполнен титл")
    @Length(groups = {Create.class, Patch.class}, max = 120, min = 3,
            message = "знаков должно быть больше 3,но меньше 120")
    private String title;
}
