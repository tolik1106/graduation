package com.zhitar.topjavagraduation.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhitar.topjavagraduation.JsonUtil;
import com.zhitar.topjavagraduation.domain.User;
import com.zhitar.topjavagraduation.service.UserService;
import com.zhitar.topjavagraduation.to.UserTo;
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
import org.springframework.transaction.annotation.Transactional;

import static com.zhitar.topjavagraduation.UserTestData.USER;
import static com.zhitar.topjavagraduation.controller.user.ProfileRestController.REST_PROFILE_URL;
import static org.assertj.core.api.Assertions.assertThat;
import static com.zhitar.topjavagraduation.TestUtil.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProfileRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private UserService service;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_PROFILE_URL)
                .with(httpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("10000"))
                .andExpect(jsonPath("$.name").value("User"));
    }

    @Test
    public void testGetUnAuth() throws Exception {
        mockMvc.perform(get(REST_PROFILE_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testRegister() throws Exception {
        UserTo createdTo = new UserTo(null, "newName", "newemail@user.com", "99999");

        ResultActions actions = mockMvc.perform(post(REST_PROFILE_URL + "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(mapper, createdTo)))
                .andDo(print())
                .andExpect(status().isCreated());

        User returned = JsonUtil.readValue(mapper, JsonUtil.getJsonString(actions), User.class);
        User created = UserUtil.createNewFromTo(createdTo);
        created.setId(returned.getId());
        assertThat(created).isEqualTo(returned);
        assertThat(returned).isEqualTo(service.findByEmail(createdTo.getEmail()));
    }

    @Test
    public void testUpdate() throws Exception {
        UserTo updatedTo = new UserTo(null, "newName", "newemail@user.com", "99999");

        mockMvc.perform(put(REST_PROFILE_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .with(httpBasic(USER))
                    .content(JsonUtil.writeValue(mapper, updatedTo)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThat(UserUtil.updateFromTo(new User(USER), updatedTo)).isEqualTo(service.findByEmail(updatedTo.getEmail()));
    }
}
