package com.comp353.webcareerportal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comp353.webcareerportal.models.Application;
import com.comp353.webcareerportal.models.Employer;
import com.comp353.webcareerportal.models.Job;
import com.comp353.webcareerportal.models.JobCategory;
import com.comp353.webcareerportal.models.JobSeeker;
import com.comp353.webcareerportal.models.JobStatus;
import com.comp353.webcareerportal.dao.ApplicationDao;
import com.comp353.webcareerportal.dao.JobCategoryDao;
import com.comp353.webcareerportal.dao.JobDao;
import com.comp353.webcareerportal.dao.JobStatusDao;
import com.comp353.webcareerportal.dao.UserDao;

@Service
public class JobService {
	@Autowired
    private JobDao jobRepo;
	
	@Autowired
    private ApplicationDao applicationRepo;
	
	@Autowired
    private UserDao userRepo;

	@Autowired
	JobCategoryDao jobCategoryRepo;
	
	@Autowired
	JobStatusDao jobStatusRepo;

	
	public boolean addNewJob(Job job) {
		if (!userRepo.employerExistsWithEmail(job.getEmployer().getEmail())) return false;
		jobRepo.save(job);
		return true;
	}
	
	public boolean deleteJobWithJobId(int id) {
		if(!jobRepo.jobExistsWithId(id)) return false;
		Job job = jobRepo.getJobWithJobId(id);
		applicationRepo.deleteApplicationWithJob(job);
		jobRepo.deleteJobWithJobId(id);
		return true;
	}
	
	public void deleteJobWithEmployerId(String id) {
		Employer employer = userRepo.getEmployerWithEmail(id);
		List<Integer> jobIds = jobRepo.getJobIdsWithEmployer(employer);
		
		for(Integer jobId : jobIds) {
			this.deleteJobWithJobId(jobId);
		}
	}
	
	public List<Job> getAllJobsForEmployerWithId(String id){
		Employer employer = userRepo.getEmployerWithEmail(id);
		return jobRepo.getJobsWithEmployer(employer);
	}
	
	public List<Job> getAllJobs(){
		return jobRepo.getAllJobs();
	}
	
	public List<Job> getAllJobsForJobSeekerWithId(String id){
		JobSeeker jobSeeker = userRepo.getJobSeekerWithEmail(id);
		List<Application> applications = applicationRepo.getApplicationsWithJobSeeker(jobSeeker);
		
		List<Job> jobs = new ArrayList<>();
		
		for(Application application : applications) {
			jobs.add(jobRepo.getJobWithJobId(application.getJob().getJobId()));
		}
		return jobs;
	}
	
	public List<Job> getAllJobsForJobCategoryWithId(int id){
		JobCategory jobCategory = jobCategoryRepo.getJobCategoryWithId(id);
		return jobRepo.getJobsWithjobCategory(jobCategory);
	}
	
	public List<Job> getAllJobsForJobStatusWithId(int id){
		JobStatus jobStatus = jobStatusRepo.getJobStatusWithId(id);
		return jobRepo.getJobsWithjobStatus(jobStatus);
	}
}
