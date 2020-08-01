package com.comp353.webcareerportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.comp353.webcareerportal.models.Application;
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
}
