package com.comp353.webcareerportal.service;

import com.comp353.webcareerportal.dao.*;
import com.comp353.webcareerportal.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobDao jobRepo;

    @Autowired
    private ApplicationDao applicationRepo;

    @Autowired
    private UserDao userRepo;

    @Autowired
    JobCategoryDao jobCategoryRepo;

    @Autowired
    JobStatusDao jobStatusRepo;


    public boolean addNewJob(Job job) {
        Employer e = (job.getEmployer());
        if (e == null) return false;
        String employerCategory = e.getEmployerCategory();
        if (employerCategory.equals("Prime")) {
            if (jobRepo.getJobCountByUser(e, job.getJobStatus()) < 5) {
                jobRepo.save(job);
            } else {
                return false;
            }
        } else {
            jobRepo.save(job);
        }
        return true;
    }

    public boolean deleteJobWithJobId(int id) {
        if (!jobRepo.jobExistsWithId(id)) return false;
        Job job = jobRepo.getJobWithJobId(id);
        applicationRepo.deleteApplicationWithJob(job);
        jobRepo.deleteJobWithJobId(id);
        return true;
    }

    public void deleteJobWithEmployerId(String id) {
        if (userRepo.employerExistsWithEmail(id)) {
            Employer employer = userRepo.getEmployerWithEmail(id);
            List<Integer> jobIds = jobRepo.getJobIdsWithEmployer(employer);

            for (Integer jobId : jobIds) {
                this.deleteJobWithJobId(jobId);
            }
        }
    }

    public List<Job> getAllJobsForEmployerWithId(String id) {
        if (!userRepo.employerExistsWithEmail(id)) return null;
        Employer employer = userRepo.getEmployerWithEmail(id);
        return jobRepo.getJobsWithEmployer(employer);
    }

    public List<Job> getAllJobs() {
        return jobRepo.getAllJobs();
    }

    public List<Job> getAllJobsForJobSeekerWithId(String id) {
        if (!userRepo.jobSeekerExistsWithEmail(id)) return null;

        JobSeeker jobSeeker = userRepo.getJobSeekerWithEmail(id);

        List<Application> applications = applicationRepo.getApplicationsWithJobSeeker(jobSeeker);

        if (applications.size() == 0) return null;

        List<Job> jobs = new ArrayList<>();

        for (Application application : applications) {
            jobs.add(jobRepo.getJobWithJobId(application.getJob().getJobId()));
        }
        return jobs;
    }

    public List<Job> getAllJobsNotAppliedForJobSeekerWithId(String id) {

        List<Job> jobs = getAllJobsForJobSeekerWithId(id);

        if (jobs == null) return jobRepo.getAllJobs();

        List<Integer> ids = new ArrayList<>();
        for (Job job : jobs) {
            ids.add(job.getJobId());
        }

        return jobRepo.getAllJobsWhereIdNotIn(ids);
    }

    public List<Job> getAllJobsForJobCategoryWithId(int id) {
        if (!jobCategoryRepo.jobCategoryExistsWithCategoryId(id)) return null;
        JobCategory jobCategory = jobCategoryRepo.getJobCategoryWithId(id);
        return jobRepo.getJobsWithjobCategory(jobCategory);
    }

    public List<Job> getAllJobsForJobStatusWithId(int id) {
        if (!jobStatusRepo.jobStatusExistsWithStatusId(id)) return null;
        JobStatus jobStatus = jobStatusRepo.getJobStatusWithId(id);
        return jobRepo.getJobsWithjobStatus(jobStatus);
    }

    public boolean updateJobStatus(int jobId, int statusId) {
        if (!jobRepo.jobExistsWithId(jobId)) return false;
        JobStatus jobStatus = jobStatusRepo.getJobStatusWithId(statusId);
        if (jobStatus == null) return false;
        jobRepo.updateJobStatus(jobId, jobStatus);
        return true;
    }
}
