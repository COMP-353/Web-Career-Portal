package com.comp353.webcareerportal.dao;

import com.comp353.webcareerportal.models.Application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import javax.transaction.Transactional;

public interface ApplicationDao extends JpaRepository<Application, Long> {

	@Query("select case when COUNT (a.applicationId) > 0 then true else false end from application a where a.applicationId = :application_id")
    boolean applicationExistsWithId(@Value("applicationId") int application_id);
	
	@Transactional
    @Modifying
    @Query("delete from application a where a.applicationId= :application_id")
    void deleteApplicationWithApplicationId(@Value("applicationId") int application_id);
}
