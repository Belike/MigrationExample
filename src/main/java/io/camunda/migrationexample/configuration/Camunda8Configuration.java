package io.camunda.migrationexample.configuration;

import io.camunda.zeebe.client.ZeebeClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;

import java.util.Map;

@Configuration
@Slf4j
@Profile("c8")
public class Camunda8Configuration {

    private final ZeebeClient zeebeClient;

    public Camunda8Configuration(ZeebeClient zeebeClient){
        this.zeebeClient = zeebeClient;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startInstance() throws Exception {
        log.info("Trying to start instance of model with id: {}", ProcessUtil.RANDOM_GENERATPR_BPMN_KEY);
        log.info("Starting 5 instances of it: ");
        for(int i = 0; i < 5; i++){
            zeebeClient
                    .newCreateInstanceCommand()
                    .bpmnProcessId(ProcessUtil.RANDOM_GENERATPR_BPMN_KEY)
                    .latestVersion()
                    .variables(Map.of("businessKey", "bKey_"+i+1))
                    .send()
                    .exceptionally(
                            e -> {
                                log.info("Couldn't start instance sucessfully!");
                                log.error(e.getMessage());
                                throw new RuntimeException();
                            }
                    );
        }
    }
}
