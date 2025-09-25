package com.algaworks.algadelivery.delivery.tracking.domain.infrastructure.http.client;

import com.algaworks.algadelivery.delivery.tracking.domain.service.CourierPayoutCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CourierPayoutCalculationServicehttpImpl implements CourierPayoutCalculationService {

    private final CourierAPIClient courierAPIClient;

    @Override
    public BigDecimal calculatePayout(Double distanceInKM) {
        CourierPayoutResultModel courierPayoutResultModel = courierAPIClient.payoutCalculation(
                new CourierPayoutCalculationInput(distanceInKM));
        return courierPayoutResultModel.getPayoutFee();
    }
}
