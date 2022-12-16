package ru.practicum.ewm.model;

import lombok.*;


/**
 * Entity ViewStats
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ViewStats {

    /**
     * name of application the request came from
     */
    private String app;

    /**
     * uri of request
     */
    private String uri;

    /**
     * ip the request came from
     */
    private long hits;
}
