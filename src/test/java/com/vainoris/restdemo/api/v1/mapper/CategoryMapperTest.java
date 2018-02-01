package com.vainoris.restdemo.api.v1.mapper;

import com.vainoris.restdemo.api.v1.model.CategoryDTO;
import com.vainoris.restdemo.domain.Category;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryMapperTest
{
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO() throws Exception
    {
        //given
        String testCategory = "Test Category";
        Category category = new Category();
        category.setName(testCategory);
        category.setId(1L);

        //when
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        //then
        assertEquals(Long.valueOf(1L), categoryDTO.getId());
        assertEquals(testCategory, categoryDTO.getName());
    }
}
