package com.harunsefainan.ecommerce.entities;
import jakarta.persistence.*;

@Entity
@lombok.Data
@Table(name = "e_commerce")
public class SellerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String storeName;
    private String storeDescription;

}
