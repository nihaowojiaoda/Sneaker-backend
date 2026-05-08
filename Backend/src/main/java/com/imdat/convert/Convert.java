package com.imdat.convert;

import com.imdat.DTO.respone.*;
import com.imdat.entity.*;
import org.springframework.stereotype.Component;

@Component
public class Convert {
    public ProductRes convertProduct(Product product) {
        return new ProductRes(
                product.getId(),
                product.getProductName(),
                product.getDescription(),
                new BrandRes(
                        product.getBrand().getId(),
                        product.getBrand().getBrandName()
                ),
                new CategoryRes(
                        product.getCategory().getId(),
                        product.getCategory().getCategoryName()
                ),
                product.getProductVariants().stream().map(this::convertProductVariant).toList(),
                product.getImages().stream().map(this::convertImage).toList()
        );
    }

    public ProductVariantRes convertProductVariant(ProductVariant productVariant) {
        return new ProductVariantRes(
                productVariant.getId(),
                productVariant.getColor(),
                productVariant.getSize(),
                productVariant.getActive(),
                productVariant.getStock(),
                productVariant.getImportPrice(),
                productVariant.getPrice()
        );
    }

    public ImageRes convertImage(Image image) {
        return new ImageRes(
                image.getId(),
                image.getUrl(),
                image.getPublicId()
        );
    }

    public InvoiceDetailRes convertInvoiceDetail(InvoiceDetail invoiceDetail) {
        return new InvoiceDetailRes(
                invoiceDetail.getId(),
                invoiceDetail.getAmountPrice(),
                invoiceDetail.getQuantity(),
                convertProductVariant(invoiceDetail.getProductVariant())
        );
    }

    public InvoiceRes convertInvoice(Invoice invoice) {
        return new InvoiceRes(
                invoice.getId(),
                invoice.getTotalPrice(),
                invoice.getStatus(),
                invoice.getPaymentMethod(),
                invoice.getShippingAddress(),
                invoice.getReceiverPhone(),
                invoice.getReceiverName(),
                invoice.getCreatedAt(),
                invoice.getInvoiceDetails().stream().map(this::convertInvoiceDetail).toList()
        );
    }

    public AdminUserDetailRes convertAccount(Account account) {
        return new AdminUserDetailRes(
                account.getId(),
                account.getUsername(),
                account.getPhoneNumber(),
                account.getAddress(),
                account.getEmail(),
                account.getRole(),
                account.getCreatedAt(),
                account.getUpdatedAt(),
                account.isActive()
        );
    }

    public CartItemRes convertCartItem(CartItem cartItem) {
        return new CartItemRes(
                cartItem.getId(),
                cartItem.getQuantity(),
                convertProductVariant(cartItem.getProductVariant())
        );
    }

    public CartRes convertCart(Cart cart) {
        return new CartRes(
                cart.getId(),
                cart.getCartItems().stream().map(this::convertCartItem).toList()
        );
    }
}
