package com.vainoris.restdemo.services;

import com.vainoris.restdemo.api.v1.mapper.CategoryMapper;
import com.vainoris.restdemo.api.v1.model.CategoryDTO;
import com.vainoris.restdemo.domain.Category;
import com.vainoris.restdemo.repositories.CategoryRepository;
import com.vainoris.restdemo.services.impl.CategoryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CategoryServiceTest
{
    private static final String CATEGORY_NAME = "Test Category";
    private static final Long CATEGORY_ID = 2L;

    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    public void getAllCategories() throws Exception
    {
        //given
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());

        //when
        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDTO> categoryDTOs = categoryService.getAllCategories();

        //then
        assertEquals(3, categoryDTOs.size());
    }

    @Test
    public void getCategoryByName() throws Exception
    {
        //given
        Category category = new Category();
        category.setId(CATEGORY_ID);
        category.setName(CATEGORY_NAME);

        Optional<Category> categoryOptional = Optional.of(category);


        //when
        when(categoryRepository.findByName(anyString())).thenReturn(categoryOptional);
        CategoryDTO categoryDTO = categoryService.getCategoryByName(CATEGORY_NAME);

        //then
        assertEquals(CATEGORY_ID, categoryDTO.getId());
        assertEquals(CATEGORY_NAME, categoryDTO.getName());
    }
}
