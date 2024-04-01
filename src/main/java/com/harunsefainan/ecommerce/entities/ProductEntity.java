package com.harunsefainan.ecommerce.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@lombok.Data
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private SellerEntity seller;

    @Column(name = "productName")
    private String productName;

    @Column(name = "category")
    private String category;

    @Column(name = "stockQuantity")
    private int stockQuantity;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "optime")
    private String optime;

}
