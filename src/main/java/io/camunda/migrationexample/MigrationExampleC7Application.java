package io.camunda.migrationexample;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@Profile("c7")
@Slf4j
@SpringBootApplication
@EnableProcessApplication("Migration-Example")
public class MigrationExampleC7Application {

    public static void main(String[] args) {
        SpringApplication.run(MigrationExampleC7Application.class, args);
    }

}
