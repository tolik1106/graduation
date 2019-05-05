package com.zhitar.topjavagraduation.repository;

import com.zhitar.topjavagraduation.domain.Restaurant;
import com.zhitar.topjavagraduation.to.RestaurantTo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @EntityGraph(attributePaths = "lunches", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r WHERE r.id=?1")
    Restaurant getWithLunches(Long id);

    @Query("SELECT new com.zhitar.topjavagraduation.to.RestaurantTo(r.id, r.name, count(v), sum(CASE WHEN v.user.id=:userId THEN 1 ELSE 0 END) > 0) FROM Restaurant r LEFT JOIN Vote v ON r.id=v.restaurant AND v.votedDate >= :date GROUP BY r.id")
    List<RestaurantTo> findAll(@Param("userId") Long userId, @Param("date") LocalDate date, Sort sort);

        //SELECT r.id, r.NAME, count(v.ID), (CASE WHEN v.USER_ID=10000 THEN 1 ELSE 0 end) as meVote FROM RESTAURANTS r LEFT JOIN VOTES v on r.ID = v.RESTAURANT_ID AND v.VOTED_DATE_TIME > parsedatetime('05-05-2019','dd-MM-yyyy') GROUP BY r.ID;
}
