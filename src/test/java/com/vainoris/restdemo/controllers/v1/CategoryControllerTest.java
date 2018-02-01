package com.vainoris.restdemo.controllers.v1;

import com.vainoris.restdemo.api.v1.model.CategoryDTO;
import com.vainoris.restdemo.services.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerTest
{
    private static final String CATEGORY_ONE_NAME = "Test Category One";
    private static final String CATEGORY_TWO_NAME = "Test Category Two";

    private static final Long CATEGORY_ONE_ID = 1L;
    private static final Long CATEGORY_TWO_ID = 2L;


    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public void testListCategories() throws Exception
    {
        CategoryDTO categoryOne = new CategoryDTO();
        categoryOne.setId(CATEGORY_ONE_ID);
        categoryOne.setName(CATEGORY_ONE_NAME);

        CategoryDTO categoryTwo = new CategoryDTO();
        categoryTwo.setId(CATEGORY_TWO_ID);
        categoryTwo.setName(CATEGORY_TWO_NAME);

        List<CategoryDTO> categoryDTOs = Arrays.asList(categoryOne, categoryTwo);

        when(categoryService.getAllCategories()).thenReturn(categoryDTOs);


        mockMvc.perform(get(CategoryController.ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2)));

    }

    @Test
    public void testGetByNameCategories() throws Exception
    {
        CategoryDTO category = new CategoryDTO();
        category.setId(1L);
        category.setName("Tom");

        when(categoryService.getCategoryByName(anyString())).thenReturn(category);

        mockMvc.perform(get("/api/v1/categories/Tom")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Tom")));
    }
}
