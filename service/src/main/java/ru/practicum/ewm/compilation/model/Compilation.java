package ru.practicum.ewm.compilation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.ewm.event.model.Event;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Class Compilation
 */
@Entity
@Table(name = "COMPILATIONS")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Compilation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMPILATION_ID")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Event> events;

    @Column(name = "PINNED")
    private Boolean pinned;

    @Column(name = "TITLE")
    @Size(max = 255)
    @NotNull
    private String title;
}
