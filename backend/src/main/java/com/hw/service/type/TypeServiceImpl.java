package com.hw.service.type;

import com.hw.model.Category;
import com.hw.model.Type;
import com.hw.repository.CategoryRepository;
import com.hw.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Type setCategory(Long id, Long categoryId) {
        Type type = typeRepository.findOne(id);
        Category category = categoryRepository.findOne(categoryId);
        type.setCategory(category);
        return typeRepository.save(type);
    }

    @Override
    public Type save(Type type) {
        return typeRepository.save(type);
    }
}
