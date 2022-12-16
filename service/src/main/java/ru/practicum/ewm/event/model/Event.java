package ru.practicum.ewm.event.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.ewm.category.model.Category;
import ru.practicum.ewm.event.enums.EventState;
import ru.practicum.ewm.user.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Entity Event - событие
 */
@Entity
@Table(name = "EVENTS")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    /**
     * id event
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EVENT_ID")
    private Long id;

    /**
     * аннотация события
     */
    @Column(name = "ANNOTATION")
    @Size(max = 2000)
    @NotNull
    private String annotation;

    /**
     * категория события
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category category;

    /**
     * количество подтвержденных запросов на участие в событии
     */
    @Column(name = "CONFIRMED_REQUESTS")
    private int confirmedRequests;

    /**
     * дата создания события
     */
    @Column(name = "CREATED_ON")
    private LocalDateTime createdOn;

    /**
     * описание события
     */
    @Column(name = "EVENT_DESCRIPTION")
    @Size(max = 7000)
    @NotNull
    private String description;

    /**
     * дата события
     */
    @Column(name = "EVENT_DATE")
    private LocalDateTime eventDate;

    /**
     * инициатор события
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User initiator;

    /**
     * локация события
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LOCATION_ID", nullable = false)
    private Location location;

    /**
     * платное (true) / бесплатное (false) участие в событии
     */
    @Column(name = "PAID")
    private Boolean paid;

    /**
     * лимит участников в событии
     */
    @Column(name = "PARTICIPANT_LIMIT")
    private Integer participantLimit;

    /**
     * дата публикации
     */
    @Column(name = "PUBLISHED_ON")
    private LocalDateTime publishedOn;

    /**
     * требуется-ли модерация запроса на участие в событии
     */
    @Column(name = "REQUEST_MODERATION")
    private Boolean requestModeration;

    /**
     * состояние события
     */
    @Column(name = "EVENT_STATE")
    @Enumerated(EnumType.STRING)
    private EventState state;

    /**
     * титл события
     */
    @Column(name = "TITLE")
    @Size(max = 120)
    @NotNull
    private String title;

    /**
     * количество просмотров события
     */
    @Column(name = "VIEWS")
    private long views;
}
