package com.zhitar.topjavagraduation.service;

import com.zhitar.topjavagraduation.domain.Restaurant;
import com.zhitar.topjavagraduation.to.RestaurantTo;
import com.zhitar.topjavagraduation.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.zhitar.topjavagraduation.RestaurantTestData.*;
import static com.zhitar.topjavagraduation.UserTestData.ADMIN_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    public void getWithLunches() {
        Restaurant withLunches = service.getWithLunches(ILMOLINO_ID);
        assertThat(withLunches).isEqualToComparingFieldByField(ILMOLINO);
    }

    @Test(expected = NotFoundException.class)
    public void getWithLunchesNotFound() {
        service.getWithLunches(1L);
    }

    @Test
    public void findAll() {
        List<RestaurantTo> restaurantToList = service.findAll(ADMIN_ID);
        assertNotNull(restaurantToList);
        assertThat(restaurantToList.size()).isEqualTo(5);
    }

    @Test(expected = NotFoundException.class)
    public void deleteById() {
        Restaurant restaurant = service.findById(BANKA_ID);
        assertThat(restaurant).isEqualTo(BANKA);
        service.deleteById(BANKA_ID);
        service.findById(BANKA_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.deleteById(1L);
    }

    @Test
    public void save() {
        Restaurant newRestaurant = new Restaurant("Mafia");
        Restaurant created = service.save(newRestaurant);
        newRestaurant.setId(created.getId());
        assertEquals(newRestaurant, created);
        assertThat(service.findAll(ADMIN_ID).size()).isEqualTo(6);
    }

    @Test
    public void update() {
        Restaurant updated = new Restaurant(BANKA_ID, "New Banka", null);
        service.save(updated);
        assertThat(service.findById(BANKA_ID)).isEqualTo(updated);
    }
}