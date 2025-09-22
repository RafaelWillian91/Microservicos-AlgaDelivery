package com.algaworks.algadelivery.delivery.tracking.domain.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum DeliveryStatus {
    DRAFT,
    WAITING_FOR_COURIER(DRAFT),
    IN_TRANSIT(WAITING_FOR_COURIER),
    DELIVERY(IN_TRANSIT);

    private final List<DeliveryStatus> previosStatuses;

    DeliveryStatus(DeliveryStatus... previosStatuses) {
        this.previosStatuses = Arrays.asList(previosStatuses);
    }

    public boolean canNotChangeTo(DeliveryStatus newStatus){
        DeliveryStatus current = this;
        return !newStatus.previosStatuses.contains(current);
    }

    public boolean canChangeTo(DeliveryStatus newStatus){
        return !canNotChangeTo(newStatus);
    }
}
