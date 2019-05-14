package com.zhitar.topjavagraduation.service;

import com.zhitar.topjavagraduation.domain.Lunch;
import com.zhitar.topjavagraduation.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static com.zhitar.topjavagraduation.RestaurantTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class LunchServiceTest extends AbstractServiceTest {

    @Autowired
    private LunchService service;

    @Autowired
    private RestaurantService restaurantService;

    @Test
    public void findAll() {
        List<Lunch> lunchList = service.findAll(ILMOLINO_ID);
        assertThat(lunchList).containsExactly(ILMOLINO_BREAD_LUNCH, ILMOLINO_LEMON_TEA_LUNCH, ILMOLINO_ORANGE_JULCE_LUNCH);
    }

    @Test
    public void save() {
        Lunch newLunch = new Lunch(null, "Rice", new BigDecimal("1.13"), null);
        Lunch created = service.saveOrUpdate(newLunch, ILMOLINO_ID);
        newLunch.setId(created.getId());
        assertEquals(newLunch, created);
        Set<Lunch> lunches = restaurantService.getWithLunches(ILMOLINO_ID).getLunches();
        assertThat(lunches).contains(ILMOLINO_BREAD_LUNCH, ILMOLINO_LEMON_TEA_LUNCH, ILMOLINO_ORANGE_JULCE_LUNCH, newLunch);
    }

    @Test
    public void update() {
        Lunch updated = new Lunch(LUNCH_BREAD_ID, "updated Lunch", new BigDecimal("2.99"), ILMOLINO);
        service.saveOrUpdate(updated, ILMOLINO_ID);
        assertThat(restaurantService.getWithLunches(ILMOLINO_ID).getLunches()).containsOnlyOnce(updated);
    }

    @Test
    public void delete() {
        service.deleteById(LUNCH_BREAD_ID, ILMOLINO_ID);
        assertThat(restaurantService.getWithLunches(ILMOLINO_ID).getLunches()).containsExactly(ILMOLINO_LEMON_TEA_LUNCH, ILMOLINO_ORANGE_JULCE_LUNCH);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.deleteById(1L, ILMOLINO_ID);
    }
}