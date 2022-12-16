package ru.practicum.ewm.request.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.ewm.event.model.Event;
import ru.practicum.ewm.request.RequestStatus;
import ru.practicum.ewm.user.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity Request - запрос на участие в событии
 */
@Entity
@Table(name = "REQUESTS")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Request {

    /**
     * id request
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REQUEST_ID")
    private Long id;

    /**
     * дата создания запроса
     */
    @Column(name = "CREATED")
    private LocalDateTime created;

    /**
     * событие
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EVENT_ID", nullable = false)
    private Event event;

    /**
     * пользователь подавший запрос на участие в событии
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User requester;

    /**
     * статус запроса
     */
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
}
