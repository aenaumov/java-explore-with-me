package ru.practicum.ewm.place.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity LocationType - тип локации
 */
@Entity
@Table(name = "LOCATION_TYPES")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LocationType {

    /**
     * id location type
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCATION_TYPE_ID")
    private Long id;

    /**
     * название типа локации
     */
    @Column(name = "LOCATION_TYPE_NAME")
    @Size(max = 50, min = 5)
    @NotNull
    private String name;
}
