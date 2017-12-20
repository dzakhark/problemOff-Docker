package com.hw.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Location {
    @Id
    @GeneratedValue
    private Long id;
    private String googlePlaceId;

    @OneToMany(mappedBy = "location")
    private Set<Address> addresses;

    @ManyToOne
    private Region region;
}
