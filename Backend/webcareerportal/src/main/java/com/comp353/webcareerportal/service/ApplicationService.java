package com.comp353.webcareerportal.service;

import com.comp353.webcareerportal.dao.*;
import com.comp353.webcareerportal.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationDao applicationRepo;

    @Autowired
    private UserDao userRepo;

    @Autowired
    private JobDao jobRepo;

    @Autowired
    private ApplicationStatusDao applicationStatusRepo;

    @Autowired
    private ActivityDao activityDao;

    public boolean addNewApplication(Application application) {
        if (!userRepo.jobSeekerExistsWithEmail(application.getJobseeker().getEmail())
                || !jobRepo.jobExistsWithId(application.getJob().getJobId())) return false;

        JobSeeker js = application.getJobseeker();

        if (js.getJobSeekerCategory().equals("Prime")) {
            if (applicationRepo.getNumberOfApplicationForAJobSeeker(js, application.getApplicationStatus()) < 5) {
                activityDao.save(new Activity(application.getJobseeker().getEmail(), "APPLIED TO A JOB"));
                applicationRepo.save(application);
            } else {
                return false;
            }
        } else {
            //Is GOLD
            activityDao.save(new Activity(application.getJobseeker().getEmail(), "APPLIED TO A JOB"));
            applicationRepo.save(application);
        }
        return true;
    }

    public boolean deleteApplicationWithApplicationId(int id) {
        if (!applicationRepo.applicationExistsWithId(id)) return false;
        applicationRepo.deleteApplicationWithApplicationId(id);
        activityDao.save(new Activity(null, "APPLICATION DELETED"));
        return true;
    }

    public void deleteApplicationWithJobSeekerId(String id) {
        if (userRepo.jobSeekerExistsWithEmail(id)) {
            JobSeeker jobSeeker = userRepo.getJobSeekerWithEmail(id);
            List<Integer> applicationIds = applicationRepo.getApplicationIdsWithJobSeeker(jobSeeker);

            for (Integer applicationId : applicationIds) {
                this.deleteApplicationWithApplicationId(applicationId);
            }
        }
    }

    public List<Application> getAllApplications() {
        return applicationRepo.getAllApplications();
    }

    public List<Application> getAllApplicationsForJobSeekerWithId(String id) {
        if (!userRepo.jobSeekerExistsWithEmail(id)) return null;
        JobSeeker jobSeeker = userRepo.getJobSeekerWithEmail(id);
        return applicationRepo.getApplicationsWithJobSeeker(jobSeeker);
    }

    public List<Application> getAllApplicationsForJobWithId(int id) {
        if (!jobRepo.jobExistsWithId(id)) return null;
        Job job = jobRepo.getJobWithJobId(id);
        return applicationRepo.getApplicationsWithJob(job);
    }

    public List<Application> getAllapplicationForapplicationStatusWithId(int id) {
        ApplicationStatus applicationStatus = applicationStatusRepo.getApplicationStatusWithId(id);
        if (applicationStatus == null) return null;
        return applicationRepo.getApplicationsWithApplicationStatus(applicationStatus);
    }

    public boolean updateApplicationStatus(int applicationId, int statusId) {
        if (!applicationRepo.applicationExistsWithId(applicationId)) return false;
        ApplicationStatus applicationStatus = applicationStatusRepo.getApplicationStatusWithId(statusId);
        if (applicationStatus == null) return false;
        applicationRepo.updateApplicationStatus(applicationId, applicationStatus);
        return true;
    }

    public List<Application> getAllApplicationsForEmployerWithId(String id, int statusId) {
        if (!userRepo.employerExistsWithEmail(id)) return null;
        List<Integer> jobIds = jobRepo.getJobIdsWithEmployer(userRepo.getEmployerWithEmail(id));

        List<Application> applications = new ArrayList<>();

        for (int jobId : jobIds) {
            Job job = jobRepo.getJobWithJobId(jobId);
            List<Application> applicationsForJob = applicationRepo.getApplicationsWithJob(job);

            for (Application application : applicationsForJob) {
                if (application.getApplicationStatus().getStatusId() == statusId) {
                    applications.add(application);
                }
            }
        }

        return applications;
    }
}
