package com.EventHorizon.EventHorizon;

import com.EventHorizon.EventHorizon.Entities.ClientGoingView;
import com.EventHorizon.EventHorizon.Repository.ClientGoingViewRepository;
import com.EventHorizon.EventHorizon.Repository.EventRepositories.EventRepository;
import com.EventHorizon.EventHorizon.Services.EventServices.FilterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class EventHorizonApplication {

    public static void main(String[] args) {

        SpringApplication.run(EventHorizonApplication.class, args);
    }
//    @Bean
//    public CommandLineRunner commandLineRunner(ClientGoingViewRepository clientGoingViewRepository ) {
//        return runner -> {
//            System.out.println("michaelllll");
//            List<ClientGoingView> list = clientGoingViewRepository.findAll();
//            for (ClientGoingView clientGoingView : list) {
//                System.out.println(clientGoingView.getClient_id());
//            }
//        };
//    }
}

