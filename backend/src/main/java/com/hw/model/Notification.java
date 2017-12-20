package com.hw.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notification {
    @Id
    @GeneratedValue
    private Long id;
    private String value;
    private Date dateOfCome;

    @ManyToOne
    private User user;
    @ManyToOne
    private NotificationStatus notificationStatus;
}
