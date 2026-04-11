package com.imdat.specification;

import com.imdat.entity.Invoice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class InvoiceSpecification {
    public static Specification<Invoice> hasStatus(String status) {
        return (root, query, cb) -> {
            if (status == null || status.isEmpty()) {
                return cb.conjunction();
            }
            return cb.like(root.get("status"), "%" + status + "%");
        };
    }

    public static Specification<Invoice> hasNamePhoneReceiverAddress(String inputSearch) {
        return (root, query, cb) -> {
            if (inputSearch == null || inputSearch.isEmpty()) {
                return cb.conjunction();
            }

            return cb.or(
                    cb.like(root.get("receiverName"), "%" + inputSearch + "%"),
                    cb.like(root.get("receiverPhone"), "%" + inputSearch + "%"),
                    cb.like(root.get("address"), "%" + inputSearch + "%")
            );
        };
    }
}
