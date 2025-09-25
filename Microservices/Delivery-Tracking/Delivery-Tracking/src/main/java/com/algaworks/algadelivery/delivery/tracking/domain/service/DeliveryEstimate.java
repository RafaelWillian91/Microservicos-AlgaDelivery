package com.algaworks.algadelivery.delivery.tracking.domain.service;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;

//Classe é um Value Object
@Data
@AllArgsConstructor
public class DeliveryEstimate {


    private Duration estimatedTime;

    private Double distanceInKM;
}
