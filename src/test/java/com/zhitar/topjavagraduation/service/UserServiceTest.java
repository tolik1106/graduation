package com.zhitar.topjavagraduation.service;

import com.zhitar.topjavagraduation.domain.Role;
import com.zhitar.topjavagraduation.domain.User;
import com.zhitar.topjavagraduation.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.zhitar.topjavagraduation.UserTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class UserServiceTest extends AbstractServiceTest{

    @Autowired
    private UserService userService;

    @Test
    public void findAll() {
        List<User> users = userService.findAll();
        assertThat(users).usingElementComparatorIgnoringFields("registered", "roles").isEqualTo(List.of(ADMIN, BILBO, LILO, PETR, SONYA, USER));
    }

    @Test
    public void deleteById() {
        userService.deleteById(USER_ID);
        assertThat(userService.findAll()).usingElementComparatorIgnoringFields("registered").isEqualTo(List.of(ADMIN, BILBO, LILO, PETR, SONYA));
    }

    @Test(expected = NotFoundException.class)
    public void deleteByIdNotFound() {
        userService.deleteById(1L);
    }

    @Test
    public void save() {
        User newUser = new User(null, "New", "new@gmail.com", "newPass", false, new Date(), Collections.singleton(Role.ROLE_USER));
        User created = userService.save(newUser);
        newUser.setId(created.getId());
        assertEquals(newUser, created);
        assertThat(userService.findAll()).contains(USER, ADMIN, PETR, SONYA, LILO, BILBO, newUser);
    }

    @Test(expected = DataAccessException.class)
    public void duplicateMailCreate() {
        userService.save(new User(null, "Duplicate", "user@user.com", "newPass", false, new Date(), Collections.singleton(Role.ROLE_USER)));
    }

    @Test
    public void findById() {
        User user = userService.findById(ADMIN_ID);
        assertThat(user).isEqualToIgnoringGivenFields(ADMIN, "registered");
    }

    @Test(expected = NotFoundException.class)
    public void findByIdNotFound() {
        User user = userService.findById(1L);
    }

    @Test
    public void findByEmail() {
        User user = userService.findByEmail("user@user.com");
        assertThat(user).isEqualToIgnoringGivenFields(USER,"registered");
    }

    @Test
    public void update() {
        User updated = new User(USER);
        updated.setName("Updated");
        updated.setPassword("newPassword");
        updated.setRoles(Collections.singleton(Role.ROLE_ADMIN));
        userService.save(new User(updated));
        assertThat(userService.findById(USER_ID)).isEqualToIgnoringGivenFields(updated, "registered");
    }

    @Test
    public void enable() {
        userService.enable(USER_ID, false);
        assertFalse(userService.findById(USER_ID).isEnabled());
        userService.enable(USER_ID, true);
        assertTrue(userService.findById(USER_ID).isEnabled());
    }
}