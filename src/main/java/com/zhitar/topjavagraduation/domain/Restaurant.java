package com.zhitar.topjavagraduation.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
//@ToString
@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractBaseEntity {

    @Column(unique = true, nullable = false, length = 64)
    private String name;

    @OneToMany(mappedBy = "restaurant")
    @OrderBy("description ASC")
    private Set<Lunch> lunches = new HashSet<>();

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
//    private Set<User> users = new HashSet<>();

    public Restaurant() {
    }

    public Restaurant(String name) {
        this.name = name;
    }

    public Restaurant(String name, Set<Lunch> lunches) {
        this.name = name;
        this.lunches = lunches;
//        this.users = users;
    }

    public Restaurant(Long id, String name, Set<Lunch> lunches) {
        super(id);
        this.name = name;
        this.lunches = lunches;
//        this.users = users;
    }
//
//    public void setUsers(Set<User> users) {
//        this.users = CollectionUtils.isEmpty(users) ? Collections.EMPTY_SET : new HashSet<>(users);
//    }

}
