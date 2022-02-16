package com.readingisgood.getir.domain.model;

import com.readingisgood.getir.domain.model.enums.OrderStatus;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity(name = "order_entity")
@Table(name = "order_entity")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity extends BaseEntity {

    @Column
    private Long customerId;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.COMPLETED;

    @NotNull
    @OneToMany(cascade={CascadeType.ALL}, orphanRemoval = true)
    private List<OrderDetailEntity> orderDetailEntities;

    @NotNull
    private BigDecimal amount;
}
