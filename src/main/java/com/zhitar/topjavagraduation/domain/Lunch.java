package com.zhitar.topjavagraduation.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "restaurant")
@Entity
@Table(name = "lunches")
public class Lunch extends AbstractBaseEntity{

    @NotBlank
    @Size(min = 3, max = 255)
    @Column(nullable = false)
    private String description;

    @NotNull
    @Digits(integer = 7, fraction = 2)
    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Restaurant restaurant;

    public Lunch(Long id, String description, BigDecimal price, Restaurant restaurant) {
        this(description, price, restaurant);
        this.id = id;
    }
}
