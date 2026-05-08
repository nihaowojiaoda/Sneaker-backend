package com.imdat.service;

import com.imdat.DTO.require.InvoiceReq;
import com.imdat.DTO.respone.InvoiceRes;
import com.imdat.convert.Convert;
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

    @Autowired
    private Convert convert;

    public InvoiceRes getInvoiceById(Integer id) {
        Invoice invoice = invoiceInterface.findById(id).orElseThrow(() -> new RuntimeException("Invoice not found"));
        return convert.convertInvoice(invoice);
    }

    public void deleteInvoiceById(Integer id) {
        invoiceInterface.deleteById(id);
    }

    @Transactional
    public void addInvoice(InvoiceReq invoiceReq) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Account account = accountInterface.findByUsername(username).orElseThrow(() -> new RuntimeException("Account not found"));
        Cart cart = cartInterface.findByAccount(account).orElseThrow(() -> new RuntimeException("Cart not found"));

        List<CartItem> cartItems = cart.getCartItems();

        if (cartItems.isEmpty()) {
            return;
        }

        Invoice invoice = new Invoice();
        Long totalPrice = new Long(0);

        for (Integer i = 0; i < cartItems.size(); i++) {
            Long amountPrice = cartItems.get(i).getProductVariant().getPrice() * cartItems.get(i).getQuantity();
            totalPrice += amountPrice;
            InvoiceDetail invoiceDetail = new InvoiceDetail(amountPrice, cartItems.get(i).getQuantity());
            invoiceDetail.setProductVariant(cartItems.get(i).getProductVariant());
            invoiceDetail.setInvoice(invoice);
            invoice.setInvoiceDetails(invoiceDetail);

            cartItems.get(i).getProductVariant().setStock(cartItems.get(i).getProductVariant().getStock() - cartItems.get(i).getQuantity());
        }

        invoice.setTotalPrice(totalPrice);
        invoice.setStatus(invoiceReq.getStatus());
        invoice.setPaymentMethod(invoiceReq.getPaymentMethod());
        invoice.setShippingAddress(invoiceReq.getShippingAddress());
        invoice.setReceiverPhone(invoiceReq.getReceivePhone());
        invoice.setReceiverName(invoiceReq.getReceiveName());
        invoice.setAccount(account);

        cart.getCartItems().clear();
        account.setInvoices(invoice);
        invoiceInterface.save(invoice);
    }

    @Transactional
    public void modifyInvoiceById(Integer invoiceId, InvoiceReq invoiceReq) {
        Invoice invoice = invoiceInterface.findById(invoiceId).orElseThrow(() -> new RuntimeException("Invoice not found"));

        if (invoiceReq.getStatus() != null) invoice.setStatus(invoiceReq.getStatus());
        if (invoiceReq.getPaymentMethod() != null) invoice.setPaymentMethod(invoiceReq.getPaymentMethod());
        if (invoiceReq.getShippingAddress() != null) invoice.setShippingAddress(invoiceReq.getShippingAddress());
        if (invoiceReq.getReceiveName() != null) invoice.setReceiverName(invoiceReq.getReceiveName());
        if (invoiceReq.getReceivePhone() != null) invoice.setReceiverPhone(invoiceReq.getReceivePhone());
    }

    public Page<InvoiceRes> getInvoice(String inputSearch, String direction, String sortBy, Integer page, Integer size, String status) {
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Invoice> spec = Specification.where(InvoiceSpecification.hasStatus(status).and(InvoiceSpecification.hasNamePhoneReceiverAddress(inputSearch)));

        Page<Invoice> invoicePage = invoiceInterface.findAll(spec, pageable);
        return invoicePage.map(convert::convertInvoice);
    }

    @Transactional
    public List<InvoiceRes> getInvoiceAccount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Account account = accountInterface.findByUsername(username).orElseThrow(() -> new RuntimeException("Account not found"));

        List<Invoice> invoiceList = invoiceInterface.findByAccount(account);
        return invoiceList.stream().map(convert::convertInvoice).toList();
    }
}
