package com.comp353.webcareerportal.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CheckingAccount extends Payment {
    private String bankNumber;
    private String accountNumber;

    public CheckingAccount(String bankNumber, String accountNumber, boolean automaticWithdrawal, boolean defaultPayment) {
        this.bankNumber = bankNumber;
        this.accountNumber = accountNumber;
        this.setAutomaticWithdrawal(automaticWithdrawal);
        this.setDefaultPayment(defaultPayment);
    }

    public void setEmployer(Employer e) {
        super.setEmployer(e);
    }

    public void setJobSeeker(JobSeeker js) {
        super.setJobSeeker(js);
    }
}
