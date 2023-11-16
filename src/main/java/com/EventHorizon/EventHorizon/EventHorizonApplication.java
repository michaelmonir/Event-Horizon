package com.EventHorizon.EventHorizon;

import com.EventHorizon.EventHorizon.MailSender.EmailSenderService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class EventHorizonApplication {
	public static void main(String[] args) {
		SpringApplication.run(EventHorizonApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(EmailSenderService emailSenderService){
		return runner->{
			emailSenderService.sendMail(
					"es-AhmedMo.Saleh2025@alexu.edu.eg",
					"hi",
					"hi"
			);
			System.out.println("hi");
		};
	}
}


