package com.zhitar.topjavagraduation.controller.user;

import com.zhitar.topjavagraduation.AuthorizedUser;
import com.zhitar.topjavagraduation.domain.User;
import com.zhitar.topjavagraduation.service.UserService;
import com.zhitar.topjavagraduation.to.UserTo;
import com.zhitar.topjavagraduation.util.UserUtil;
import com.zhitar.topjavagraduation.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(ProfileRestController.REST_PROFILE_URL)
public class ProfileRestController {

    public static final String REST_PROFILE_URL = "/rest/profile/";

    @Autowired
    private UserService service;

    @GetMapping
    public UserTo get(@AuthenticationPrincipal AuthorizedUser authUser) {
        return UserUtil.asTo(service.findById(authUser.getId()));
    }

    @PostMapping(value = "/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<UserTo> register(@Valid @RequestBody UserTo userTo) {
        User created = service.save(UserUtil.createNewFromTo(userTo));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_PROFILE_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(UserUtil.asTo(created));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody UserTo userTo, @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        ValidationUtil.assureIdConsistent(userTo, authorizedUser.getId());
        service.update(userTo);
    }
}
