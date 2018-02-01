package com.vainoris.restdemo.api.v1.mapper;

import com.vainoris.restdemo.api.v1.model.CategoryDTO;
import com.vainoris.restdemo.domain.Category;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryMapperTest
{
    private static final String CATEGORY_NAME = "Test Category";
    private static final Long CATEGORY_ID = 1L;

    private CategoryMapper categoryMapper = CategoryMapper.INSTANCE;


    @Test
    public void categoryToCategoryDTO() throws Exception
    {
        //given
        Category category = new Category();
        category.setName(CATEGORY_NAME);
        category.setId(CATEGORY_ID);

        //when
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        //then
        assertEquals(Long.valueOf(CATEGORY_ID), categoryDTO.getId());
        assertEquals(CATEGORY_NAME, categoryDTO.getName());
    }
}
