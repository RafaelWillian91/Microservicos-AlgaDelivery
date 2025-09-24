package com.algaworks.algadelivery.delivery.tracking.domain.repository;

import com.algaworks.algadelivery.delivery.tracking.domain.model.ContactPoint;
import com.algaworks.algadelivery.delivery.tracking.domain.model.Delivery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

//teste de beans de persistencia
@DataJpaTest
//Para nao deixar criar um anco de dados de teste
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DeliveryRepositoryTest {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Test
    public void shouldPersist(){
        Delivery delivery = Delivery.draft();

        delivery.editPreparationDetails(createdValidPreparationDetails());

        delivery.addItem("Computador", 2);
        delivery.addItem("Notebook", 2);

        //flush → força o Hibernate a executar o INSERT imediatamente no banco (sem esperar o commit).
        //Isso é útil em testes, porque garante que o dado já foi enviado ao banco antes da próxima consulta.
        deliveryRepository.saveAndFlush(delivery);

        Delivery persistedDelivery = deliveryRepository.findById(delivery.getId()).orElseThrow();
        assertEquals(2, persistedDelivery.getItems().size());
    }

    private Delivery.PreparationDetails createdValidPreparationDetails(){
        ContactPoint sender = ContactPoint.builder()
                .zipCode("14806-655")
                .street("Rua Araraquara")
                .number("500")
                .complement("fundos")
                .name("Flavio Eduardo")
                .phone("(11) 90000-6565")
                .build();

        ContactPoint recipient = ContactPoint.builder()
                .zipCode("15554-655")
                .street("Rua Matao")
                .number("47")
                .complement("")
                .name("Maria Joao")
                .phone("(11) 90000-6565")
                .build();

        return Delivery.PreparationDetails.builder()
                .sender(sender)
                .recipient(recipient)
                .distanceFee(new BigDecimal("15.00"))
                .courierPayout(new BigDecimal("5.00"))
                .excepteDeliveryTime(Duration.ofHours(5))
                .build();
    }

}