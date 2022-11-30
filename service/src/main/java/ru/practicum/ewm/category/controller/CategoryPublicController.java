package ru.practicum.ewm.category.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.category.service.CategoryService;
import ru.practicum.ewm.category.model.dto.CategoryDto;

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
public class CategoryPublicController {

    private final CategoryService categoryService;

    public CategoryPublicController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Получение всех категорий
     */
    @GetMapping
    public List<CategoryDto> getAllCategoriesPublic(
            @PositiveOrZero @RequestParam(required = false, defaultValue = "0") int from,
            @Positive @RequestParam(required = false, defaultValue = "10") int size
    ) {
        log.info("GET all categories from {}, size {}", from, size);
        return categoryService.getAllCategoriesPublic(from, size);
    }

    /**
     * Получение конкретной категории
     */
    @GetMapping("/{catId}")
    public CategoryDto getCategoryPublic(
            @PathVariable Long catId
    ) {
        log.info("GET category {}", catId);
        return categoryService.getCategoryPublic(catId);
    }
}
