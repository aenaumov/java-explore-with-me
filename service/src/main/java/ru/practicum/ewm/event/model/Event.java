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
 * Class Event
 */
@Entity
@Table(name = "EVENTS")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EVENT_ID")
    private Long id;

    @Column(name = "ANNOTATION")
    @Size(max = 2000)
    @NotNull
    private String annotation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category category;

    @Column(name = "CONFIRMED_REQUESTS")
    private int confirmedRequests;

    @Column(name = "CREATED_ON")
    private LocalDateTime createdOn;

    @Column(name = "EVENT_DESCRIPTION")
    @Size(max = 7000)
    @NotNull
    private String description;

    @Column(name = "EVENT_DATE")
    private LocalDateTime eventDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User initiator;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LOCATION_ID", nullable = false)
    private Location location;

    @Column(name = "PAID")
    private Boolean paid;

    @Column(name = "PARTICIPANT_LIMIT")
    private Integer participantLimit;

    @Column(name = "PUBLISHED_ON")
    private LocalDateTime publishedOn;

    @Column(name = "REQUEST_MODERATION")
    private Boolean requestModeration;

    @Column(name = "EVENT_STATE")
    @Enumerated(EnumType.STRING)
    private EventState state;

    @Column(name = "TITLE")
    @Size(max = 120)
    @NotNull
    private String title;

    @Column(name = "VIEWS")
    private long views;
}
