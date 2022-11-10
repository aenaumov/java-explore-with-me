package ru.practicum.ewm.category.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.category.service.CategoryService;
import ru.practicum.ewm.category.model.dto.CategoryDto;
import ru.practicum.ewm.common.Create;
import ru.practicum.ewm.common.Patch;

/**
 * Controller end-point "/admin/categories"
 */
@Slf4j
@Validated
@RestController
@RequestMapping(path = "/admin/categories")
public class CategoryAdminController {

    private final CategoryService categoryService;

    public CategoryAdminController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Обновление категории админом
     */
    @PatchMapping
    public CategoryDto updateCategoryByAdmin(@Validated(Patch.class) @RequestBody CategoryDto categoryDto) {
        log.info("PATCH category {} ", categoryDto);
        return categoryService.updateCategoryByAdmin(categoryDto);
    }

    /**
     * Добавление категории админом
     */
    @PostMapping
    public CategoryDto addCategoryByAdmin(@Validated(Create.class) @RequestBody CategoryDto categoryDto) {
        log.info("POST category {}", categoryDto);
        return categoryService.addCategoryByAdmin(categoryDto);
    }

    /**
     * Удаление категории админом
     */
    @DeleteMapping("/{catId}")
    public void deleteCategoryByAdmin(@PathVariable Long catId) {
        log.info("DELETE category {}", catId);
        categoryService.deleteCategoryByAdmin(catId);
    }
}
