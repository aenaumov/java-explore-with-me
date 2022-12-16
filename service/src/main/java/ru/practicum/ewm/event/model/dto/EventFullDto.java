package ru.practicum.ewm.event.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.practicum.ewm.event.enums.EventState;

import java.time.LocalDateTime;

/**
 * A DTO for the Event entity {@link ru.practicum.ewm.event.model.Event}
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EventFullDto {

    private Long id;

    private String annotation;

    private CategoryEventDto category;

    private int confirmedRequests;

    private LocalDateTime createdOn;

    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;

    private UserEventDto initiator;

    private LocationEventDto location;

    private Boolean paid;

    private int participantLimit;

    private LocalDateTime publishedOn;

    private Boolean requestModeration;

    private EventState state;

    private String title;

    private long views;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserEventDto {
        private Long id;
        private String name;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CategoryEventDto {
        private Long id;
        private String name;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LocationEventDto {
        private double lat;
        private double lon;
    }
}
