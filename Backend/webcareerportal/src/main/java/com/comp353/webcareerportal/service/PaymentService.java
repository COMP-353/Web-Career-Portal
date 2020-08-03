package com.comp353.webcareerportal.service;

import com.comp353.webcareerportal.dao.PaymentDao;
import com.comp353.webcareerportal.dao.UserDao;
import com.comp353.webcareerportal.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    PaymentDao paymentRepo;
    @Autowired
    UserDao userRepo;

    public boolean addCreditCard(String userId, CreditCard creditCard) {
        if (!setAppropriateUser(userId, creditCard)) return false;
        paymentRepo.save(creditCard);
        return true;
    }

    private boolean setAppropriateUser(String id, Payment payment) {
        boolean success = false;

        if (userRepo.employerExistsWithEmail(id)) {
            Employer e = userRepo.getEmployerWithEmail(id);
            if (e != null) payment.setEmployer(e);
            success = true;
        } else if (userRepo.jobSeekerExistsWithEmail(id)) {
            JobSeeker js = userRepo.getJobSeekerWithEmail(id);
            payment.setJobSeeker(js);
            success = true;
        }
        return success;
    }

    public boolean addCheckingAccount(String id, CheckingAccount checkingAccount) {
        if (!setAppropriateUser(id, checkingAccount)) return false;
        paymentRepo.save(checkingAccount);
        return true;
    }

    public List<CheckingAccount> getAllCheckingAccountsForUserWithId(String id) {
        return paymentRepo.getAllCheckingAccountsFromUser(id);
    }

    public List<CreditCard> getAllCreditCardsForUserWithId(String id) {
        return paymentRepo.getAllCreditCardsFromUser(id);
    }

    public void deleteCheckingAccountWithId(int id) {
        paymentRepo.deleteCheckingAccountWithId(id);
    }

    public void deleteCreditCardWithId(int id) {
        paymentRepo.deleteCheckingAccountWithId(id);
    }

    public void deleteAllPaymentsForUser(String id) {
        deleteAllPaymentsForUser(id);
    }

    //@Scheduled(cron = "[Seconds] [Minutes] [Hours] [Day of month] [Month] [Day of week] [Year]")
    @Scheduled(cron = "0 0 0 1 * ? ")
    private void getAllMyMoney() {
        makeEmployersPay();
        makeJobSeekersPay();
    }

    private void makeEmployersPay() {
        List<String> employers = paymentRepo.getEmployersWithAutomaticPaymentsAndCheckingAccountAsDefault();
        employers.addAll(paymentRepo.getEmployersWithAutomaticPaymentsAndCreditCardAsDefault());
        userRepo.chargeAllEmployersWithAutomaticPayments(employers);
    }

    private void makeJobSeekersPay() {
        List<String> js = paymentRepo.getEmployersWithAutomaticPaymentsAndCheckingAccountAsDefault();
        js.addAll(paymentRepo.getJobSeekersWithAutomaticPaymentsAndCreditCardsAsDefault());
        userRepo.chargeAllJobSeekersWithoutAutomaticPayments(js);
    }
}
