package ru.practicum.ewm.request.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.practicum.ewm.request.RequestStatus;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * A DTO for the Request entity {@link ru.practicum.ewm.request.model.Request}
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ParticipationRequestDto {

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    @NotNull(message = "надо указать id события")
    private Long event;

    private Long requester;

    private RequestStatus status;

}
