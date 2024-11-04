package io.camunda.migrationexample.services;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class RandomService {
    public int nextRandomInt(){
        return ThreadLocalRandom.current().nextInt();
    }

    public int nextRandomInt(int lowerBound, int upperBound){
        return ThreadLocalRandom.current().nextInt(lowerBound, upperBound);
    }
}
