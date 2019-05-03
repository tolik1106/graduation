package com.zhitar.topjavagraduation.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
//@ToString
@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractBaseEntity {

    public Restaurant() {
    }

    public Restaurant(String name, Set<Lunch> lunches, Set<User> users) {
        this.name = name;
        this.lunches = lunches;
        this.users = users;
    }

    public Restaurant(Long id, String name, Set<Lunch> lunches, Set<User> users) {
        super(id);
        this.name = name;
        this.lunches = lunches;
        this.users = users;
    }

    @Column(unique = true, nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "restaurant")
    @OrderBy("description ASC")
    private Set<Lunch> lunches = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<User> users = new HashSet<>();

    public Restaurant(String name) {
        this.name = name;
    }

    public void setUsers(Set<User> users) {
        this.users = CollectionUtils.isEmpty(users) ? Collections.EMPTY_SET : new HashSet<>(users);
    }

}
