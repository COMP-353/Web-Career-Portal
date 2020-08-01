package com.comp353.webcareerportal.dao;

import com.comp353.webcareerportal.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {

    @Deprecated
    @Query("select case when COUNT (e.email) > 0 then true else false end from employer e where e.email = :email")
    boolean employerExistsWithEmail(@Value("email") String email);

    @Deprecated
    @Query("select case when COUNT (js.email) > 0 then true else false end from jobseeker js where js.email = :email")
    boolean jobSeekerExistsWithEmail(@Value("email") String email);

    @Deprecated
    @Query("select case when COUNT (a.email) > 0 then true else false end from admin a where a.email = :email")
    boolean adminExistsWithEmail(@Value("email") String email);

    @Query ("select case when count(e.email) > 0 then true else false end from employer e where e.email in (select e.email from employer e where e.email = :email) or e.email in (select js.email from jobseeker js where js.email = :email) or e.email in (select a.email from admin a where a.email = :email)")
    boolean userExistsWithEmail(@Value("email") String email);

    @Query("select case when e.password = :password then true else false end from employer e where e.email in (select e.email from employer e where e.email = :email) or e.email in (select js.email from jobseeker js where js.email = :email)or e.email in (select a.email from admin a where a.email = :email)")
    boolean authenticateUserWithEmail(@Value("email") String email, @Value("password") String password);
}
