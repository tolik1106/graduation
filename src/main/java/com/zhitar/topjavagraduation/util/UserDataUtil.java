package com.zhitar.topjavagraduation.util;

import com.zhitar.topjavagraduation.domain.Role;
import com.zhitar.topjavagraduation.domain.User;

import java.util.Date;
import java.util.EnumSet;

public class UserDataUtil {

    private UserDataUtil() {}

    public static final User USER = new User(null, "Tolik", "tolik@user.com", "123456", true, new Date(), EnumSet.of(Role.USER));
    public static final User ADMIN = new User(null, "Admin", "admin@admin.com", "123456", true, new Date(), EnumSet.of(Role.ADMIN, Role.USER));
}
