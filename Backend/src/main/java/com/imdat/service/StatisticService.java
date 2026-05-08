package com.imdat.service;

import com.imdat.DTO.respone.InvoiceRes;
import com.imdat.DTO.respone.ProductVariantRes;
import com.imdat.DTO.respone.StatisticRes;
import com.imdat.convert.Convert;
import com.imdat.entity.Invoice;
import com.imdat.entity.ProductVariant;
import com.imdat.repository.AccountInterface;
import com.imdat.repository.InvoiceInterface;
import com.imdat.repository.ProductVariantInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticService {
    @Autowired
    private InvoiceInterface invoiceInterface;

    @Autowired
    private AccountInterface accountInterface;

    @Autowired
    private ProductVariantInterface productVariantInterface;

    @Autowired
    private Convert convert;

    public List<ProductVariantRes> getLowStockProduct() {
        Pageable top3 = PageRequest.of(0, 3);

        List<ProductVariant> products =
                productVariantInterface.getLowStockProducts(top3);

        return products.stream().map(convert::convertProductVariant).toList();
    }

    public List<InvoiceRes> getRecentInvoice() {
        Pageable top4 = PageRequest.of(0, 4);

        List<Invoice> invoices =
                invoiceInterface.getRecentOrders(top4);

        return invoices.stream().map(convert::convertInvoice).toList();
    }

    public StatisticRes getStatistic() {
        return new StatisticRes(
                invoiceInterface.getTotalRevenue(),
                invoiceInterface.countNewOrders(),
                accountInterface.countCustomers(),
                getRecentInvoice(),
                getLowStockProduct()
        );
    }
}
