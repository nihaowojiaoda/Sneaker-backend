package com.imdat.specification;

import com.imdat.entity.Account;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class AccountSpecification {
    public static Specification<Account> hasUsernameEmailPhoneNumber(String inputSearch) {

        if (inputSearch == null) return null;

        return (root, query, cb) ->
                cb.or(
                    cb.like(root.get("username"), "%" +inputSearch + "%"),
                    cb.like(root.get("email"), "%" + inputSearch + "%"),
                    cb.like(root.get("phoneNumber"), inputSearch)
                );
    }

    public static Specification<Account> hasRole (String role) {
        return (root, query, cb) ->
                role == null ? null : cb.like(root.get("role"), "%" + role + "%");
    }
}
