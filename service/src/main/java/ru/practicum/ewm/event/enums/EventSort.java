package ru.practicum.ewm.event.enums;

import lombok.Getter;

/**
 * Enum сортировка
 */
@Getter
public enum EventSort {
    EVENT_DATE("eventDate"),
    VIEWS("views");

    private String shortName;

    EventSort(String shortName) {
        this.shortName = shortName;
    }
}
