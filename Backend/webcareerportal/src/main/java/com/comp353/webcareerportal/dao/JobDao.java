package com.comp353.webcareerportal.dao;

import com.comp353.webcareerportal.models.Employer;
import com.comp353.webcareerportal.models.Job;
import com.comp353.webcareerportal.models.JobCategory;
import com.comp353.webcareerportal.models.JobStatus;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

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
	
	@Query(nativeQuery = true, value = "select * from job")
    List<Job> getAllJobs();
	
	@Query(nativeQuery = true, value = "select j.jobId from job j where j.employer_email= :employer group by j.jobId")
    List<Integer> getJobIdsWithEmployer(@Value("employer") Employer employer);
	
    @Query(nativeQuery = true, value = "select * from job j where j.employer_email= :employer group by j.jobId")
    List<Job> getJobsWithEmployer(@Value("employer") Employer employer);
    
    @Query(nativeQuery = true, value = "select * from job j where j.job_category= :jobCategory group by j.jobId")
    List<Job> getJobsWithjobCategory(@Value("job_category") JobCategory jobCategory);
    
    @Query(nativeQuery = true, value = "select * from job j where j.job_status= :jobStatus group by j.jobId")
    List<Job> getJobsWithjobStatus(@Value("job_status") JobStatus jobStatus);
    
    @Query(nativeQuery = true, value = "select * from job j where j.jobId not in ?1")
    List<Job> getAllJobsWhereIdNotIn(@Value("ids") Collection<Integer> ids);
}
