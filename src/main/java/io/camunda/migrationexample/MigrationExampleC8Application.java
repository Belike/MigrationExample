package io.camunda.migrationexample;

import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.camunda.community.migration.adapter.EnableCamunda7Adapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@Profile("c8")
@SpringBootApplication
@EnableCamunda7Adapter
@Deployment(resources = "classpath*:/models/Bpmn-C8/*.bpmn")
public class MigrationExampleC8Application {

    public static void main(String ... args){
        SpringApplication.run(MigrationExampleC8Application.class, args);
    }
}
