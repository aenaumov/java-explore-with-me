package ru.practicum.ewm.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity Hit - статистическая запись в БД
 */
@Entity
@Setter
@Getter
@Table(name = "HITS")
@AllArgsConstructor
@NoArgsConstructor
public class Hit {
    /**
     * id of Entity Hit
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HIT_ID")
    private Long id;

    /**
     * name of application which sent statistic
     */
    @Column(name = "APP")
    private String app;

    /**
     * uri по которому был запрос
     */
    @Column(name = "URI")
    private String uri;

    /**
     * ip с которого пришел запрос
     */
    @Column(name = "IP")
    private String ip;

    /**
     * время запроса
     */
    @Column(name = "TIMESTAMP")
    private LocalDateTime timestamp;

}
