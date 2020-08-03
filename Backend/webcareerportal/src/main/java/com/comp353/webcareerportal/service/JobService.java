package com.comp353.webcareerportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comp353.webcareerportal.models.Employer;
import com.comp353.webcareerportal.models.Job;
import com.comp353.webcareerportal.dao.ApplicationDao;
import com.comp353.webcareerportal.dao.JobDao;
import com.comp353.webcareerportal.dao.UserDao;

@Service
public class JobService {
	@Autowired
    private JobDao jobRepo;
	
	@Autowired
    private ApplicationDao applicationRepo;
	
	@Autowired
    private UserDao userRepo;
	
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
		List<Job> jobs = jobRepo.getJobWithEmployer(employer);
		
		for(Job job : jobs) {
			this.deleteJobWithJobId(job.getJobId());
		}
	}
}
