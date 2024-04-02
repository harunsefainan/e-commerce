package com.harunsefainan.ecommerce.entities;

import com.harunsefainan.ecommerce.enums.UserRole;
import jakarta.persistence.*;

@Entity
@lombok.Data
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "role")
    private UserRole role;

    @Column(name = "optime")
    private String optime;

}
