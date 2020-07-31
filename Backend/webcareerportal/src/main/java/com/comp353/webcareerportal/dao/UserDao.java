package com.comp353.webcareerportal.dao;

import com.comp353.webcareerportal.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {

//    @Query("select distinct email from admin, employer, jobseeker ")
//    List<User> getAllUsers();
    @Query("select case when COUNT (e.email) > 0 then true else false end from employer e where e.email = :email")
    boolean employerExistsWithEmail(@Value("email") String email);

    @Query("select case when COUNT (js.email) > 0 then true else false end from jobseeker js where js.email = :email")
    boolean jobSeekerExistsWithEmail(@Value("email") String email);

    @Query("select case when COUNT (a.email) > 0 then true else false end from admin a where a.email = :email")
    boolean adminExistsWithEmail(@Value("email") String email);
}
