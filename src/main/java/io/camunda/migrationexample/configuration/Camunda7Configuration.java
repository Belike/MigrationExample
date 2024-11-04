package io.camunda.migrationexample.configuration;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;

import java.util.List;

@Configuration
@Profile("c7")
@Slf4j
public class Camunda7Configuration {

    private final RepositoryService repositoryService;
    private final RuntimeService runtimeService;

    public Camunda7Configuration(RepositoryService repositoryService, RuntimeService runtimeService){
        this.repositoryService = repositoryService;
        this.runtimeService = runtimeService;
    }

    @EventListener
    public void startInstancePostDeploy(PostDeployEvent event){
        // Generate a list of all deployments, identified by ProcessDefinition.getKey()
        List<String> deployments = repositoryService.createProcessDefinitionQuery()
                .list()
                .stream()
                .map(ProcessDefinition::getKey)
                .toList();

        log.info("There are currently {} Deployments registered with the engine", deployments.size());

        if(!deployments.isEmpty() && deployments.contains(ProcessUtil.RANDOM_GENERATPR_BPMN_KEY)){
            for(int i = 0; i < 5; i++){
                //Starting new Instances with businessKey 1..5
                runtimeService.startProcessInstanceByKey(
                        ProcessUtil.RANDOM_GENERATPR_BPMN_KEY,
                        "bKey_"+i+1);
            }
        }
    }
}
