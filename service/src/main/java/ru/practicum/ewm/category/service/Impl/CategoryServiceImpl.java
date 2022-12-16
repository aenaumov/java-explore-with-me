package ru.practicum.ewm.category.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.category.CategoryMapper;
import ru.practicum.ewm.category.CategoryRepository;
import ru.practicum.ewm.category.model.Category;
import ru.practicum.ewm.category.model.dto.CategoryDto;
import ru.practicum.ewm.category.service.CategoryService;
import ru.practicum.ewm.exception.NotFoundException;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getAllCategoriesPublic(Pageable pageable) {
        final List<Category> categories = categoryRepository.findAll(pageable).toList();
        return CategoryMapper.toCategoryDtoList(categories);
    }

    @Override
    public CategoryDto getCategoryPublic(Long catId) {
        final Category category = getCategoryById(catId);
        return CategoryMapper.toDto(category);
    }

    @Override
    @Transactional
    public CategoryDto updateCategoryByAdmin(CategoryDto categoryDto) {
        Category category = getCategoryById(categoryDto.getId());
        category.setName(categoryDto.getName());
        return CategoryMapper.toDto(category);
    }

    @Override
    @Transactional
    public CategoryDto addCategoryByAdmin(CategoryDto categoryDto) {
        final Category category = categoryRepository.save(CategoryMapper.toCategory(categoryDto));
        return CategoryMapper.toDto(category);
    }

    @Override
    @Transactional
    public void deleteCategoryByAdmin(Long catId) {
        categoryRepository.deleteById(catId);
    }

    private Category getCategoryById(Long catId) {
        return categoryRepository.findById(catId)
                .orElseThrow(() -> new NotFoundException(String.format("Category with id=%d was not found", catId)));
    }
}
