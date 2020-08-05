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
	
	@Query(nativeQuery = true, value = "select * from applicationStatus a where a.statusId= :status_id")
	ApplicationStatus getApplicationStatusWithId(@Value("statusId") int status_id);

}
