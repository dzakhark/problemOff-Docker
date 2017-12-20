package com.hw.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TaskInstance {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @OneToOne
    private Task task;

    @ManyToOne
    private TaskMark taskMark;

}
