package com.algaworks.algadelivery.delivery.tracking.domain.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
//Value Object Pattern - Ele não tem identidade própria (id).O foco é nos atributos
public class ContactPoint {

    private String zipCode;
    private String street;
    private String number;

    private String complement;
    private String name;
    private String phone;


}
