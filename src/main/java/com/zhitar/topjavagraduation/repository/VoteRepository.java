package com.zhitar.topjavagraduation.repository;

import com.zhitar.topjavagraduation.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.votedDate >= :currentDay")
    Optional<Vote> get(@Param("userId") Long userId, @Param("currentDay") LocalDate currentDay);
}
