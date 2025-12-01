package com.banco.demo.service;

import com.banco.demo.entity.Credit;
import com.banco.demo.entity.Client;
import com.banco.demo.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CreditService {

    private final CreditRepository creditRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    public CreditService(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    public Credit createCredit(Long clientId, Double amount, String category) {
        Client client = clientService.getClientById(clientId);

        Credit credit = new Credit();
        credit.setAmount(amount);
        credit.setCategory(category);
        credit.setStatus("pendiente");
        credit.setRequestDate(LocalDateTime.now());
        credit.setClient(client);

        String code = client.getName().substring(0, 3).toUpperCase() + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        return creditRepository.save(credit);
    }

    public Credit getCreditById(Long id) {
        return creditRepository.findById(id).orElseThrow(() -> new RuntimeException("Credit not found"));
    }

    public List<Credit> getCreditsByCategory(String category) {
        return creditRepository.findByCategory(category);
    }

    public Credit updateCreditStatus(Long id, String status) {
        Credit credit = getCreditById(id);
        credit.setStatus(status);
        if ("aprobado".equals(status)) {
            credit.setApprovalDate(LocalDateTime.now());
        }
        return creditRepository.save(credit);
    }
}