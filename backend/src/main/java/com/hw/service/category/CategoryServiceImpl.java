package com.hw.service.category;

import com.hw.model.Category;
import com.hw.model.Type;
import com.hw.repository.CategoryRepository;
import com.hw.service.type.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TypeService typeService;

    @Override
    public Set<Category> findAllMainCategories() {
        return categoryRepository.findAllMainCategories();
    }

    @Override
    public Category addSubCategory(Long id, Long subId) {
        Category category = categoryRepository.findOne(id);
        Category subCategory = categoryRepository.findOne(subId);
        subCategory.setSuperCategory(category);
        categoryRepository.save(subCategory);
        return category;
    }

    @Override
    public Category createSubCategory(Long id, Category subCategory) {
        Category category = categoryRepository.findOne(id);
        subCategory.setSuperCategory(category);
        categoryRepository.save(subCategory);
        return category;
    }

    @Override
    public Category setSuperCategory(Long id, Long superId) {
        Category category = categoryRepository.findOne(id);
        Category superCategory = categoryRepository.findOne(superId);
        category.setSuperCategory(superCategory);
        return categoryRepository.save(category);
    }

    @Override
    public Category createSuperCategory(Long id, Category superCategory) {
        Category category = categoryRepository.findOne(id);
        category.setSuperCategory(superCategory);
        return categoryRepository.save(category);
    }

    @Override
    public Category alterToMain(Long id) {
        Category category = categoryRepository.findOne(id);
        category.setSuperCategory(null);
        return categoryRepository.save(category);
    }

    @Override
    public Category addType(Long id, Type type) {
        Category category = categoryRepository.findOne(id);
        type.setCategory(category);
        typeService.save(type);
        return category;
    }

    @Override
    public Category addType(Long id, Long typeId) {
        Category category = categoryRepository.findOne(id);
        typeService.setCategory(typeId, id);
        return category;
    }

    @Override
    public Set<Category> getSubCategories(Long id) {
        return categoryRepository.findAllBySuperCategory(categoryRepository.findOne(id));
    }

    @Override
    public void delete(Long id) {
        categoryRepository.delete(id);
    }
}
