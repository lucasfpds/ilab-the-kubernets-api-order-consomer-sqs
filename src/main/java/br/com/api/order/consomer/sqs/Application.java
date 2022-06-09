package br.com.api.order.consomer.sqs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.api.order.consomer.sqs.service.SQSService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);

		while(true) {
			System.out.println("Read messages ...");
			SQSService.messageService();
		}
	}
}
