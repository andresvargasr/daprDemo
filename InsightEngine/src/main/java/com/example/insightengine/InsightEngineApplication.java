package com.example.insightengine;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class InsightEngineApplication {
    private static final Logger logger = LoggerFactory.getLogger(InsightEngineApplication.class);
    public static void main(String[] args) throws InterruptedException{
        String TOPIC_NAME = "applications";
        String PUBSUB_NAME = "applicationpubsub";
        DaprClient client = new DaprClientBuilder().build();
        for (int i = 0; i <= 10; i++) {
            int applicationId = i;
            Application application = new Application(applicationId);
            // Publish an event/message using Dapr PubSub
            client.publishEvent(
                    PUBSUB_NAME,
                    TOPIC_NAME,
                    application).block();
            logger.info("Published data: " + application.getApplicationId());
            TimeUnit.MILLISECONDS.sleep(5000);
        }
    }
}
@AllArgsConstructor
@Getter
class Application {
    private int applicationId;
}