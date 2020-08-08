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
	
	@GetMapping(path = "applied/jobseeker/{id}")
	public List<Job> getAllJobsByJobSeekerId(@PathVariable(name = "id") String id){
			return jobService.getAllJobsForJobSeekerWithId(id);
	}
	
	@GetMapping(path = "notapplied/jobseeker/{id}")
	public List<Job> getAllJobsNotAppliedByJobSeekerId(@PathVariable(name = "id") String id){
			return jobService.getAllJobsNotAppliedForJobSeekerWithId(id);
	}
	
	@GetMapping(path = "jobcategory/{id}")
	public List<Job> getAllJobsByJobCategoryId(@PathVariable(name = "id") int id){
			return jobService.getAllJobsForJobCategoryWithId(id);
	}

	@GetMapping(path = "jobstatus/{id}")
	public List<Job> getAllJobsByJobStatusId(@PathVariable(name = "id") int id){
			return jobService.getAllJobsForJobStatusWithId(id);
	}
	
    @PostMapping(path = "newJob/{id}")
    public String addNewJob(@RequestBody Job job, @PathVariable(name = "id") String eId) {
        return jobService.addNewJob(job, eId) ? "Job added successfully" : "Job not added. Invalid employer email.";
    }
    
    @GetMapping(path = "updateJobStatus/{id}/{statusId}")
    public String updateJobStatus(@PathVariable(name = "id") int id, @PathVariable(name = "statusId") int statusId) {
        return jobService.updateJobStatus(id, statusId) ? "Job updated successfully" : "An error occured";
    }
    
    @DeleteMapping(path = "deleteJob/{id}")
    public String deleteJob(@PathVariable(name = "id") int id){
        return jobService.deleteJobWithJobId(id) ? "Job deleted succesfully" : "Job with given JobId does not exist";
    }
}
