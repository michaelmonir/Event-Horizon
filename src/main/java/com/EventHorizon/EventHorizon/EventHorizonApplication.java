package com.EventHorizon.EventHorizon;

import com.EventHorizon.EventHorizon.Repository.EventRepositories.EventRepository;
import com.EventHorizon.EventHorizon.Services.EventServices.FilterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EventHorizonApplication {

    public static void main(String[] args) {

        SpringApplication.run(EventHorizonApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(EventRepository eventRepository) {
        return runner -> {
            System.out.println("michaelllll");

        };
    }
}

