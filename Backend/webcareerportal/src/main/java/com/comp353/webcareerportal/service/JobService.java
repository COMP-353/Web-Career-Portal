package com.comp353.webcareerportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.comp353.webcareerportal.models.Job;
import com.comp353.webcareerportal.dao.JobDao;
import com.comp353.webcareerportal.dao.UserDao;

@Service
public class JobService {
	@Autowired
    private JobDao jobRepo;
	
	@Autowired
    private UserDao userRepo;
	
	public boolean addNewJob(Job job) {
		if (!userRepo.employerExistsWithEmail(job.getEmployer().getEmail())) return false;
		jobRepo.save(job);
		return true;
	}
}
