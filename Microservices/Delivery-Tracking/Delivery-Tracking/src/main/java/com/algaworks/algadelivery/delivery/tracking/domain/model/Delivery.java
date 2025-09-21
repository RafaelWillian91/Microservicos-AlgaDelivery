package com.algaworks.algadelivery.delivery.tracking.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter(AccessLevel.PRIVATE)
@Getter
@ToString
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
    public static Delivery draft() {
        Delivery delivery = new Delivery();

        delivery.setId(UUID.randomUUID());
        delivery.setDeliveryStatus(DeliveryStatus.DRAFT);
        delivery.setTotalCost(BigDecimal.ZERO);
        delivery.setTotalItems(0);
        delivery.setDistanceFee(BigDecimal.ZERO);
        delivery.setCourierCost(BigDecimal.ZERO);

        return delivery;
    }

    //Apenas o AggregateRoot (Delivery (Aggregate Root)) controla a criação e inclusão do Item.
    public UUID addItem(String name, int quantity){
        Item item = Item.brandNew(name, quantity);
        items.add(item);
        calculateTotalItems();
        return item.getId();
    }

    public void removeItem(UUID uuid){
        items.removeIf(item -> item.getId().equals(uuid));
        calculateTotalItems();
    }

    public void removeItems(){
        items.clear();
        calculateTotalItems();
    }

    public void changeItemQuantity(UUID itemId, int quantity){
        Item item = getItems().stream().filter(i -> i.getId().equals(itemId))
                .findFirst().orElseThrow();

        item.setQuantity(quantity);
        calculateTotalItems();
    }

    //Apenas o AggregateRoot pode modificar a lista de items
    public List<Item> getItems(){

        return Collections.unmodifiableList(this.items);
    }

    //A invariante é:
    //O atributo totalItems deve sempre refletir a soma das quantidades dos Items da entrega.
    //calculateTotalItems() é o guardião da invariante.
    //Os métodos addItem, removeItem, removeItems chamam ele sempre → garantindo consistência
    private void calculateTotalItems(){
        int totalItems = getItems().stream().mapToInt(Item::getQuantity).sum();
        setTotalItems(totalItems);
    }

}
