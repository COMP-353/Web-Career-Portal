package com.comp353.webcareerportal.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comp353.webcareerportal.models.Application;
import com.comp353.webcareerportal.models.Job;
import com.comp353.webcareerportal.service.ApplicationService;

@RestController
@RequestMapping(path = "application/")
public class ApplicationController {
	@Autowired
    private ApplicationService applicationService;

	@GetMapping(path = "all")
	public List<Application> getAllApplications(){
			return applicationService.getAllApplications();
	}
	
	@GetMapping(path = "jobseeker/{id}")
	public List<Application> getAllApplicationsByJobSeekerId(@PathVariable(name = "id") String id){
		return applicationService.getAllApplicationsForJobSeekerWithId(id);
	}
	
	@GetMapping(path = "applicationstatus/{id}")
	public List<Application> getAllJobsByJobStatusId(@PathVariable(name = "id") int id){
			return applicationService.getAllapplicationForapplicationStatusWithId(id);
	}
	
	
	@GetMapping(path = "job/{id}")
	public List<Application> getAllApplicationsByJobId(@PathVariable(name = "id") int id){
		return applicationService.getAllApplicationsForJobWithId(id);
	}
	
    @PostMapping(path = "newApplication")
    public String addNewApplication(@RequestBody Application application) {
        return applicationService.addNewApplication(application) ? "Application added successfully" : "Application not added. Invalid job id or invalid jobseeker email.";
    }
    
    @DeleteMapping(path = "deleteApplication/{id}")
    public String deleteApplication(@PathVariable(name = "id") int id){
        return applicationService.deleteApplicationWithApplicationId(id) ? "Application deleted succesfully" : "Application with given ApplicationId does not exist";
    }
}
