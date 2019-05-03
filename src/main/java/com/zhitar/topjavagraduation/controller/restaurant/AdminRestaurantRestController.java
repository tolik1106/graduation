package com.zhitar.topjavagraduation.controller.restaurant;

import com.zhitar.topjavagraduation.domain.Lunch;
import com.zhitar.topjavagraduation.domain.Restaurant;
import com.zhitar.topjavagraduation.service.LunchService;
import com.zhitar.topjavagraduation.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/admin/restaurants")
public class AdminRestaurantRestController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private LunchService lunchService;

    @PostMapping
    public void createOrUpdate(@RequestBody Restaurant restaurant) {
        if (restaurant.isNew()) {
            restaurantService.save(restaurant);
        } else {
            restaurantService.update(restaurant);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        restaurantService.deleteById(id);
    }

    @PostMapping("/{id}")
    public void createOrUpdateLunch(@PathVariable Long id, @RequestBody Lunch lunch) {
        lunchService.saveOrUpdate(lunch, id);
    }
}
