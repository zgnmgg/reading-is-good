package com.readingisgood.getir.domain.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "customer")
@Table(name = "customer")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity extends BaseEntity {

    @Column(unique = true)
    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String address;
}
