package com.comp353.webcareerportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comp353.webcareerportal.dao.ApplicationStatusDao;
import com.comp353.webcareerportal.dao.JobStatusDao;
import com.comp353.webcareerportal.models.ApplicationStatus;
import com.comp353.webcareerportal.models.JobStatus;

@Service
public class ApplicationStatusService {
	
	@Autowired
    private ApplicationStatusDao applicationStatusRepo;
	
	public boolean addNewApplicationStatus(ApplicationStatus applicationStatus) {
		if (applicationStatusRepo.applicationStatusExistsWithStatus(applicationStatus.getStatus())) return false;
		applicationStatusRepo.save(applicationStatus);
		return true;
	}
	
	public List<ApplicationStatus> getAllApplicationStatuses(){
		return applicationStatusRepo.getAllApplicationStatuses();
	}

}
