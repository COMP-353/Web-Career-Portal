package com.comp353.webcareerportal.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.comp353.webcareerportal.models.JobCategory;
import com.comp353.webcareerportal.models.JobStatus;

public interface JobStatusDao extends JpaRepository<JobStatus, Long> {
	
	@Query("select case when COUNT (j.statusId) > 0 then true else false end from JobStatus j where j.statusId = :status_id")
    boolean jobStatusExistsWithStatusId(@Value("statusId") int status_id);
	
	@Query("select case when COUNT (j.status) > 0 then true else false end from JobStatus j where j.status = :status")
    boolean jobStatusExistsWithStatus(@Value("status") String status);
	
	@Transactional
    @Modifying
    @Query("delete from JobStatus j where j.statusId= :status_id")
    void deleteJobStatusWithStatusId(@Value("statusId") int status_id);
	
	@Query(nativeQuery = true, value = "select * from jobStatus j where j.statusId= :status_id")
	JobStatus getJobStatusWithId(@Value("statusId") int status_id);
}
