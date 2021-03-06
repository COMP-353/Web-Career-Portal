package com.comp353.webcareerportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comp353.webcareerportal.models.JobCategory;
import com.comp353.webcareerportal.models.JobStatus;
import com.comp353.webcareerportal.service.JobCategoryService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "jobCategory/")
public class JobCategoryController {
	
	@Autowired
    private JobCategoryService jobCategoryService;

    @PostMapping(path = "newJobCategory")
    public String addNewJobCategory(@RequestBody JobCategory jobCategory ) {
        return jobCategoryService.addNewJobCategory (jobCategory ) ? "JobCategory added successfully" : "An error occured";
    }
    
    @DeleteMapping(path = "deleteJobCategory/{id}")
    public String deleteJobCategory(@PathVariable(name = "id") int id){
        return jobCategoryService.deleteJobCategoryWithCategoryId(id) ? "JobCategory deleted succesfully" : "JobCategory with given categoryId does not exist";
    }
    
    @GetMapping(path = "all")
  	public List<JobCategory> getAllJobCategories(){
  			return jobCategoryService.getAllJobCategories();
  	}
}
