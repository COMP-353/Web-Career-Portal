package com.comp353.webcareerportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comp353.webcareerportal.models.JobStatus;
import com.comp353.webcareerportal.service.JobStatusService;

@RestController
@RequestMapping(path = "jobStatus/")
public class JobStatusController {
	
	@Autowired
    private JobStatusService jobStatusService;

    @PostMapping(path = "newJobStatus")
    public String addNewJobStatus(@RequestBody JobStatus jobStatus) {
        return jobStatusService.addNewJobStatus(jobStatus) ? "JobStatus added successfully" : "An error occured";
    }
    
    @DeleteMapping(path = "deleteJobStatus/{id}")
    public String deleteJobStatus(@PathVariable(name = "id") int id){
        return jobStatusService.deleteJobStatusWithStatusId(id) ? "Job deleted succesfully" : "Job with given JobId does not exist";
    }
}