package com.algaworks.algadelivery.delivery.tracking;

import com.algaworks.algadelivery.delivery.tracking.domain.model.Delivery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class DeliveryTrackingApplication {

	public static void main(String[] args) {

		SpringApplication.run(DeliveryTrackingApplication.class, args);




	}

}
