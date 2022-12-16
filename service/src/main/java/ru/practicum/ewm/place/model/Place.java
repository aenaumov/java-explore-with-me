package ru.practicum.ewm.place.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity Place - конкретная локация
 */
@Entity
@Table(name = "PLACES")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Place {

    /**
     * id place
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLACE_ID")
    private Long id;

    /**
     * тип локации
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LOCATION_TYPE_ID", nullable = false)
    private LocationType locationType;

    /**
     * название локации
     */
    @Column(name = "PLACE_NAME")
    @Size(max = 200, min = 5)
    @NotNull
    private String name;

    /**
     * широта
     */
    @Column(name = "PLACE_LAT")
    @NotNull
    private double lat;

    /**
     * долгота
     */
    @Column(name = "PLACE_LON")
    @NotNull
    private double lon;

    /**
     * радиус
     */
    @Column(name = "PLACE_RAD")
    @NotNull
    private double rad;
}
