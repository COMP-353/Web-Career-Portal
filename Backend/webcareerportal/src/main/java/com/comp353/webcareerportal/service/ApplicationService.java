package com.comp353.webcareerportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.comp353.webcareerportal.models.Application;
import com.comp353.webcareerportal.models.Employer;
import com.comp353.webcareerportal.models.Job;
import com.comp353.webcareerportal.models.JobSeeker;
import com.comp353.webcareerportal.dao.ApplicationDao;
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
	
	public boolean addNewApplication(Application application) {
		if (!userRepo.jobSeekerExistsWithEmail(application.getJobseeker().getEmail())
				|| !jobRepo.jobExistsWithId(application.getJob().getJobId())) return false;
		applicationRepo.save(application);
		return true;
	}
	
	public boolean deleteApplicationWithApplicationId(int id) {
		if(!applicationRepo.applicationExistsWithId(id)) return false;
		applicationRepo.deleteApplicationWithApplicationId(id);
		return true;
	}
	
	public void deleteApplicationWithJobSeekerId(String id) {
		JobSeeker jobSeeker = userRepo.getJobSeekerWithEmail(id);
		List<Integer> applicationIds = applicationRepo.getApplicationIdsWithJobSeeker(jobSeeker);
		
		for(Integer applicationId : applicationIds) {
			this.deleteApplicationWithApplicationId(applicationId);
		}
	}
	
	public List<Application> getAllApplications(){
		return applicationRepo.getAllApplications();
	}
}
