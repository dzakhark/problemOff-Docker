package com.hw.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`user`")
public class User implements UserDetails, Cloneable{
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String phoneNumber;
    private Date dateBirthday;
    private String description;

    @ManyToOne
    private Address address;

    @ManyToOne
    private UserSex sex;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @OneToMany(mappedBy = "receiver",
            fetch = FetchType.LAZY)
    private Set<Comment> comments;

    @OneToMany(mappedBy = "author",
            fetch = FetchType.LAZY)
    private Set<Comment> authorComments;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY)
    private Set<Service> services;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY)
    private Set<Notification> notifications;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY)
    private Set<Correspondence> correspondences;

    @OneToMany(mappedBy = "interlocutor",
            fetch = FetchType.LAZY)
    private Set<Correspondence> interlocutorCorrespondences;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY)
    private Set<Task> tasks;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY)
    private Set<TaskInstance> taskInstances;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY)
    private Set<TaskOrder> taskOrders;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
