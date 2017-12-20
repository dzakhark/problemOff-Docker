package com.hw.controller.resourse;

import com.hw.model.projection.CategoryProjection;
import com.hw.util.resource.links.CategoryLinks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

@Component
public class CategoryProjectionResourceProcessor implements ResourceProcessor<Resource<CategoryProjection>> {

    private String self = "self";

    @Autowired
    private CategoryLinks categoryLinks;

    @Override
    public Resource<CategoryProjection> process(Resource<CategoryProjection> categoryProjectionResource) {
        String[] parsedHref = categoryProjectionResource
                .getLink(self)
                .getHref()
                .split("/");
        Long categoryId = Long.valueOf(parsedHref[parsedHref.length-1]);
        categoryProjectionResource.add(categoryLinks.getLazyProjectionLinks(categoryId));
        return categoryProjectionResource;
    }
}
