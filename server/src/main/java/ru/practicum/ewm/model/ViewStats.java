package ru.practicum.ewm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Class ViewStats
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ViewStats {

    private String app;

    private String uri;

    private long hits;
}