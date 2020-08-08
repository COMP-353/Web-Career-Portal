package com.comp353.webcareerportal.controller;

import com.comp353.webcareerportal.models.CheckingAccount;
import com.comp353.webcareerportal.models.CreditCard;
import com.comp353.webcareerportal.models.Payment;
import com.comp353.webcareerportal.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "payment/")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PostMapping(path = "newCheckingAccount/{id}")
    public boolean addCheckingAccount(@PathVariable(name = "id") String id, @RequestBody CheckingAccount checkingAccount) {
        return paymentService.addCheckingAccount(id, checkingAccount);
    }

    @PostMapping(path = "newCreditCard/{id}")
    public boolean addCreditCard(@PathVariable(name = "id") String id, @RequestBody CreditCard creditCard) {
        return paymentService.addCreditCard(id, creditCard);
    }

    @GetMapping(path = "checking/{id}")
    public List<CheckingAccount> getCheckingAccountsForUserWithId(@PathVariable(name = "id") String id) {
        return paymentService.getAllCheckingAccountsForUserWithId(id);
    }

    @GetMapping(path = "credit/{id}")
    public List<CreditCard> getCreditCardsForUserWithId(@PathVariable(name = "id") String id) {
        return paymentService.getAllCreditCardsForUserWithId(id);
    }

    @DeleteMapping(path = "checking/{id}")
    void deleteCheckingAccount(@PathVariable(name = "id") int id) {
        paymentService.deleteCheckingAccountWithId(id);
    }

    @DeleteMapping(path = "credit/{id}")
    void deleteCreditCard(@PathVariable(name = "id") int id) {
        paymentService.deleteCreditCardWithId(id);
    }

    @PutMapping(path = "updateCC")
    void updateCreditCard(@RequestBody CreditCard cc){
        paymentService.updateCreditCardInfo(cc);
    }

    @PutMapping(path = "updateCA")
    void updateCa(@RequestBody CheckingAccount ca){
        paymentService.updateCheckingAccountInfo(ca);
    }
}
