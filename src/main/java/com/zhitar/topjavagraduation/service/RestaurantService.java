package com.zhitar.topjavagraduation.service;

import com.zhitar.topjavagraduation.domain.Restaurant;
import com.zhitar.topjavagraduation.repository.RestaurantRepository;
import com.zhitar.topjavagraduation.to.RestaurantTo;
import com.zhitar.topjavagraduation.util.ValidationUtil;
import com.zhitar.topjavagraduation.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.zhitar.topjavagraduation.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    @Cacheable(value = "restaurantSearch", key = "#id")
    public Restaurant getWithLunches(Long id) {
        return ValidationUtil.checkNotFoundWithId(repository.getWithLunches(id), id);
    }

    @Cacheable(value = "restaurants", key = "#userId")
    public List<RestaurantTo> findAll(Long userId) {
        return repository.findAll(userId, LocalDate.now(), Sort.by(Sort.Direction.ASC, "name"));
    }

    @CacheEvict(value = {"restaurants", "restaurantSearch"}, allEntries = true)
    public void deleteById(Long id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    @CacheEvict(value = {"restaurants", "restaurantSearch"}, allEntries = true)
    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public Restaurant findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Restaurant with id=" + id + " not found"));
    }

    @Transactional
    @CacheEvict(value = {"restaurants", "restaurantSearch"}, allEntries = true)
    public Restaurant update(Restaurant restaurant) {
        Restaurant toUpdate = repository.findById(restaurant.getId()).orElseThrow(() -> new NotFoundException("Restaurant with id=" + restaurant.getId() + " not found"));
        toUpdate.setName(restaurant.getName());
        return repository.save(toUpdate);
    }
}
