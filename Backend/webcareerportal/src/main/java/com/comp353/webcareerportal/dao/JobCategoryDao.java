package com.comp353.webcareerportal.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.comp353.webcareerportal.models.Job;
import com.comp353.webcareerportal.models.JobCategory;
import com.comp353.webcareerportal.models.JobStatus;

public interface JobCategoryDao extends JpaRepository<JobCategory, Long> {
	
	@Query("select case when COUNT (j.categoryId) > 0 then true else false end from JobCategory j where j.categoryId = :category_id")
    boolean jobCategoryExistsWithCategoryId(@Value("categoryId") int category_id);
	
	@Query("select case when COUNT (j.category) > 0 then true else false end from JobCategory j where j.category = :category")
    boolean jobCategoryExistsWithCategory(@Value("category") String category);
	
	@Transactional
    @Modifying
    @Query("delete from JobCategory j where j.categoryId= :category_id")
    void deleteJobCategoryWithCategoryId(@Value("categoryId") int category_id);
	
	@Query(nativeQuery = true, value = "select * from jobCategory j where j.categoryId= :category_id")
	JobCategory getJobCategoryWithId(@Value("categoryId") int category_id);
	
	@Query(nativeQuery = true, value = "select * from jobCategory")
	List<JobCategory> getAllJobCategories();
}
