package com.comp353.webcareerportal.service;

import com.comp353.webcareerportal.dao.ActivityDao;
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
    @Autowired
    ActivityDao activityDao;

    private final String ADDED_CC = "ADDED A CREDIT CARD TO THEIR ACCOUNT";
    private final String ADDED_CA = "ADDED A CHECKING TO THEIR ACCOUNT";
    private final String REMOVED_CC = "REMOVED A CREDIT FROM THEIR ACCOUNT";
    private final String REMOVED_CA = "REMOVED A CREDIT FROM THEIR ACCOUNT";
    private final String CHARGED = "PAYMENT MADE AUTOMATICALLY";

    public boolean addCreditCard(String userId, CreditCard creditCard) {
        if (!setAppropriateUser(userId, creditCard)) return false;
        paymentRepo.save(creditCard);
        activityDao.save(new Activity(userId, ADDED_CC));
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
        activityDao.save(new Activity(id, ADDED_CA));
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
        paymentRepo.deleteCreditCardWithId(id);
    }

    public void deleteAllPaymentsForUser(String id) {
        deleteAllPaymentsForUser(id);
        activityDao.save(new Activity(id, "REMOVED ALL THEIR PAYMENT METHODS"));
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
        employers.forEach(e -> activityDao.save(new Activity(e, CHARGED)));
    }

    private void makeJobSeekersPay() {
        List<String> js = paymentRepo.getEmployersWithAutomaticPaymentsAndCheckingAccountAsDefault();
        js.addAll(paymentRepo.getJobSeekersWithAutomaticPaymentsAndCreditCardsAsDefault());
        userRepo.chargeAllJobSeekersWithoutAutomaticPayments(js);
        js.forEach(j -> activityDao.save(new Activity(j, CHARGED)));
    }

    public void updateCreditCardInfo(CreditCard cc){
        paymentRepo.updateCreditCard(cc.getId(),cc.isAutomaticWithdrawal(),cc.getBillingAddress(), cc.getCreditCardName(),cc.getSecurityCode(),cc.isDefaultPayment());
    }

    public void updateCheckingAccountInfo(CheckingAccount ca){
        paymentRepo.updateCheckingAccount(ca.getId(),ca.isAutomaticWithdrawal(),ca.getAccountNumber(),ca.getBankNumber(),ca.isDefaultPayment());
    }
}
