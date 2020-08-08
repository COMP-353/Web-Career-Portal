package com.comp353.webcareerportal.service;

import com.comp353.webcareerportal.dao.ActivityDao;
import com.comp353.webcareerportal.models.Activity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.comp353.webcareerportal.models.Application;
import com.comp353.webcareerportal.models.ApplicationStatus;
import com.comp353.webcareerportal.models.Employer;
import com.comp353.webcareerportal.models.Job;
import com.comp353.webcareerportal.models.JobSeeker;
import com.comp353.webcareerportal.models.JobStatus;
import com.comp353.webcareerportal.dao.ApplicationDao;
import com.comp353.webcareerportal.dao.ApplicationStatusDao;
import com.comp353.webcareerportal.dao.UserDao;
import com.comp353.webcareerportal.dao.JobDao;

@Service
public class ApplicationService {
	@Autowired
    private ApplicationDao applicationRepo;
	
	@Autowired
    private UserDao userRepo;
	
	@Autowired
    private JobDao jobRepo;
	
	@Autowired
    private ApplicationStatusDao applicationStatusRepo;

	@Autowired
	private ActivityDao activityDao;
	
	public boolean addNewApplication(Application application) {
		if (!userRepo.jobSeekerExistsWithEmail(application.getJobseeker().getEmail())
				|| !jobRepo.jobExistsWithId(application.getJob().getJobId())) return false;

		applicationRepo.save(application);
		activityDao.save(new Activity(application.getJobseeker().getEmail(), "APPLIED TO A JOB"));
		return true;
	}
	
	public boolean deleteApplicationWithApplicationId(int id) {
		if(!applicationRepo.applicationExistsWithId(id)) return false;
		applicationRepo.deleteApplicationWithApplicationId(id);
		activityDao.save(new Activity(null, "APPLICATION DELETED"));
		return true;
	}
	
	public void deleteApplicationWithJobSeekerId(String id) {
		if(userRepo.jobSeekerExistsWithEmail(id)) {
			JobSeeker jobSeeker = userRepo.getJobSeekerWithEmail(id);
			List<Integer> applicationIds = applicationRepo.getApplicationIdsWithJobSeeker(jobSeeker);
			
			for(Integer applicationId : applicationIds) {
				this.deleteApplicationWithApplicationId(applicationId);
			}
		}
	}
	
	public List<Application> getAllApplications(){
		return applicationRepo.getAllApplications();
	}
	
	public List<Application> getAllApplicationsForJobSeekerWithId(String id){
		if(!userRepo.jobSeekerExistsWithEmail(id)) return null;
		JobSeeker jobSeeker = userRepo.getJobSeekerWithEmail(id);
		return applicationRepo.getApplicationsWithJobSeeker(jobSeeker);
	}
	
	public List<Application> getAllApplicationsForJobWithId(int id){
		if(!jobRepo.jobExistsWithId(id)) return null;
		Job job = jobRepo.getJobWithJobId(id);
		return applicationRepo.getApplicationsWithJob(job);
	}
	
	public List<Application> getAllapplicationForapplicationStatusWithId(int id){
		ApplicationStatus applicationStatus = applicationStatusRepo.getApplicationStatusWithId(id);
		if(applicationStatus == null) return null;
		return applicationRepo.getApplicationsWithApplicationStatus(applicationStatus);
	}
	
	public boolean updateApplicationStatus(int applicationId, int statusId) {
		if(!applicationRepo.applicationExistsWithId(applicationId)) return false;
		ApplicationStatus applicationStatus = applicationStatusRepo.getApplicationStatusWithId(statusId);
		if(applicationStatus == null) return false;
		applicationRepo.updateApplicationStatus(applicationId, applicationStatus);
		return true;
	}
	
	public List<Application> getAllApplicationsForEmployerWithId(String id){
		if(!userRepo.employerExistsWithEmail(id)) return null;
		List<Integer> jobIds = jobRepo.getJobIdsWithEmployer(userRepo.getEmployerWithEmail(id));
		
		List<Application> applications = new ArrayList<>();
		
		for(int jobId : jobIds) {
			Job job = jobRepo.getJobWithJobId(jobId);
			List<Application> applicationsForJob = applicationRepo.getApplicationsWithJob(job);
			
			for(Application application : applicationsForJob ) {
				applications.add(application);
			}
		}
		
		return applications;
	}
}
