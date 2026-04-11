package com.imdat.service;

import com.imdat.DTO.InvoiceDetailDTO;
import com.imdat.entity.*;
import com.imdat.repository.AccountInterface;
import com.imdat.repository.CartInterface;
import com.imdat.repository.InvoiceInterface;
import com.imdat.specification.InvoiceSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceInterface invoiceInterface;

    @Autowired
    private AccountInterface accountInterface;

    @Autowired
    private CartInterface cartInterface;

    @Autowired
    private InvoiceSpecification invoiceSpecification;

    public Invoice getInvoiceById(Integer id) {
        Invoice invoice = invoiceInterface.findById(id).orElseThrow(() -> new RuntimeException("Invoice not found"));
        return invoice;
    }

    public void deleteInvoiceById(Integer id) {
        invoiceInterface.deleteById(id);
    }

    @Transactional
    public void addInvoice(InvoiceDetailDTO invoiceDetailDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Account account = accountInterface.findByUsername(username).orElseThrow(() -> new RuntimeException("Account not found"));
        Cart cart = cartInterface.findByAccount(account).orElseThrow(() -> new RuntimeException("Cart not found"));

        List<CartItem> cartItems = cart.getCartItems();

        if (cartItems.isEmpty()) {
            return;
        }

        Invoice invoice = new Invoice();
        Double totalPrice = 0.0;

        for (Integer i = 0; i < cartItems.size(); i++) {
            Double amountPrice = (cartItems.get(i).getProductVariant().getPrice() * cartItems.get(i).getQuantity());
            totalPrice += amountPrice;
            InvoiceDetail invoiceDetail = new InvoiceDetail(amountPrice, cartItems.get(i).getQuantity());
            invoiceDetail.setProductVariant(cartItems.get(i).getProductVariant());
            invoiceDetail.setInvoice(invoice);
            invoice.setInvoiceDetails(invoiceDetail);

            cartItems.get(i).getProductVariant().setStock(cartItems.get(i).getProductVariant().getStock() - cartItems.get(i).getQuantity());
        }

        invoice.setTotalPrice(totalPrice);
        invoice.setStatus(invoiceDetailDTO.getStatus());
        invoice.setPaymentMethod(invoiceDetailDTO.getPaymentMethod());
        invoice.setShippingAddress(invoiceDetailDTO.getShippingAddress());
        invoice.setReceiverPhone(invoiceDetailDTO.getReceivePhone());
        invoice.setReceiverName(invoiceDetailDTO.getReceiveName());
        invoice.setAccount(account);

        cart.emptyCartItem();

        account.setInvoices(invoice);

        invoiceInterface.save(invoice);
    }

    @Transactional
    public void modifyInvoiceById(Integer invoiceId, InvoiceDetailDTO invoiceDetailDTO) {
        Invoice invoice = invoiceInterface.findById(invoiceId).orElseThrow(() -> new RuntimeException("Invoice not found"));

        if (invoiceDetailDTO.getStatus() != null) invoice.setStatus(invoiceDetailDTO.getStatus());
        if (invoiceDetailDTO.getPaymentMethod() != null) invoice.setStatus(invoiceDetailDTO.getPaymentMethod());
        if (invoiceDetailDTO.getShippingAddress() != null) invoice.setStatus(invoiceDetailDTO.getShippingAddress());
        if (invoiceDetailDTO.getReceiveName() != null) invoice.setStatus(invoiceDetailDTO.getReceiveName());
        if (invoiceDetailDTO.getReceivePhone() != null) invoice.setStatus(invoiceDetailDTO.getReceivePhone());
    }

    public Page<Invoice> getInvoice(String inputSearch, String direction, String sortBy, Integer page, Integer size, String status) {
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Invoice> spec = Specification.where(InvoiceSpecification.hasStatus(status).and(InvoiceSpecification.hasNamePhoneReceiverAddress(inputSearch)));

        return invoiceInterface.findAll(spec, pageable);
    }

    @Transactional
    public List<Invoice> getInvoiceAccount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Account account = accountInterface.findByUsername(username).orElseThrow(() -> new RuntimeException("Account not found"));

        System.out.println("Số lượng invoiceeeeeeeeeeeeee: " + invoiceInterface.findByAccount(account).size());
        return invoiceInterface.findByAccount(account);
    }
}
