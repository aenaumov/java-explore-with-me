package ru.practicum.ewm.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Class Hit
 */
@Entity
@Setter
@Getter
@Table(name = "HITS")
@AllArgsConstructor
@NoArgsConstructor
public class Hit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HIT_ID")
    private Long id;

    @Column(name = "APP")
    private String app;

    @Column(name = "URI")
    private String uri;

    @Column(name = "IP")
    private String ip;

    @Column(name = "TIMESTAMP")
    private LocalDateTime timestamp;

}
