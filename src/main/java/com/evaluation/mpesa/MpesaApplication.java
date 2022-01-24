package com.evaluation.mpesa;

import com.evaluation.mpesa.customers.Customer;
import com.evaluation.mpesa.customers.CustomerRepository;
import com.evaluation.mpesa.customers.Gender;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootApplication
public class MpesaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MpesaApplication.class, args);
	}

//	@Bean
//	CommandLineRunner runner(CustomerRepository repository) {
//		return args -> {
//
//			String email = "pato@gmail.com";
//			Customer customer = new Customer(
//					"Pato",
//					"Kim",
//					email,
//					"0715786923",
//					Gender.MALE,
//					5000.00,
//					LocalDateTime.now()
//			);
//
//			// this is created in the reposiroty
//			repository.findCustomerByEmail(email).ifPresentOrElse(c -> {
//				System.out.println(c.getEmail() + " already exist");
//			}, () -> {
//				System.out.println("Inserting Customer " + customer);
//				repository.insert(customer);
//			});
//
//		};
//	}

}
