package ru.practicum.ewm.category.service;

import ru.practicum.ewm.category.model.dto.CategoryDto;

import java.util.List;

/**
 * Интерфейс для работы с Category
 */
public interface CategoryService {

    /**
     * Получить все категории
     */
    List<CategoryDto> getAllCategoriesPublic(int from, int size);

    /**
     * Получить категорию по id
     */
    CategoryDto getCategoryPublic(Long catId);

    /**
     * Обновить категорию для админа
     */
    CategoryDto updateCategoryByAdmin(CategoryDto categoryDto);

    /**
     * Добавить категорию для админа
     */
    CategoryDto addCategoryByAdmin(CategoryDto categoryDto);

    /**
     * Удалить категорию для админа
     */
    void deleteCategoryByAdmin(Long catId);
}
