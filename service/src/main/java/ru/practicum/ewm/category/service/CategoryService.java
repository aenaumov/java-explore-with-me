package ru.practicum.ewm.category.service;

import org.springframework.data.domain.Pageable;
import ru.practicum.ewm.category.model.dto.CategoryDto;

import java.util.List;

/**
 * Интерфейс для работы с Category
 */
public interface CategoryService {

    /**
     * <p>метод для получения всех категории</p>
     *
     * @param pageable {@code Pageable} пагинация
     * @return list {@code List<CategoryDto>} of CategoryDto {@link ru.practicum.ewm.category.model.dto.CategoryDto}
     */
    List<CategoryDto> getAllCategoriesPublic(Pageable pageable);

    /**
     * <p>метод для получения категории по id</p>
     *
     * @param catId {@code Long} id category
     * @return {@code CategoryDto} of CategoryDto {@link ru.practicum.ewm.category.model.dto.CategoryDto}
     */
    CategoryDto getCategoryPublic(Long catId);

    /**
     * <p>метод для обновления категории админом</p>
     *
     * @param categoryDto {@code CategoryDto} CategoryDto {@link ru.practicum.ewm.category.model.dto.CategoryDto}
     * @return {@code CategoryDto} of CategoryDto {@link ru.practicum.ewm.category.model.dto.CategoryDto}
     */
    CategoryDto updateCategoryByAdmin(CategoryDto categoryDto);

    /**
     * <p>метод для создания категории админом</p>
     *
     * @param categoryDto {@code CategoryDto} CategoryDto {@link ru.practicum.ewm.category.model.dto.CategoryDto}
     * @return {@code CategoryDto} of CategoryDto {@link ru.practicum.ewm.category.model.dto.CategoryDto}
     */
    CategoryDto addCategoryByAdmin(CategoryDto categoryDto);

    /**
     * <p>метод для удаления категории админом</p>
     *
     * @param catId {@code Long} id category
     */
    void deleteCategoryByAdmin(Long catId);
}
