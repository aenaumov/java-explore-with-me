package ru.practicum.ewm.compilation.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * A DTO for the Compilation entity
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NewCompilationDto {

    private Set<Long> events;

    private Boolean pinned;

    @NotBlank(message = "описание не должно быть пустым")
    private String title;
}
