package com.zhitar.topjavagraduation;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@SpringBootApplication
public class TopjavaGraduationApplication {

    @Value("classpath:db/initDb.sql")
    private Resource initDbScript;

    @Value("classpath:db/populateDb.sql")
    private Resource populateDbScript;

    public static void main(String[] args) {
        SpringApplication.run(TopjavaGraduationApplication.class, args);
    }


    @Bean
    public Hibernate5Module hibernateModule(){
        return new Hibernate5Module();
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScripts(initDbScript, populateDbScript);
        return populator;
    }

}
