package com.imdat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotNull;

@Entity
public class InvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Double amountPrice;

    @NotNull
    private Integer quantity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "product_variant_id")
    private ProductVariant productVariant;

    public InvoiceDetail(Double amountPrice, Integer quantity) {
        this.amountPrice = amountPrice;
        this.quantity = quantity;
    }

    public InvoiceDetail() {
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getAmountPrice() {
        return amountPrice;
    }

    public void setAmountPrice(Double amountPrice) {
        this.amountPrice = amountPrice;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Integer getId() {
        return id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public ProductVariant getProductVariant() {
        return productVariant;
    }

    public void setProductVariant(ProductVariant productVariant) {
        this.productVariant = productVariant;
    }
}
