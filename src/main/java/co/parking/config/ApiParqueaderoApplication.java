package co.parking.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "co.parking.service", "co.parking.restcontroller" })
@EnableJpaRepositories(basePackages = { "co.parking.dao" })
@EntityScan("co.parking.domain")
public class ApiParqueaderoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiParqueaderoApplication.class, args);
	}
}
