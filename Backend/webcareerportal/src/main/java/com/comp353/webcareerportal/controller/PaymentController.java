package com.comp353.webcareerportal.controller;

import com.comp353.webcareerportal.models.CheckingAccount;
import com.comp353.webcareerportal.models.CreditCard;
import com.comp353.webcareerportal.models.Payment;
import com.comp353.webcareerportal.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "payment/")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PostMapping(path = "newCheckingAccount/{id}")
    public boolean addCheckingAccount(@PathVariable(name = "id") String id, @RequestBody CheckingAccount checkingAccount){
        return paymentService.addCheckingAccount(id, checkingAccount);
    }

    @PostMapping(path = "newCreditCard/{id}")
    public boolean addCreditCard(@PathVariable(name = "id") String id,@RequestBody CreditCard creditCard){
        return paymentService.addCreditCard(id, creditCard);
    }

    @GetMapping(path = "checking/{id}")
    public List <CheckingAccount> getCheckingAccountsForUserWithId(@PathVariable(name = "id") String id){
        return paymentService.getAllCheckingAccountsForUserWithId(id);
    }

    @GetMapping(path = "credit/{id}")
    public List <CreditCard> getCreditCardsForUserWithId(@PathVariable(name = "id") String id){
        return paymentService.getAllCreditCardsForUserWithId(id);
    }
}
