package com.zhitar.topjavagraduation;

import com.zhitar.topjavagraduation.to.UserTo;
import com.zhitar.topjavagraduation.util.UserUtil;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {

    private UserTo userTo;

    public AuthorizedUser(com.zhitar.topjavagraduation.domain.User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        this.userTo = UserUtil.asTo(user);
    }

    public Long getId() {
        return userTo.getId();
    }

    public void update(UserTo newTo) {
        userTo = newTo;
    }

    public UserTo getUserTo() {
        return userTo;
    }

    public String toString() {
        return userTo.toString();
    }
}
