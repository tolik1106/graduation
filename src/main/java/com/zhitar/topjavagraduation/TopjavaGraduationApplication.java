package com.zhitar.topjavagraduation;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class TopjavaGraduationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TopjavaGraduationApplication.class, args);
    }

    @Bean
    public Hibernate5Module hibernateModule(){
        return new Hibernate5Module();
    }

}
