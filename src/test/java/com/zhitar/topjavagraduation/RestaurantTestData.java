package com.zhitar.topjavagraduation;

import com.zhitar.topjavagraduation.domain.Lunch;
import com.zhitar.topjavagraduation.domain.Restaurant;

import java.math.BigDecimal;
import java.util.Set;

import static com.zhitar.topjavagraduation.domain.AbstractBaseEntity.LONG_START_SEQ;

public class RestaurantTestData {

    public static final Long ILMOLINO_ID = LONG_START_SEQ + 6;
    public static final Long SMORODINA_ID = LONG_START_SEQ + 7;
    public static final Long LKAFA_ID = LONG_START_SEQ + 8;
    public static final Long BANKA_ID = LONG_START_SEQ + 9;
    public static final Long SPEZO_ID = LONG_START_SEQ + 10;

    public static final Restaurant ILMOLINO = new Restaurant(ILMOLINO_ID, "Il'molino", null);
    public static final Restaurant SMORODINA = new Restaurant(SMORODINA_ID, "Smorodina", null);
    public static final Restaurant LKAFA = new Restaurant(LKAFA_ID, "I'kafa", null);
    public static final Restaurant BANKA = new Restaurant(BANKA_ID, "Banka", null);
    public static final Restaurant SPEZO = new Restaurant(SPEZO_ID, "Spezo", null);

    public static final Long LUNCH_BREAD_ID = LONG_START_SEQ + 11;
    public static final Long LUNCH_LEMON_TEA_ID = LONG_START_SEQ + 12;
    public static final Long LUNCH_ORANGE_JULCE_ID = LONG_START_SEQ + 13;

    public static final Lunch ILMOLINO_BREAD_LUNCH = new Lunch(LUNCH_BREAD_ID, "Bread", new BigDecimal("0.25"), ILMOLINO);
    public static final Lunch ILMOLINO_LEMON_TEA_LUNCH = new Lunch(LUNCH_LEMON_TEA_ID, "Lemon tea", new BigDecimal("1.2"), ILMOLINO);
    public static final Lunch ILMOLINO_ORANGE_JULCE_LUNCH = new Lunch(LUNCH_ORANGE_JULCE_ID, "Orange juice", new BigDecimal("0.99"), ILMOLINO);

    static {
        ILMOLINO.setLunches(Set.of(ILMOLINO_BREAD_LUNCH, ILMOLINO_LEMON_TEA_LUNCH, ILMOLINO_ORANGE_JULCE_LUNCH));
    }
}
