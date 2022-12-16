package ru.practicum.ewm.category.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.category.service.CategoryService;
import ru.practicum.ewm.category.model.dto.CategoryDto;
import ru.practicum.ewm.common.EwmPageRequest;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * Controller end-point "/categories"
 */
@Slf4j
@Validated
@RestController
@RequestMapping(path = "/categories")
@AllArgsConstructor
public class CategoryPublicController {

    private final CategoryService categoryService;

    /**
     * <p>Получение всех категорий</p>
     *
     * @param from {@code int} с какой позиции выводить
     * @param size {@code int} сколько элементов выводить
     * @return {@code List<CategoryDto>} {@link ru.practicum.ewm.category.model.dto.CategoryDto}
     */
    @GetMapping
    public List<CategoryDto> getAllCategoriesPublic(
            @PositiveOrZero @RequestParam(required = false, defaultValue = "0") int from,
            @Positive @RequestParam(required = false, defaultValue = "10") int size
    ) {
        log.info("GET all categories from {}, size {}", from, size);
        return categoryService.getAllCategoriesPublic(new EwmPageRequest(from, size, Sort.unsorted()));
    }

    /**
     * <p>Получение конкретной категории</p>
     *
     * @param catId {@code Long} id категории
     * @return {@code CategoryDto} {@link ru.practicum.ewm.category.model.dto.CategoryDto}
     */
    @GetMapping("/{catId}")
    public CategoryDto getCategoryPublic(
            @PathVariable Long catId
    ) {
        log.info("GET category {}", catId);
        return categoryService.getCategoryPublic(catId);
    }
}
