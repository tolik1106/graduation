package com.zhitar.topjavagraduation;

import com.zhitar.topjavagraduation.domain.Role;
import com.zhitar.topjavagraduation.domain.User;

import java.util.List;

import static com.zhitar.topjavagraduation.domain.AbstractBaseEntity.LONG_START_SEQ;

public class UserTestData {

    public static final Long USER_ID = LONG_START_SEQ;
    public static final Long ADMIN_ID = LONG_START_SEQ + 1;
    public static final Long PETR_ID = LONG_START_SEQ + 2;
    public static final Long SONYA_ID = LONG_START_SEQ + 3;
    public static final Long LILO_ID = LONG_START_SEQ + 4;
    public static final Long BILBO_ID = LONG_START_SEQ + 5;

    public static final User USER = new User(USER_ID, "User", "user@user.com", "000000", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@admin.com", "123456", Role.ROLE_USER, Role.ROLE_ADMIN);
    public static final User PETR = new User(PETR_ID, "Petr", "petr@user.com", "654321", Role.ROLE_USER);
    public static final User SONYA = new User(SONYA_ID, "Sonya", "sonya@user.com", "11111", Role.ROLE_USER);
    public static final User LILO = new User(LILO_ID, "Lilo", "lilo@user.com", "22222", Role.ROLE_USER);
    public static final User BILBO = new User(BILBO_ID, "Bilbo", "bilbo@user.com", "55555", Role.ROLE_USER);

    public static final List<User> USERS = List.of(ADMIN, BILBO, LILO, PETR, SONYA, USER);

}
