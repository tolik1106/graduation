package com.zhitar.topjavagraduation.service;

import com.zhitar.topjavagraduation.domain.Lunch;
import com.zhitar.topjavagraduation.domain.Restaurant;
import com.zhitar.topjavagraduation.repository.LunchRepository;
import com.zhitar.topjavagraduation.repository.RestaurantRepository;
import com.zhitar.topjavagraduation.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.zhitar.topjavagraduation.util.ValidationUtil.checkNotFoundWithId;

@Service
public class LunchService {

    @Autowired
    private LunchRepository lunchRepo;

    @Autowired
    private RestaurantRepository restaurantRepo;

    public List<Lunch> findAll(Long restaurantId) {
        return lunchRepo.findAll(restaurantId);
    }

    @Transactional
    @CacheEvict(value = {"restaurantSearch"}, allEntries = true)
    public Lunch saveOrUpdate(Lunch lunch, Long restaurantId) {
        Restaurant restaurant = restaurantRepo.findById(restaurantId).orElseThrow(() -> new NotFoundException("Restaurant with id=" + restaurantId + " not found"));
        lunch.setRestaurant(restaurant);
        return lunchRepo.save(lunch);
    }

    @CacheEvict(value = {"restaurantSearch"}, allEntries = true)
    public void deleteById(Long lunchId, Long restaurantId) {
        checkNotFoundWithId(lunchRepo.delete(lunchId, restaurantId) != 0, lunchId);
    }

}
