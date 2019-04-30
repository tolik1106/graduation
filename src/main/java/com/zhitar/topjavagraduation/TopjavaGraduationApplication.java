package com.zhitar.topjavagraduation;

import com.zhitar.topjavagraduation.domain.Restaurant;
import com.zhitar.topjavagraduation.domain.User;
import com.zhitar.topjavagraduation.repository.RestaurantRepository;
import com.zhitar.topjavagraduation.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.zhitar.topjavagraduation.util.RestaurantDataUtil.LKAFA;
import static com.zhitar.topjavagraduation.util.RestaurantDataUtil.SMORODINA;
import static com.zhitar.topjavagraduation.util.UserDataUtil.ADMIN;
import static com.zhitar.topjavagraduation.util.UserDataUtil.USER;

@SpringBootApplication
public class TopjavaGraduationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TopjavaGraduationApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository repository, RestaurantRepository restaurantRepository) {
        return (args -> {
            save(repository, restaurantRepository);
        });
    }

    public void save(UserRepository repository, RestaurantRepository restaurantRepository) {
        restaurantRepository.save(LKAFA);
        restaurantRepository.save(SMORODINA);

        repository.save(USER);
        repository.save(ADMIN);

        Restaurant lkafa = restaurantRepository.findById(10000L).get();
        Restaurant smorodina = restaurantRepository.findById(10001L).get();

        User user = repository.findById(10002L).get();
        User admin = repository.findById(10003L).get();

        System.out.println(user.getId());
        System.out.println(admin.getId());

        user.setRestaurant(lkafa);
        admin.setRestaurant(smorodina);

        repository.save(user);
        repository.save(admin);
    }

}
