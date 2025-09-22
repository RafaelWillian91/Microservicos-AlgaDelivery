package com.algaworks.algadelivery.delivery.tracking.domain.model;

import com.algaworks.algadelivery.delivery.tracking.domain.exception.DomainException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryTest {

    @Test
    public void shouldChangeToPlaced(){
        Delivery delivery = Delivery.draft();

        delivery.editPreparationDetails(createdValidPreparationDetails());

        delivery.place();

        assertEquals(DeliveryStatus.WAITING_FOR_COURIER, delivery.getDeliveryStatus());
        assertNotNull(delivery.getPlacedAt());
    }

    @Test
    public void shouldNotPlace() {
        Delivery delivery = Delivery.draft();// cria um delivery no estado DRAFT
        assertThrows(DomainException.class, () -> {
            delivery.place(); // tenta colocar o pedido
        });

        assertEquals(DeliveryStatus.DRAFT, delivery.getDeliveryStatus());
        assertNull(delivery.getPlacedAt());
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