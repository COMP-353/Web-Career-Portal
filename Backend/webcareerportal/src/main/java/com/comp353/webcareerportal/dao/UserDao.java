package com.comp353.webcareerportal.dao;

import com.comp353.webcareerportal.models.EmployerCategory;
import com.comp353.webcareerportal.models.JobSeekerCategory;
import com.comp353.webcareerportal.models.User;
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

}

