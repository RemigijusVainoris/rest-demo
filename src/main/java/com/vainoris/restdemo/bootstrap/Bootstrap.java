package com.vainoris.restdemo.bootstrap;

import com.vainoris.restdemo.domain.Category;
import com.vainoris.restdemo.domain.Customer;
import com.vainoris.restdemo.repositories.CategoryRepository;
import com.vainoris.restdemo.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Bootstrap implements CommandLineRunner
{
    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository)
    {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override public void run(String... args) throws Exception
    {
        loadCategories();
        loadCustomers();
    }

    private void loadCategories() {
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

    private void loadCustomers()
    {
        List<Customer> customers = new ArrayList<>();

        Customer johnDoe = new Customer();
        johnDoe.setFirstName("John");
        johnDoe.setLastName("Doe");
        customers.add(johnDoe);

        Customer janeDoe = new Customer();
        janeDoe.setFirstName("Jane");
        janeDoe.setLastName("Doe");
        customers.add(janeDoe);

        customers.forEach(customerRepository::save);
    }
}
