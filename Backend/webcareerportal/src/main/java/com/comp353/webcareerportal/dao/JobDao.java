package com.comp353.webcareerportal.dao;

import com.comp353.webcareerportal.models.Job;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface JobDao extends JpaRepository<Job, Long> {
	@Query("select case when COUNT (j.jobId) > 0 then true else false end from job j where j.jobId = :job_id")
    boolean jobExistsWithId(@Value("jobId") int job_id);
	
	@Transactional
    @Modifying
    @Query("delete from job j where j.jobId= :job_id")
    void deleteJobWithJobId(@Value("jobId") int job_id);
	
	@Query(nativeQuery = true, value = "select * from job j where j.jobId= :job_id")
	Job getJobWithJobId(@Value("jobId") int job_id);
}
