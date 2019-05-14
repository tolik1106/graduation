package com.zhitar.topjavagraduation.controller.restaurant;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhitar.topjavagraduation.JsonUtil;
import com.zhitar.topjavagraduation.domain.Restaurant;
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

import static com.zhitar.topjavagraduation.RestaurantTestData.ILMOLINO;
import static com.zhitar.topjavagraduation.RestaurantTestData.ILMOLINO_ID;
import static com.zhitar.topjavagraduation.TestUtil.httpBasic;
import static com.zhitar.topjavagraduation.UserTestData.USER;
import static com.zhitar.topjavagraduation.controller.restaurant.ProfileRestaurantRestController.REST_PROFILE_RESTAURANTS_URL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProfileRestaurantRestControllerTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_PROFILE_RESTAURANTS_URL)
                .with(httpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGet() throws Exception {
        ResultActions actions = mockMvc.perform(get(REST_PROFILE_RESTAURANTS_URL + '/' + ILMOLINO_ID)
                .with(httpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        Restaurant result = JsonUtil.readValue(mapper, JsonUtil.getJsonString(actions), Restaurant.class);
        assertThat(ILMOLINO).isEqualTo(result);
    }

    @Test
    public void testVote() throws Exception {
        mockMvc.perform(get(REST_PROFILE_RESTAURANTS_URL + '/' + ILMOLINO_ID + "/vote")
            .with(httpBasic(USER)))
                .andExpect(status().isOk());
    }
}