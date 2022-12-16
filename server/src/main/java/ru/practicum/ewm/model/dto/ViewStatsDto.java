package ru.practicum.ewm.model.dto;

import lombok.*;

/**
 * A DTO for the  ViewStats entity {@link ru.practicum.ewm.model.ViewStats}
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
