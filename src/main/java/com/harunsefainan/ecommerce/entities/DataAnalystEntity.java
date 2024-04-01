package com.harunsefainan.ecommerce.entities;

import jakarta.persistence.*;

@Entity
@lombok.Data
@Table(name = "data_analyst")
public class DataAnalystEntity {
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

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "optime")
    private String optime;
}