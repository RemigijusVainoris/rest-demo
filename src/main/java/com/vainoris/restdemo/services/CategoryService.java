package com.vainoris.restdemo.services;

import com.vainoris.restdemo.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService
{
    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);
}
