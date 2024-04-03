package com.harunsefainan.ecommerce.entities;

import com.harunsefainan.ecommerce.enums.UserRole;
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

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;

    @Column(name = "optime")
    private String optime;

}
