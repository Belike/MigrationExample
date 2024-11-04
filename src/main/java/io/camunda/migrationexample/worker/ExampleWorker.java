package io.camunda.migrationexample.worker;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Profile("c8")
@Slf4j
public class ExampleWorker {
    @JobWorker(type = "example")
    public Map<String,Object> exampleJobWorker(ActivatedJob job, JobClient jobClient){
        log.info("I am just an example worker, wohoo");
        return Map.of("ReturnValue", "Only cool stuff");
    }
}
