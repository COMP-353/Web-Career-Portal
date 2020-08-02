package com.comp353.webcareerportal.service;

import com.comp353.webcareerportal.dao.PaymentDao;
import com.comp353.webcareerportal.dao.UserDao;
import com.comp353.webcareerportal.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
