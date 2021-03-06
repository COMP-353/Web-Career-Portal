package com.comp353.webcareerportal.dao;

import com.comp353.webcareerportal.models.CheckingAccount;
import com.comp353.webcareerportal.models.CreditCard;
import com.comp353.webcareerportal.models.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface PaymentDao extends JpaRepository<Payment, Long> {

    @Query(nativeQuery = true, value = "select * from CheckingAccount ca where ca.employer = :id or ca.jobSeeker = :id")
    List<CheckingAccount> getAllCheckingAccountsFromUser(@Value("id")String id);

    @Query(nativeQuery = true, value = "select * from CreditCard cc where cc.employer = :id or cc.jobSeeker = :id")
    List<CreditCard> getAllCreditCardsFromUser(@Value("id")String id);

    @Query(nativeQuery = true, value = "select ca.employer from CheckingAccount ca group by ca.employer having (count(automaticWithdrawal = true and defaultPayment = true) = 1) and not null")
    List<String> getEmployersWithAutomaticPaymentsAndCheckingAccountAsDefault();

    @Query(nativeQuery = true, value = "select cc.employer from CreditCard cc group by cc.employer having (count(automaticWithdrawal = true and defaultPayment = true) = 1) and not null")
    List<String> getEmployersWithAutomaticPaymentsAndCreditCardAsDefault();

    @Query(nativeQuery = true, value = "select ca.jobseeker from CheckingAccount ca group by ca.jobseeker having (count(automaticWithdrawal = true and defaultPayment = true) = 1) and not null")
    List<String> getJobSeekersWithAutomaticPaymentsAndCheckingAccountAsDefault();

    @Query(nativeQuery = true, value = "select cc.jobseeker from CreditCard cc group by cc.jobseeker having (count(automaticWithdrawal = true and defaultPayment = true) = 1) and not null")
    List<String> getJobSeekersWithAutomaticPaymentsAndCreditCardsAsDefault();

    @Transactional
    @Modifying
    @Query("delete from CreditCard cc where cc.id = :id")
    void deleteCreditCardWithId(@Value("id") int id);

    @Transactional
    @Modifying
    @Query("delete from CheckingAccount ca where ca.id = :id")
    void deleteCheckingAccountWithId(@Value("id") int id);

    @Transactional
    @Modifying
    @Query("delete from CreditCard cc where cc.employer = :id or cc.jobSeeker = :id")
    void deleteAllCreditCardsForUserWithId(@Value("id") String id);

    @Transactional
    @Modifying
    @Query("delete from CheckingAccount ca where ca.employer = :id or ca.jobSeeker = :id")
    void deleteAllCheckingAccountsForUserWithId(@Value("id") String id);

    @Transactional
    @Modifying
    @Query("update CreditCard cc set cc.billingAddress = :add, cc.creditCardName = :name, cc.defaultPayment = :def, cc.automaticWithdrawal = :auto, cc.securityCode = :sec where cc.id = :id")
    void updateCreditCard(@Value("id") int id, @Value("auto") boolean auto,@Value("add") String add,@Value("name") String name, @Value("sec") int sec ,@Value("def") boolean def);

    @Transactional
    @Modifying
    @Query("update CheckingAccount cc set cc.accountNumber = :acc, cc.bankNumber = :name, cc.defaultPayment = :def, cc.automaticWithdrawal = :auto where cc.id = :id")
    void updateCheckingAccount(@Value("id") int id, @Value("auto") boolean auto,@Value("acc") String acc,@Value("name") String name ,@Value("def") boolean def);
}
