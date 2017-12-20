package com.hw.repository;

import com.hw.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    @Query("select c from Category c where c.superCategory = null")
    Set<Category> findAllMainCategories();

    Set<Category> findAllBySuperCategory(Category category);
}