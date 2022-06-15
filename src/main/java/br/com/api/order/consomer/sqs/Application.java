package br.com.api.order.consomer.sqs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.api.order.consomer.sqs.service.sqs.SQSService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);

		Integer count = 0;

		while(true) {			
			System.out.println("Read messages ...");
			
			count = SQSService.messageService(count);
		}
	}
}
