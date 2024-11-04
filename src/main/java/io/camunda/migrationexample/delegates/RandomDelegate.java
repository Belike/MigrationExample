package io.camunda.migrationexample.delegates;

import io.camunda.migrationexample.services.RandomService;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RandomDelegate implements JavaDelegate {

    private final RandomService randomService;

    public RandomDelegate(RandomService randomService){
        this.randomService = randomService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Executing RandomDelegate");
        int random = randomService.nextRandomInt();
        delegateExecution.setVariable("RandomNumber", random);
    }
}
