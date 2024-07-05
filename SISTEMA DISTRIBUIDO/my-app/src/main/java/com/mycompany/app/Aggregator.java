package com.mycompany.app; 

import networking.WebClient;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

public class Aggregator {
    private WebClient webClient;

    public Aggregator() {
        this.webClient = new WebClient();
    }

    public List<String> sendTasksToWorkers(List<String> workersAddresses, List<String> object) {
        CompletableFuture<String>[] futures = new CompletableFuture[workersAddresses.size()];

        for (int i = 0; i < workersAddresses.size(); i++) {
            String workerAddress = workersAddresses.get(i);
            String task = object.get(i);
            byte[] requestPayload = SerializationUtils.serialize(task);

            futures[i] = webClient.sendTask(workerAddress,requestPayload)
                .thenApply(responseBytes -> {
                String result = (String) SerializationUtils.deserialize(responseBytes);
                return result;
                });
    }


        List<String> results = new ArrayList();
        for (int i = 0; i < object.size(); i++) {
            results.add(futures[i].join());
        }

        return results;
    }
}
