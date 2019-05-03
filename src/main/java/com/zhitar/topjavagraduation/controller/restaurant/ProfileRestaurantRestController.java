package com.zhitar.topjavagraduation.controller.restaurant;

import com.zhitar.topjavagraduation.domain.Restaurant;
import com.zhitar.topjavagraduation.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/profile/restaurants")
public class ProfileRestaurantRestController {

    @Autowired
    private RestaurantService service;

    @GetMapping
    public List<Restaurant> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable Long id) {
        return service.getWithLunches(id);
    }

}
