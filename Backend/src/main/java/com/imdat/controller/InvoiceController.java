package com.imdat.controller;

import com.imdat.DTO.require.InvoiceReq;
import com.imdat.DTO.respone.InvoiceRes;
import com.imdat.entity.Invoice;
import com.imdat.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    //Lấy hóa đơn theo Id (USER, ADMIN)
    @GetMapping("user/invoice/{id}")
    public InvoiceRes getInvoiceById(@PathVariable Integer id) {
        return invoiceService.getInvoiceById(id);
    }

    //Xóa hóa đơn(ADMIN)
    @DeleteMapping("admin/invoice/{id}")
    public void deleteInvoiceById(@PathVariable Integer id) {
        invoiceService.deleteInvoiceById(id);
    }

    //Thanh toán(USER)
    @PostMapping("user/invoice")
    public void addInvoice(@Valid @RequestBody InvoiceReq invoiceReq) {
        invoiceService.addInvoice(invoiceReq);
    }

    //Sửa hóa đơn theo Id(ADMIN)
    @PutMapping("admin/invoice/{id}")
    public void modifyInvoiceById(@PathVariable Integer id, @Valid @RequestBody InvoiceReq invoiceReq) {
        invoiceService.modifyInvoiceById(id, invoiceReq);
    }

    //Lọc, Tìm kiếm hóa đơn(ADMIN)
    @GetMapping("admin/invoice")
    public Page<InvoiceRes> getInvoice(
            @RequestParam(required = true) String inputSearch,
            @RequestParam(required = true) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return invoiceService.getInvoice(inputSearch, direction, sortBy, page, size, status);
    }

    //Lấy các hóa đơn của tài khoản(USER)
    @GetMapping("user/account_invoice")
    public List<InvoiceRes> getInvoiceByAccount() {
        return invoiceService.getInvoiceAccount();
    }
}
