package com.banco.demo.controller;

import com.banco.demo.entity.Credit;
import com.banco.demo.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CreditController {

    private final CreditService creditService;

    @Autowired
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @QueryMapping
    public Credit getCredit(@Argument Long id) {
        return creditService.getCreditById(id);
    }

    @QueryMapping
    public List<Credit> getCreditsByCategory(@Argument String category) {
        return creditService.getCreditsByCategory(category);
    }

    @MutationMapping
    public Credit createCredit(@Argument Long clientId, @Argument Double amount, @Argument String category) {
        return creditService.createCredit(clientId, amount, category);
    }

    @MutationMapping
    public Credit updateCreditStatus(@Argument Long id, @Argument String status) {
        return creditService.updateCreditStatus(id, status);
    }
}