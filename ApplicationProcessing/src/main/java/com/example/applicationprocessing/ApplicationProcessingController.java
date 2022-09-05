package com.example.applicationprocessing;

import org.slf4j.LoggerFactory;

import io.dapr.Topic;
import io.dapr.client.domain.CloudEvent;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

@RestController
public class ApplicationProcessingController{
    private static final Logger logger = LoggerFactory.getLogger(ApplicationProcessingController.class);
    @Topic(name = "applications", pubsubName = "applicationpubsub")
    @PostMapping(path = "/applications", consumes = MediaType.ALL_VALUE)
    public Mono<ResponseEntity> getCheckout(@RequestBody(required = false) CloudEvent<Application> cloudEvent) {
        return Mono.fromSupplier(() -> {
            try {
                logger.info("Application Processing received: " + cloudEvent.getData().getApplicationId());
                return ResponseEntity.ok("SUCCESS");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}

@Getter
@Setter
class Application {
    private int applicationId;
}