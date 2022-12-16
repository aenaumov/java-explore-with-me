package ru.practicum.ewm.client.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

/**
 * A DTO for the Hit entity
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EndPointHitDto {

    private Long id;

    private String app;

    private String uri;

    private String ip;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
}
