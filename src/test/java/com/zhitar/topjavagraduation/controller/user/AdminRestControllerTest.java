package com.zhitar.topjavagraduation.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhitar.topjavagraduation.JsonUtil;
import com.zhitar.topjavagraduation.domain.Role;
import com.zhitar.topjavagraduation.domain.User;
import com.zhitar.topjavagraduation.service.UserService;
import com.zhitar.topjavagraduation.util.UserUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.zhitar.topjavagraduation.TestUtil.httpBasic;
import static com.zhitar.topjavagraduation.UserTestData.*;
import static com.zhitar.topjavagraduation.controller.user.AdminRestController.REST_URL;
import static com.zhitar.topjavagraduation.util.exception.ErrorType.DATA_ERROR;
import static com.zhitar.topjavagraduation.util.exception.ErrorType.VALIDATION_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AdminRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private UserService service;

    @Test
    public void testGetAll() throws Exception {
        ResultActions actions = mockMvc.perform(get(REST_URL)
                .with(httpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print());
        List<User> users = JsonUtil.readValues(mapper, JsonUtil.getJsonString(actions), User.class);
        assertThat(users).usingElementComparatorIgnoringFields("registered").isEqualTo(USERS);
    }

    @Test
    public void testGet() throws Exception {
        ResultActions actions = mockMvc.perform(get(REST_URL + '/' + ADMIN_ID)
                .with(httpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        User user = JsonUtil.readValue(mapper, JsonUtil.getJsonString(actions), User.class);
        assertThat(ADMIN).isEqualTo(user);
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + '/' + 1)
            .with(httpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void testGetUnAuth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetForbidden() throws Exception {
        mockMvc.perform(get(REST_URL)
            .with(httpBasic(USER)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testUpdate() throws Exception {
        User updated = new User(USER);
        updated.setName("Updated");
        mockMvc.perform(post(REST_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .with(httpBasic(ADMIN))
            .content(JsonUtil.writeValue(mapper, UserUtil.asTo(updated))))
                .andExpect(status().isOk());
        assertThat(service.findById(USER_ID)).isEqualTo(updated);
    }

    @Test
    public void testUpdateInvalid() throws Exception {
        User updated = new User(USER);
        updated.setName("    ");
        updated.setPassword("456");
        mockMvc.perform(post(REST_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .with(httpBasic(ADMIN))
            .content(JsonUtil.writeValue(mapper, updated)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.type").value(VALIDATION_ERROR.name()));
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void testUpdateDuplicate() throws Exception {
        User updated = new User(USER);
        updated.setEmail("admin@admin.com");
        mockMvc.perform(post(REST_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .with(httpBasic(ADMIN))
            .content(JsonUtil.writeValue(mapper, updated)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.type").value(DATA_ERROR.name()))
                .andDo(print());
    }

    @Test
    public void testCreate() throws Exception {
        User created = new User(null, "Created", "created@mail.com", "created", Role.ROLE_USER);
        mockMvc.perform(post(REST_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .with(httpBasic(ADMIN))
            .content(JsonUtil.writeValue(mapper, UserUtil.asTo(created))))
                .andExpect(status().isOk());

        assertThat(service.findByEmail("created@mail.com"))
                .isEqualToIgnoringGivenFields(created, "id", "registered");
    }

    @Test
    public void testCreateInvalid() throws Exception {
        User created = new User(null, null, "", "123", Role.ROLE_USER);
        mockMvc.perform(post(REST_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .with(httpBasic(ADMIN))
            .content(JsonUtil.writeValue(mapper, UserUtil.asTo(created))))
                .andExpect(jsonPath("$.type").value(VALIDATION_ERROR.name()))
                .andDo(print());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void testCreateDuplicate() throws Exception {
        User created = new User(null, "New", "admin@admin.com", "88888", Role.ROLE_USER);
        mockMvc.perform(post(REST_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .with(httpBasic(ADMIN))
            .content(JsonUtil.writeValue(mapper, UserUtil.asTo(created))))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.type").value(DATA_ERROR.name()))
                .andDo(print());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + '/' + USER_ID)
                .with(httpBasic(ADMIN)))
                .andExpect(status().isNoContent());
        assertThat(service.findAll())
                .usingElementComparatorIgnoringFields("registered")
                .isEqualTo(
                        USERS.stream()
                                .filter(u -> !u.equals(USER))
                                .collect(Collectors.toList()));
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + '/' + 1)
            .with(httpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void testEnable() throws Exception {
        User before = service.findById(USER_ID);

        enable("false");

        User after = service.findById(USER_ID);

        assertTrue(before.isEnabled());
        assertFalse(after.isEnabled());
        //To discard changes in db
        enable("true");
    }

    private void enable(String enable) throws Exception {
        mockMvc.perform(post(REST_URL + '/' + USER_ID)
            .with(httpBasic(ADMIN))
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .content("enabled=" + enable))
                .andExpect(status().isNoContent());
    }
}