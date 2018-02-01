package com.vainoris.restdemo.services.impl;

import com.vainoris.restdemo.api.v1.mapper.CategoryMapper;
import com.vainoris.restdemo.api.v1.model.CategoryDTO;
import com.vainoris.restdemo.domain.Category;
import com.vainoris.restdemo.exceptions.ResourceNotFoundException;
import com.vainoris.restdemo.repositories.CategoryRepository;
import com.vainoris.restdemo.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService
{
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository)
    {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override public List<CategoryDTO> getAllCategories()
    {
        List<CategoryDTO> categories = new ArrayList<>();

        categoryRepository.findAll()
                .iterator()
                .forEachRemaining(category -> categories.add(categoryMapper.categoryToCategoryDTO(category)));

        return categories;
    }

    @Override public CategoryDTO getCategoryByName(String name)
    {
        Optional<Category> categoryOptional = categoryRepository.findByName(name);

        if (!categoryOptional.isPresent())
            throw new ResourceNotFoundException("Resource with name = " + name + " was not found!");

        return categoryMapper.categoryToCategoryDTO(categoryOptional.get());
    }
}
