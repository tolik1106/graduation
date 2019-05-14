package com.zhitar.topjavagraduation.controller.user;

import com.zhitar.topjavagraduation.service.UserService;
import com.zhitar.topjavagraduation.domain.User;
import com.zhitar.topjavagraduation.to.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.zhitar.topjavagraduation.util.UserUtil.createNewFromTo;

@RestController
@RequestMapping(AdminRestController.REST_URL)
public class AdminRestController {

    public static final String REST_URL = "/rest/admin/users";

    @Autowired
    private UserService service;

    @GetMapping
    public List<User> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public void createOrUpdate(@Valid @RequestBody UserTo userTo) {
        if (userTo.isNew()) {
            service.save(createNewFromTo(userTo));
        } else {
            service.update(userTo);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void enable(@PathVariable Long id, @RequestParam boolean enabled) {
        service.enable(id, enabled);
    }
}
