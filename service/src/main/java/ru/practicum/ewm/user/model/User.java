package ru.practicum.ewm.user.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity User - пользователь
 */
@Entity
@Table(name = "USERS")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /**
     * id user
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    /**
     * имя пользователя
     */
    @Column(name = "USER_NAME")
    @Size(max = 50)
    @NotNull
    private String name;

    /**
     * e-mail пользователя
     */
    @Column(name = "EMAIL")
    @Size(max = 50)
    @NotNull
    private String email;
}
