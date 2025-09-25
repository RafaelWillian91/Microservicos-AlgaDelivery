package com.algaworks.algadelivery.courier.management.domain.model.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CourierPayoutService {

    public BigDecimal calculate(Double distanceKm){
        return new BigDecimal("10")
                .multiply(new BigDecimal(distanceKm))
                .setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }


}
