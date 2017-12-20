package com.hw.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NotificationStatus {
    @Id
    @GeneratedValue
    private Long id;
    private String value;

    @OneToMany(mappedBy = "notificationStatus")
    private Set<Notification> notifications;
}
