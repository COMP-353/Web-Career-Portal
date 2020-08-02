package com.comp353.webcareerportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comp353.webcareerportal.dao.JobCategoryDao;
import com.comp353.webcareerportal.models.JobCategory;

@Service
public class JobCategoryService {
	
	@Autowired
    private JobCategoryDao jobCategoryRepo;
	
	public boolean addNewJobCategory(JobCategory jobCategory) {
		if (jobCategoryRepo.jobCategoryExistsWithCategory(jobCategory.getCategory())) return false;
		jobCategoryRepo.save(jobCategory);
		return true;
	}
	
	public boolean deleteJobCategoryWithCategoryId(int id) {
		if(!jobCategoryRepo.jobCategoryExistsWithCategoryId(id)) return false;
		jobCategoryRepo.deleteJobCategoryWithCategoryId(id);
		return true;
	}
}