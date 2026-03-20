package com.imdat.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer size;

    private String color;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "productVariant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "productVariant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<InvoiceDetail> invoiceDetails;

    public ProductVariant() {}
}
