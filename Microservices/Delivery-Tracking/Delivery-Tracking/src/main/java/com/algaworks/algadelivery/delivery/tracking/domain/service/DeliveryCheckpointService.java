package com.algaworks.algadelivery.delivery.tracking.domain.service;

import com.algaworks.algadelivery.delivery.tracking.domain.model.Delivery;
import com.algaworks.algadelivery.delivery.tracking.domain.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class DeliveryCheckpointService {

    private final DeliveryRepository deliveryRepository;

    public void place (UUID deliveryId){
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow();
        delivery.place();
        deliveryRepository.saveAndFlush(delivery);
    }

    public void pickUp (UUID deliveryId, UUID courierId){
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow();
        delivery.pickUp(courierId);
        deliveryRepository.saveAndFlush(delivery);
    }

    public void complement (UUID deliveryId){
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow();
        delivery.markAsDelivered();
        deliveryRepository.saveAndFlush(delivery);
    }


}
