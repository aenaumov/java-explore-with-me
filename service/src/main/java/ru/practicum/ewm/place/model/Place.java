package ru.practicum.ewm.place.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Class Place
 */
@Entity
@Table(name = "PLACES")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLACE_ID")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LOCATION_TYPE_ID", nullable = false)
    private LocationType locationType;

    @Column(name = "PLACE_NAME")
    @Size(max = 200, min = 5)
    @NotNull
    private String name;

    @Column(name = "PLACE_LAT")
    @NotNull
    private double lat;

    @Column(name = "PLACE_LON")
    @NotNull
    private double lon;

    @Column(name = "PLACE_RAD")
    @NotNull
    private double rad;
}
