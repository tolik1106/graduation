package com.zhitar.topjavagraduation.service;

import com.zhitar.topjavagraduation.AuthorizedUser;
import com.zhitar.topjavagraduation.domain.User;
import com.zhitar.topjavagraduation.repository.UserRepository;
import com.zhitar.topjavagraduation.to.UserTo;
import com.zhitar.topjavagraduation.util.UserUtil;
import com.zhitar.topjavagraduation.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.zhitar.topjavagraduation.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;
    public List<User> findAll() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name", "email"));
    }

    public void deleteById(Long id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    public User save(User user) {
        return repository.save(user);
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("User with id=" + id + " not found"));
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Transactional
    public User update(UserTo userTo) {
        User user = UserUtil.updateFromTo(findById(userTo.getId()), userTo);
        return repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }

    @Transactional
    public void enable(Long id, boolean enabled) {
        User user = findById(id);
        user.setEnabled(enabled);
    }
}
