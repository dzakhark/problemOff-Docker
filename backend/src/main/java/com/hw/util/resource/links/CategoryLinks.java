package com.hw.util.resource.links;

import com.hw.controller.api.admin.category.AdminCategoryController;
import com.hw.model.Category;
import com.hw.model.Role;
import com.hw.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.hateoas.Link;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@PropertySource("classpath:constants.properties")
@Component
public class CategoryLinks implements HypermediaLinks<Category> {

    @Value("${user_role}")
    private String userRole;
    @Value("${admin_role}")
    private String adminRole;

    @Override
    public List<Link> getLinks(Long id) {
        List<Link> links = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken) && authentication != null) {
            User user = (User) authentication.getPrincipal();
            for (Role role : user.getRoles()) {
                if (role.getName().equals(userRole)) {
                    System.out.println();
                } else if (role.getName().equals(adminRole)) {
                    links.addAll(getLinksForAdmin(id));
                }
            }
        }
        return links;
    }

    @Override
    public List<Link> getLazyProjectionLinks(Long id) {
        List<Link> links = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken) && authentication != null) {
            User user = (User) authentication.getPrincipal();
            for (Role role : user.getRoles()) {
                if (role.getName().equals(userRole)) {
                    System.out.println();
                } else if (role.getName().equals(adminRole)) {
                    links.addAll(getLinksForAdmin(id));
                    links.add(linkTo(methodOn(AdminCategoryController.class)
                            .getSubCategories(id, null))
                            .withRel("getSubCategories"));
                }
            }
        }
        return links;
    }

    private List<Link> getLinksForAdmin(Long id) {
        List<Link> links = new ArrayList<>();
        links.add(linkTo(methodOn(AdminCategoryController.class)
                .setSubCategory(id, null, null))
                .withRel("setSubCategory"));
        links.add(linkTo(methodOn(AdminCategoryController.class)
                .createSubCategory(id,null, null))
                .withRel("createSubCategory"));
        links.add(linkTo(methodOn(AdminCategoryController.class)
                .setSuperCategory(id, null, null))
                .withRel("setSuperCategory"));
        links.add(linkTo(methodOn(AdminCategoryController.class)
                .createSuperCategory(id, null, null))
                .withRel("createSuperCategory"));
        links.add(linkTo(methodOn(AdminCategoryController.class)
                .alterToMain(id, null))
                .withRel("alterToMain"));
        links.add(linkTo(methodOn(AdminCategoryController.class)
                .createType(id, null, null))
                .withRel("createType"));
        links.add(linkTo(methodOn(AdminCategoryController.class)
                .setType(id, null, null))
                .withRel("setType"));
        return links;
    }

}
