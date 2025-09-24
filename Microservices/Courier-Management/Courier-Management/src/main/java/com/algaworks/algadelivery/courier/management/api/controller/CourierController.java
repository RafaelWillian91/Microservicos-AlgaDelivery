package com.algaworks.algadelivery.courier.management.api.controller;

import com.algaworks.algadelivery.courier.management.api.model.CourierInput;
import com.algaworks.algadelivery.courier.management.domain.model.Courier;
import com.algaworks.algadelivery.courier.management.domain.model.repository.CourierRepository;
import com.algaworks.algadelivery.courier.management.domain.model.service.CourierRegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/couriers")
@RequiredArgsConstructor
public class CourierController {

    private final CourierRepository courierRepository;
    private final CourierRegistrationService courierRegistrationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Courier create(@Valid @RequestBody CourierInput courierInput){
        return courierRegistrationService.create(courierInput);
    }

    @PutMapping("/{courierId}")
    public Courier update(@PathVariable UUID courierId, @Valid @RequestBody CourierInput input){
        return courierRegistrationService.update(courierId, input);
    }

    @GetMapping
    public PagedModel<Courier> findAll(@PageableDefault Pageable pageable){
        return new PagedModel<>(
                courierRepository.findAll(pageable));
    }

    @GetMapping("/{courierId}")
    public Courier findById(@PathVariable UUID courierId){
        return courierRepository.findById(courierId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }











}
