package com.hw.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    private Date writingDate;
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    private User receiver;
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;
    @ManyToOne
    private CommentMark commentMark;
}
