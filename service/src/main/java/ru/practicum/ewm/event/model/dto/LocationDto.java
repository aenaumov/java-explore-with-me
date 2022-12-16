package ru.practicum.ewm.event.model.dto;

import lombok.*;

/**
 * A DTO for the Location entity {@link ru.practicum.ewm.event.model.Location}
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {

    private double lat;

    private double lon;
}
