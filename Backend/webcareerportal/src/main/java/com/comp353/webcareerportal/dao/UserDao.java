package com.comp353.webcareerportal.dao;

import com.comp353.webcareerportal.models.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {

    // Checking users Exists
    @Query("select case when COUNT (e.email) > 0 then true else false end from employer e where e.email = :email")
    boolean employerExistsWithEmail(@Value("email") String email);

    @Query("select case when COUNT (js.email) > 0 then true else false end from jobseeker js where js.email = :email")
    boolean jobSeekerExistsWithEmail(@Value("email") String email);


    @Query("select case when COUNT (a.email) > 0 then true else false end from admin a where a.email = :email")
    boolean adminExistsWithEmail(@Value("email") String email);


    @Deprecated
    @Query(nativeQuery = true, value = "select case when count(email) > 0 then true else false end from ( (select e.email from employer e where e.email = :email) union (select js.email from jobseeker js where js.email = :email) union (select a.email from admin a where a.email = :email)) as eeje")
    BigInteger userExistsWithEmail(@Value("email") String email);

    // Authenticating users
    @Query("select case when e.password = :password then true else false end from employer e where e.email = :email")
    boolean authenticateEmployerWithEmail(@Value("email") String email, @Value("password") String password);

    @Query("select case when a.password = :password then true else false end from admin a where a.email = :email")
    boolean authenticateAdminWithEmail(@Value("email") String email, @Value("password") String password);

    @Query("select case when js.password = :password then true else false end from jobseeker js where js.email = :email")
    boolean authenticateJobSeekerWithEmail(@Value("email") String email, @Value("password") String password);

    // Updating user categories
    @Transactional
    @Modifying
    @Query("update jobseeker js set js.jobSeekerCategory = :category where js.email = :email")
    void updateJobSeekerCategoryWithEmail(@Value("email") String email, @Value("category") JobSeekerCategory category);

    @Transactional
    @Modifying
    @Query("update employer e set e.employerCategory = :category where e.email = :email")
    void updateEmployerCategoryWithEmail(@Value("email") String email, @Value("category") EmployerCategory category);

    // Updating user passwords
    @Transactional
    @Modifying
    @Query("update jobseeker js set js.password = :password where js.email = :email")
    void updateJobSeekerPasswordWithEmail(@Value("email") String email, @Value("password") String password);

    @Transactional
    @Modifying
    @Query("update employer e set e.password = :password where e.email = :email")
    void updateEmployerPasswordWithEmail(@Value("email") String email, @Value("password") String password);

    @Transactional
    @Modifying
    @Query("update admin a set a.password = :password where a.email = :email")
    void updateAdminPasswordWithEmail(@Value("email") String email, @Value("password") String password);

    // Deleting users
    @Transactional
    @Modifying
    @Query("delete from jobseeker js where js.email = :email")
    void deleteJobSeekerWithEmail(@Value("email") String email);

    @Transactional
    @Modifying
    @Query("delete from employer e where e.email = :email")
    void deleteEmployerWithEmail(@Value("email") String email);

    @Transactional
    @Modifying
    @Query("delete from admin a where a.email = :email")
    void deleteAdminWithEmail(@Value("email") String email);

    // Retrieving Users
    @Query(nativeQuery = true, value = "select * from jobseeker js where js.email = :email")
    JobSeeker getJobSeekerWithEmail(@Value("email") String email);

    @Query(nativeQuery = true, value = "select * from employer e where e.email = :email")
    Employer getEmployerWithEmail(@Value("email") String email);

    // Changing Status of Users
    @Modifying
    @Transactional
    @Query("update jobseeker js set js.status = 'Active' where js.email = :id")
    void activateJobSeekerWithEmail(@Value("id") String id);

    @Modifying
    @Transactional
    @Query("update employer e set e.status = 'Active' where e.email = :id")
    void activateEmployerWithEmail(@Value("id") String id);

    @Modifying
    @Transactional
    @Query("update admin a set a.status = 'Active' where a.email = :id")
    void activateAdminWithEmail(@Value("id") String id);

    @Modifying
    @Transactional
    @Query("update jobseeker js set js.status = 'Inactive' where js.email = :id")
    void deactivateJobSeekerWithEmail(@Value("id") String id);

    @Modifying
    @Transactional
    @Query("update employer e set e.status = 'Inactive' where e.email = :id")
    void deactivateEmployerWithEmail(@Value("id") String id);

    @Modifying
    @Transactional
    @Query("update admin a set a.status = 'Inactive' where a.email = :id")
    void deactivateAdminWithEmail(@Value("id") String id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update employer e set e.accountBalance = (case when (e.employerCategory = 1) then e.accountBalance = 50 + e.accountBalance else e.accountBalance = e.accountBalance + 100 end) where e.email not in :ids")
    void chargeAllEmployersWithAutomaticPayments(@Value("ids") List<String> ids);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update jobseeker js set js.accountBalance = (case when (js.jobSeekerCategory = 1) then js.accountBalance = 10 + js.accountBalance when (js.jobSeekerCategory = 2) then js.accountBalance = 20 + js.accountBalance end) where js.email not in :ids")
    void chargeAllJobSeekersWithoutAutomaticPayments(@Value("ids") List<String> ids);
}

