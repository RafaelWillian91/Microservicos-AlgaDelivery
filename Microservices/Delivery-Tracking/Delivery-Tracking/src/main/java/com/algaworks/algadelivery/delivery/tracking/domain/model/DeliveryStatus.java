package com.algaworks.algadelivery.delivery.tracking.domain.model;

import java.util.Arrays;
import java.util.List;

public enum DeliveryStatus {
    DRAFT,
    //só pode mudar para WAITING_FOR_COURIER se o status atual for DRAFT
    WAITING_FOR_COURIER(DRAFT),
    IN_TRANSIT(WAITING_FOR_COURIER),
    DELIVERED(IN_TRANSIT);

    //Cada enum pode ter uma lista de status anteriores válidos
    private final List<DeliveryStatus> previosStatuses;

    DeliveryStatus(DeliveryStatus... previosStatuses) {

        this.previosStatuses = Arrays.asList(previosStatuses);
    }

    public boolean canNotChangeTo(DeliveryStatus newStatus){
        DeliveryStatus current = this;
        return !newStatus.previosStatuses.contains(current);
    }

    public boolean canChangeTo(DeliveryStatus newStatus){
        return canNotChangeTo(newStatus);
    }
}
