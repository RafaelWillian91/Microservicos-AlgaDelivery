package com.algaworks.algadelivery.delivery.tracking.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter(AccessLevel.PRIVATE)
@Getter
public class Delivery {

    @EqualsAndHashCode.Include
    private UUID id;
    private UUID courierId;

    private DeliveryStatus deliveryStatus;

    private OffsetDateTime placedAt;
    private OffsetDateTime assignedAt;
    private OffsetDateTime expectedDeliveryAt;
    private OffsetDateTime fulfilledAt;


    private BigDecimal distanceFee;
    private BigDecimal totalCost;

    private BigDecimal courierCost;
    private Integer totalItems;

    private ContactPoint sender;

    private ContactPoint recipient;
    private List<Item> items = new ArrayList<>();

    /*Cria uma entrega em estado inicial (DRAFT)
Isso é uma fábrica estática para garantir que o agregado comece em um estado válido.
        Cria uma entrega em estado inicial (DRAFT)
        Define valores numéricos zerados
        Gera um UUID automaticamente
Isso é uma fábrica estática para garantir que o agregado comece em um estado válido.
     */

    //Garante que apenas o Aggregate Root pode modificar a lista de itens
    //getItems() com Collections.unmodifiableList impede mutações externas.
    public static Delivery draft(){
        Delivery delivery = new Delivery();
        delivery.setId(UUID.randomUUID());
        delivery.setDeliveryStatus(DeliveryStatus.DRAFT);
        delivery.setTotalCost(BigDecimal.ZERO);
        delivery.setTotalItems(0);
        delivery.setDistanceFee(BigDecimal.ZERO);
        delivery.setCourierCost(BigDecimal.ZERO);

        return delivery;
    }

    ///Apenas o AgreatRoot pode modificar a lista de items
    public List<Item> getItems(){
        return Collections.unmodifiableList(this.items);
    }

}
