package com.comp353.webcareerportal.controller;

import com.comp353.webcareerportal.models.Job;
import com.comp353.webcareerportal.service.JobService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "job/")
public class JobController {
	
	@Autowired
    private JobService jobService;
	
	@GetMapping(path = "employer/{id}")
	public List<Job> getAllJobsByEmployerId(@PathVariable(name = "id") String id){
			return jobService.getAllJobsForEmployerWithId(id);
	}
	
	@GetMapping(path = "all")
	public List<Job> getAllJobs(){
			return jobService.getAllJobs();
	}

    @PostMapping(path = "newJob")
    public String addNewJob(@RequestBody Job job) {
        return jobService.addNewJob(job) ? "Job added successfully" : "Job not added. Invalid employer email.";
    }
    
    @DeleteMapping(path = "deleteJob/{id}")
    public String deleteJob(@PathVariable(name = "id") int id){
        return jobService.deleteJobWithJobId(id) ? "Job deleted succesfully" : "Job with given JobId does not exist";
    }
}
