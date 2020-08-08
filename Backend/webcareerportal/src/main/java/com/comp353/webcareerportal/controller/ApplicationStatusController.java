package com.comp353.webcareerportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comp353.webcareerportal.models.ApplicationStatus;
import com.comp353.webcareerportal.models.JobStatus;
import com.comp353.webcareerportal.service.ApplicationStatusService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "applicationStatus/")
public class ApplicationStatusController {
	
	@Autowired
    private ApplicationStatusService applicationStatusService;

    @PostMapping(path = "newApplicationStatus")
    public String addNewApplicationStatus(@RequestBody ApplicationStatus applicationStatus) {
        return applicationStatusService.addNewApplicationStatus(applicationStatus) ? "ApplicationStatus added successfully" : "An error occured";
    }
    
    @GetMapping(path = "all")
	public List<ApplicationStatus> getAllApplicationStatuses(){
			return applicationStatusService.getAllApplicationStatuses();
	}
}