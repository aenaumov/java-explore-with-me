package ru.practicum.ewm.model.dto;

import lombok.*;

/**
 * A DTO for the Hit entity
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ViewStatsDto {

    private String app;

    private String uri;

    private long hits;
}
