package com.vainoris.restdemo.bootstrap;

import com.vainoris.restdemo.domain.Category;
import com.vainoris.restdemo.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Bootstrap implements CommandLineRunner
{
    private CategoryRepository categoryRepository;

    public Bootstrap(CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    @Override public void run(String... args) throws Exception
    {
        List<Category> categories = new ArrayList<>();

        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categories.add(fruits);
        categories.add(dried);
        categories.add(fresh);
        categories.add(exotic);
        categories.add(nuts);

        categories.forEach(categoryRepository::save);
    }
}
