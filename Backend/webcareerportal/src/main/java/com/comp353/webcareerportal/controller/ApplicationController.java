package com.comp353.webcareerportal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comp353.webcareerportal.models.Application;
import com.comp353.webcareerportal.service.ApplicationService;

@RestController
@RequestMapping(path = "application/")
public class ApplicationController {
	@Autowired
    private ApplicationService applicationService;

    @PostMapping(path = "newApplication")
    public String addNewApplication(@RequestBody Application application) {
        return applicationService.addNewApplication(application) ? "Application added successfully" : "Application not added. Invalid job id or invalid jobseeker email.";
    }
    
    @DeleteMapping(path = "deleteApplication/{id}")
    public String deleteApplication(@PathVariable(name = "id") int id){
        return applicationService.deleteApplicationWithApplicationId(id) ? "Application deleted succesfully" : "Application with given ApplicationId does not exist";
    }
}
