package com.hw.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category",
            fetch = FetchType.LAZY)
    @RestResource(exported = false)
    private Set<Type> types;

    @OneToMany(mappedBy = "superCategory",
            fetch = FetchType.LAZY)
    @RestResource(exported = false)
    private Set<Category> subCategories;

    @ManyToOne
//    @RestResource(exported = false)
    private Category superCategory;
}
