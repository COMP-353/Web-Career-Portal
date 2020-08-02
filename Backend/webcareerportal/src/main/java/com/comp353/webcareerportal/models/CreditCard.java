package com.comp353.webcareerportal.models;

import com.comp353.webcareerportal.dao.UserDao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CreditCard extends Payment{
    private String creditCardNumber;
    private String creditCardName;
    private int securityCode;
    private String billingAddress;


    public CreditCard(String creditCardName, String creditCardNumber, int securityCode, String billingAddress, boolean automaticWithdrawal, boolean defaultPayment){
        this.creditCardName = creditCardName;
        this.creditCardNumber = creditCardNumber;
        this.billingAddress = billingAddress;
        this.securityCode = securityCode;
        this.setAutomaticWithdrawal(automaticWithdrawal);
        this.setDefaultPayment(defaultPayment);

    }

    private void setEmployerFromId(Employer employer){
        this.setEmployer(employer);
    }

    private void getJobSeekerFromId(JobSeeker js){
        this.setJobSeeker(js);
    }
}
