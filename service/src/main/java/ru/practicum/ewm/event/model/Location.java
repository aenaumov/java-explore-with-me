package ru.practicum.ewm.event.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Entity Location - локация места проведения события
 */
@Entity
@Table(name = "LOCATIONS")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    /**
     * id location
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCATION_ID")
    private Long id;

    /**
     * широта
     */
    @Column(name = "LAT")
    private double lat;

    /**
     * долгота
     */
    @Column(name = "LON")
    private double lon;
}
