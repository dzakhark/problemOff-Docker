package com.hw.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Correspondence {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private User interlocutor;

    @OneToMany(mappedBy = "correspondence")
    private Set<Message> messages;
}
