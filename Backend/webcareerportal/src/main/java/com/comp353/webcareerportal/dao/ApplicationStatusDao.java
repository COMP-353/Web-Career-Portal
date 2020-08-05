package com.comp353.webcareerportal.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.comp353.webcareerportal.models.ApplicationStatus;
import com.comp353.webcareerportal.models.JobStatus;

public interface ApplicationStatusDao extends JpaRepository<ApplicationStatus, Long> {
	
	@Query("select case when COUNT (a.status) > 0 then true else false end from ApplicationStatus a where a.status = :status")
    boolean applicationStatusExistsWithStatus(@Value("status") String status);
	
	@Query(nativeQuery = true, value = "select * from applicationStatus a where a.statusId= :status_id")
	ApplicationStatus getApplicationStatusWithId(@Value("statusId") int status_id);

}
