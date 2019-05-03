package com.zhitar.topjavagraduation.util;

import com.zhitar.topjavagraduation.domain.Role;
import com.zhitar.topjavagraduation.domain.User;
import com.zhitar.topjavagraduation.to.UserTo;

public class UserUtil {

    private UserUtil() {}

    public static User createNewFromTo(UserTo newUser) {
        return new User(null, newUser.getName(), newUser.getEmail().toLowerCase(), newUser.getPassword(), Role.ROLE_USER);
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }
}
