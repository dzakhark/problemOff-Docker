package com.hw.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private Date dateOfFinish;
    private String price;

    @ManyToOne
    private User user;

    @ManyToOne
    private Type type;

    @OneToOne(mappedBy = "task")
    private TaskInstance taskInstance;

    @OneToMany(mappedBy = "task")
    private Set<TaskOrder> taskOrders;

    @ManyToOne
    private Address address;

    @ManyToOne
    private TaskStatus taskStatus;
}
