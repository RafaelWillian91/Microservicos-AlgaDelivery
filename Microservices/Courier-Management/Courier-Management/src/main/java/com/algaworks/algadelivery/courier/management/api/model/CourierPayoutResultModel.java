package com.algaworks.algadelivery.courier.management.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
public class CourierPayoutResultModel {

    private BigDecimal payoutFee;

}
