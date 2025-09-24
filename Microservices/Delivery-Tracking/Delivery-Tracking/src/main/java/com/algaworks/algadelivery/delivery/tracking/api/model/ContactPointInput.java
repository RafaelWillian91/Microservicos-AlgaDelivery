package com.algaworks.algadelivery.delivery.tracking.api.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContactPointInput {

    @NotBlank
    private String zipCode;
    @NotBlank
    private String street;
    @NotBlank
    private String number;

    private String complement;
    @NotBlank
    private String name;
    @NotBlank
    private String phone;



}
