package com.zhitar.topjavagraduation.service;

import com.zhitar.topjavagraduation.domain.Restaurant;
import com.zhitar.topjavagraduation.repository.RestaurantRepository;
import com.zhitar.topjavagraduation.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    public Restaurant getWithLunches(Long id) {
        return repository.getWithLunches(id);
    }

    public List<Restaurant> findAll() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public long count() {
        return 0;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public Restaurant findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Restaurant update(Restaurant restaurant) {
        Restaurant toUpdate = repository.findById(restaurant.getId()).orElseThrow(() -> new NotFoundException("Restaurant with " + restaurant.getId() + " not found"));
        toUpdate.setName(restaurant.getName());
        return repository.save(toUpdate);
    }
}
