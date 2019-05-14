package com.zhitar.topjavagraduation.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "votes", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "votedDate"}, name = "votes_unique_user_date_idx"))
public class Vote extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate votedDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime votedTime;
}
