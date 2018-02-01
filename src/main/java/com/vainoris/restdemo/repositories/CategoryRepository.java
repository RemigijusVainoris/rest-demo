package com.vainoris.restdemo.repositories;

import com.vainoris.restdemo.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>
{
}
