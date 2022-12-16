package ru.practicum.ewm.category;

import ru.practicum.ewm.category.model.Category;
import ru.practicum.ewm.category.model.dto.CategoryDto;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Утилитный класс для конвертации Category {@link ru.practicum.ewm.category.model.Category} в DTO и обратно
 */
public final class CategoryMapper {

    private CategoryMapper() {
    }

    public static CategoryDto toDto(Category category) {
        final Long id = category.getId();
        final String name = category.getName();
        return new CategoryDto(id, name);
    }

    public static Category toCategory(CategoryDto categoryDto) {
        final String name = categoryDto.getName();
        return new Category(null, name);
    }

    public static List<CategoryDto> toCategoryDtoList(List<Category> categories) {
        return categories.stream()
                .map(CategoryMapper::toDto)
                .collect(Collectors.toList());
    }
}
