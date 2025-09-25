package com.algaworks.algadelivery.delivery.tracking.domain.infrastructure.http.client;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
public class CourierPayoutResultModel {

    private BigDecimal payoutFee;

}
