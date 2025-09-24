package com.algaworks.algadelivery.delivery.tracking.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter(AccessLevel.PRIVATE)
@Getter
public class Item {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;
    private String name;
    @Setter(AccessLevel.PACKAGE)
    private Integer quantity;

    /*
    @ManyToOne(optional = false)
    Diz que todo Item pertence a uma Delivery obrigatoriamente.
    O optional = false faz com que a coluna delivery_id no banco seja NOT NULL.
    Isso reforça a regra de negócio: um Item não existe sozinho, só dentro de uma Delivery.
     */
    @ManyToOne(optional = false)
    /*Torna o getter de delivery privado.
    Isso impede que código de fora acesse livremente a Delivery de um Item
    Só o próprio domínio (métodos da Delivery e do Item) têm acesso.
    👉 Isso é alinhado ao encapsulamento do DDD. */
    @Getter(AccessLevel.PRIVATE)
    private Delivery delivery;

    /*É um método de fábrica estático, que cria um Item de forma consistente.
    Ele já garante:
    Criação do UUID.
    Nome e quantidade preenchidos.
    Associação com a Delivery.
    Assim, ninguém pode criar um Item inválido (sem delivery). */
    static Item brandNew(String name, Integer quantity, Delivery delivery){
        Item item = new Item();
        item.setId(UUID.randomUUID());
        item.setName(name);
        item.setQuantity(quantity);
        item.setDelivery(delivery);
        return item;
    }


}
