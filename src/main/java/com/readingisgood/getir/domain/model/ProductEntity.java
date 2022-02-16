package com.readingisgood.getir.domain.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "product")
@Table(name = "product")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity extends BaseEntity {
    private String name;

    @Column
    private String author;

    @Column
    private Long stock;

    @Column
    private BigDecimal price;

    @Column
    private String publishYear;

}
