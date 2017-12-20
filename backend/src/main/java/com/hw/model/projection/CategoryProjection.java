package com.hw.model.projection;

import com.hw.model.Category;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "lazy", types = Category.class)
public interface CategoryProjection {
    String getName();
}
