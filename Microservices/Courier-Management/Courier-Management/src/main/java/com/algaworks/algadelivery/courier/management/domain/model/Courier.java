package com.algaworks.algadelivery.courier.management.domain.model;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Courier {

    @EqualsAndHashCode.Include
    private UUID id;

    @Setter(AccessLevel.PUBLIC)
    private String name;

    @Setter(AccessLevel.PUBLIC)
    private String phone;

    //quantidade de entregas concluídas.
    private Integer fulfilledDeliviriesQuantity;

    //quantidade de entregas pendentes.
    private Integer pendingDeliveriesQuantity;

    //data/hora da última entrega concluída.
    private OffsetDateTime lastFulfilledDeliveryAt;

    //lista de entregas atribuídas mas ainda não concluídas.
    private List<AssignedDelivery> pendingDeliveries = new ArrayList<>();

    public  List<AssignedDelivery> getPendingDeliveries() {
        return Collections.unmodifiableList(this.pendingDeliveries);
    }

    //factorie method - garante que todo Courier criado esteja válido.
    public static Courier brandNew(String name, String phone){

        Courier courier = new Courier();
        courier.setId(UUID.randomUUID());
        courier.setName(name);
        courier.setPhone(phone);
        courier.setPendingDeliveriesQuantity(0);
        courier.setFulfilledDeliviriesQuantity(0);
        return courier;
    }

    //Atribui uma nova entrega ao entregador.
    //Cria um AssignedDelivery.pending(deliveryId).
    //Adiciona na lista de pendentes.
    //Incrementa o contador de pendentes.
    public void assign(UUID deliveryId){
        this.pendingDeliveries.add(
                AssignedDelivery.pending(deliveryId)
        );
        this.pendingDeliveriesQuantity++;

    }

    public void fullfill(UUID deliveryId){
        AssignedDelivery delivery = this.pendingDeliveries.stream().filter(
                d -> d.getId().equals(deliveryId)
        ).findFirst().orElseThrow();
        this.pendingDeliveries.remove(delivery);

        this.pendingDeliveriesQuantity--;
        this.fulfilledDeliviriesQuantity++;
        this.lastFulfilledDeliveryAt = OffsetDateTime.now();
    }
}
