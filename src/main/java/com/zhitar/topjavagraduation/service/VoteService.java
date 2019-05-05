package com.zhitar.topjavagraduation.service;

import com.zhitar.topjavagraduation.domain.Restaurant;
import com.zhitar.topjavagraduation.domain.Vote;
import com.zhitar.topjavagraduation.repository.RestaurantRepository;
import com.zhitar.topjavagraduation.repository.UserRepository;
import com.zhitar.topjavagraduation.repository.VoteRepository;
import com.zhitar.topjavagraduation.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Vote save(Long userId, Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new NotFoundException("Restaurant with " + restaurantId + " not found"));
        if (restaurant == null) return null;
        LocalDateTime votedDateTime = LocalDateTime.now();
        Vote vote = voteRepository.get(userId, votedDateTime.toLocalDate()).orElse(null);
        if (vote == null) {
            vote = new Vote();
            vote.setUser(userRepository.getOne(userId));
            vote.setRestaurant(restaurantRepository.getOne(restaurantId));
            vote.setVotedDate(votedDateTime.toLocalDate());
            vote.setVotedTime(votedDateTime.toLocalTime());
        } else {
            if (vote.getVotedTime().isAfter(LocalTime.of(11, 00))) {
                return vote;
            } else {
                vote.setVotedTime(votedDateTime.toLocalTime());
                vote.setRestaurant(restaurantRepository.getOne(restaurantId));
            }
        }
        return voteRepository.save(vote);
    }
}
