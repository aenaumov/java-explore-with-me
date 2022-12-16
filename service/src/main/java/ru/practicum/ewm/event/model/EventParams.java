package ru.practicum.ewm.event.model;

import lombok.*;
import ru.practicum.ewm.event.enums.EventState;

import java.time.LocalDateTime;
import java.util.List;

/**
 * class - параметры для запросов с фильтрацией
 */

@Setter
@Getter
@Builder
@AllArgsConstructor
public class EventParams {

    private String text;

    private List<Long> categories;

    private LocalDateTime rangeStart;

    private LocalDateTime rangeEnd;

    private List<Long> users;

    private Boolean paid;

    private Boolean onlyAvailable;

    private List<EventState> states;

}
