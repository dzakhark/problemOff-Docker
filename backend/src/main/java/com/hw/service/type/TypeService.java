package com.hw.service.type;

import com.hw.model.Type;

public interface TypeService {

    Type setCategory(Long id, Long categoryId);
    Type save(Type type);
}
