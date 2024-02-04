package com.EventHorizon.EventHorizon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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