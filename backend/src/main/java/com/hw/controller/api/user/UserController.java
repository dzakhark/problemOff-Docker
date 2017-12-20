package com.hw.controller.api.user;

import com.hw.model.Category;
import com.hw.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;
import java.util.stream.Collectors;

@RepositoryRestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Resources<PersistentEntityResource>> getCategories(
            PersistentEntityResourceAssembler assembler) {
        Set<Category> mainCategories = categoryService.findAllMainCategories();
        return ResponseEntity.ok(
                new Resources<>(
                        mainCategories
                                .stream()
                                .map(assembler::toFullResource)
                                .collect(Collectors.toList())
                )
        );
    }

}
