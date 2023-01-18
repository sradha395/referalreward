package com.assignment.retailerreward.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer", uniqueConstraints = {
        @UniqueConstraint(name = "unique_customer_email", columnNames = "email"),
        @UniqueConstraint(name = "unique_customer_mobilenumber", columnNames = "mobile_number")})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "mobile_number")
    private String mobileNumber;
    @Column(name = "email")
    private String email;

}
