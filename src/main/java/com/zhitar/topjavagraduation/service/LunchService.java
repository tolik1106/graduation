package com.zhitar.topjavagraduation.service;

import com.zhitar.topjavagraduation.domain.Lunch;
import com.zhitar.topjavagraduation.domain.Restaurant;
import com.zhitar.topjavagraduation.repository.LunchRepository;
import com.zhitar.topjavagraduation.repository.RestaurantRepository;
import com.zhitar.topjavagraduation.util.ValidationUtil;
import com.zhitar.topjavagraduation.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LunchService {

    @Autowired
    private LunchRepository lunchRepo;

    @Autowired
    private RestaurantRepository restaurantRepo;

    public List<Lunch> findAll(Long restaurantId) {
        return lunchRepo.findAll(restaurantId);
    }

    public void saveOrUpdate(Lunch lunch, Long restaurantId) {
        Restaurant restaurant = restaurantRepo.findById(restaurantId).orElseThrow(() -> new NotFoundException("Restaurant with " + restaurantId + " not found"));
        lunch.setRestaurant(restaurant);
        lunchRepo.save(lunch);
    }

}
