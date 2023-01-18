package com.assignment.retailerreward.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "fk_customer_id"))
    private Customer customer;

    @Column(name = "total_price")
    private Float totalPrice;


}
