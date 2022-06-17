package br.com.api.order.consomer.sqs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.api.order.consomer.sqs.service.sqs.SQSService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws Exception {
		
		SpringApplication.run(Application.class, args);

		List<Integer> contadores = new ArrayList<Integer>();

		contadores.add(0, 0);
		contadores.add(1, 0);

		while(true) {			
			System.out.println("Read messages ...");
			
			contadores = SQSService.messageService(contadores);
		}
	}
}
