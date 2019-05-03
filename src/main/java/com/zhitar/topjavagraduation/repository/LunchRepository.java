package com.zhitar.topjavagraduation.repository;

import com.zhitar.topjavagraduation.domain.Lunch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LunchRepository extends JpaRepository<Lunch, Long> {

    @Query("SELECT l FROM Lunch l WHERE l.restaurant.id=:restaurantId ORDER BY l.description")
    List<Lunch> findAll(@Param("restaurantId") Long id);

    @Query("SELECT l FROM Lunch l WHERE l.id=:lunchId AND l.restaurant.id=:restaurantId")
    Lunch get(Long lunchId, Long restaurantId);
}
