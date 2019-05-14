package com.zhitar.topjavagraduation.repository;

import com.zhitar.topjavagraduation.domain.Lunch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LunchRepository extends JpaRepository<Lunch, Long> {

    @Query("SELECT l FROM Lunch l WHERE l.restaurant.id=:restaurantId ORDER BY l.description")
    List<Lunch> findAll(@Param("restaurantId") Long id);

    @Query("SELECT l FROM Lunch l WHERE l.id=?1 AND l.restaurant.id=?2")
    Lunch get(Long lunchId, Long restaurantId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Lunch l WHERE l.id=?1 AND l.restaurant.id=?2")
    int delete(Long lunchId, Long restaurantId);
}
