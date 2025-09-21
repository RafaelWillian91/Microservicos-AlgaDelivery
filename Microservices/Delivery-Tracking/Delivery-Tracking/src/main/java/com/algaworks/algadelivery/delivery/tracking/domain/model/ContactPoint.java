package com.algaworks.algadelivery.delivery.tracking.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@AllArgsConstructor
@Builder
@Getter
//Value Object Pattern - Ele não tem identidade própria (id).O foco é nos atributos
public class ContactPoint {

    private String zipCode;
    private String street;
    private String number;

    private String complement;
    private String name;
    private String phone;


}
