package com.hw.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Type {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "type")
    private Set<Service> services;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Category category;

    @OneToMany(mappedBy = "type")
    private Set<Task> tasks;
}
