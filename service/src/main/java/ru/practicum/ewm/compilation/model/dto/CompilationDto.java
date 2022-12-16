package ru.practicum.ewm.compilation.model.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * A DTO for the Compolation entity {@link ru.practicum.ewm.compilation.model.Compilation}
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CompilationDto {

    private Long id;

    private List<EventShortDto> events;

    private Boolean pinned;

    private String title;

    @Setter
    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EventShortDto {

        private Long id;

        private String annotation;

        private CategoryEventDto category;

        private int confirmedRequests;

        private LocalDateTime eventDate;

        private UserEventDto initiator;

        private Boolean paid;

        private String title;

        private long views;
    }

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
}
