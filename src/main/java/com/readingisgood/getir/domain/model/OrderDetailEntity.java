package com.readingisgood.getir.domain.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "order_detail")
@Table(name = "order_detail")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class OrderDetailEntity extends BaseEntity  {

    @Column
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_entity_id")
    private OrderEntity entity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;
}
