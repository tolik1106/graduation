package com.zhitar.topjavagraduation.controller.restaurant;

import com.zhitar.topjavagraduation.AuthorizedUser;
import com.zhitar.topjavagraduation.domain.Restaurant;
import com.zhitar.topjavagraduation.service.RestaurantService;
import com.zhitar.topjavagraduation.service.VoteService;
import com.zhitar.topjavagraduation.to.RestaurantTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/profile/restaurants")
public class ProfileRestaurantRestController {

    @Autowired
    private RestaurantService service;

    @Autowired
    private VoteService voteService;

    @GetMapping
    public List<RestaurantTo> getAll(@AuthenticationPrincipal AuthorizedUser authUser) {
        return service.findAll(authUser.getId());
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable Long id) {
        return service.getWithLunches(id);
    }

    @GetMapping("/{id}/vote")
    public void vote(
            @AuthenticationPrincipal AuthorizedUser user,
            @PathVariable Long id) {
        voteService.save(user.getId(), id);
    }
}
