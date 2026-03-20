package com.imdat.entity;

import jakarta.persistence.*;

@Entity
public class InvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double amountPrice;

    @ManyToOne
    @JoinColumn(name = "product_variant_id")
    private ProductVariant productVariant;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    public InvoiceDetail() {}
}
