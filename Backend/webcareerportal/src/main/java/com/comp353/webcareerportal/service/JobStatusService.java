package com.comp353.webcareerportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.comp353.webcareerportal.dao.JobStatusDao;
import com.comp353.webcareerportal.models.JobStatus;
import org.springframework.stereotype.Service;

@Service
public class JobStatusService {
	
	@Autowired
    private JobStatusDao jobStatusRepo;
	
	public boolean addNewJobStatus(JobStatus jobStatus) {
		if (jobStatusRepo.jobStatusExistsWithStatus(jobStatus.getStatus())) return false;
		jobStatusRepo.save(jobStatus);
		return true;
	}
	
	public boolean deleteJobStatusWithStatusId(int id) {
		if(!jobStatusRepo.jobStatusExistsWithStatusId(id)) return false;
		jobStatusRepo.deleteJobStatusWithStatusId(id);
		return true;
	}
	
	public List<JobStatus> getAllJobStatuses(){
		return jobStatusRepo.getAllJobStatuses();
	}
}
