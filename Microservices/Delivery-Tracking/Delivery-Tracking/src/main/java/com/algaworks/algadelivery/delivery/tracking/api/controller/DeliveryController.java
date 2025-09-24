package com.algaworks.algadelivery.delivery.tracking.api.controller;

import com.algaworks.algadelivery.delivery.tracking.api.model.CourierIdInput;
import com.algaworks.algadelivery.delivery.tracking.api.model.DeliveryInput;
import com.algaworks.algadelivery.delivery.tracking.domain.model.Delivery;
import com.algaworks.algadelivery.delivery.tracking.domain.repository.DeliveryRepository;
import com.algaworks.algadelivery.delivery.tracking.domain.service.DeliveryCheckpointService;
import com.algaworks.algadelivery.delivery.tracking.domain.service.DeliveryPreparationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;
import java.util.function.Supplier;

@RestController
@RequestMapping("/api/v1/deliveries")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryPreparationService deliveryPreparationService;
    private final DeliveryRepository deliveryRepository;
    private final DeliveryCheckpointService deliveryCheckpointService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Delivery draft(@RequestBody @Valid DeliveryInput deliveryInput){

        return deliveryPreparationService.draft(deliveryInput);
    }

    @PostMapping("/{deliveryId}/placement")
    public void place (@PathVariable UUID deliveryId){
        deliveryCheckpointService.place(deliveryId);
    }
    @PostMapping("/{deliveryId}/pickups")
    public void pickUp (@PathVariable UUID deliveryId, @Valid @RequestBody CourierIdInput input){
        deliveryCheckpointService.pickUp(deliveryId,input.getCourierId());

    }

    @PostMapping("/{deliveryId}/completion")
    public void complete(@PathVariable UUID deliveryId){
        deliveryCheckpointService.complement(deliveryId);
    }



    @PutMapping("/{deliveryId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Delivery edit(@PathVariable UUID deliveryId, @RequestBody @Valid DeliveryInput deliveryInput){
        return deliveryPreparationService.edit(deliveryId, deliveryInput);
    }

    @GetMapping
    public PagedModel<Delivery> findAll(@PageableDefault Pageable pageable){
        return new PagedModel<>(
                deliveryRepository.findAll(pageable)
        );
    }

    @GetMapping("/{deliveryId}")
    public Delivery findById(@PathVariable UUID deliveryId){
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(new Supplier<ResponseStatusException>() {
                    @Override
                    public ResponseStatusException get() {
                        return new ResponseStatusException(HttpStatus.NOT_FOUND);
                    }
                });
    }


}
