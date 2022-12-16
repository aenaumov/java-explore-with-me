package ru.practicum.ewm.category.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity Category - категория события
 */
@Entity
@Table(name = "CATEGORIES")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    /**
     * <p>id категории</p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Long id;

    /**
     * <p>name of category</p>
     */
    @Column(name = "CATEGORY_NAME")
    @Size(max = 50)
    @NotNull
    private String name;
}
