package com.zhitar.topjavagraduation.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractBaseEntity {

    @NotBlank
    @Size(min = 2, max = 64)
    @Column(unique = true, nullable = false, length = 64)
    private String name;

    @OneToMany(mappedBy = "restaurant")
    @OrderBy("description ASC")
    private Set<Lunch> lunches = new HashSet<>();

    public Restaurant() {
    }

    public Restaurant(String name) {
        this.name = name;
    }

    public Restaurant(String name, Set<Lunch> lunches) {
        this.name = name;
        this.lunches = lunches;
    }

    public Restaurant(Long id, String name, Set<Lunch> lunches) {
        super(id);
        this.name = name;
        this.lunches = lunches;
    }
}
