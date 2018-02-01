package com.vainoris.restdemo.repositories;

import com.vainoris.restdemo.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long>
{
    Optional<Category> findByName(String name);
}
