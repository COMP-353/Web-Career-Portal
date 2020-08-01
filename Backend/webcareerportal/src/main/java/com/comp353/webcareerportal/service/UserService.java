package com.comp353.webcareerportal.service;

import com.comp353.webcareerportal.dao.UserDao;
import com.comp353.webcareerportal.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userRepo;

    public boolean addNewAdmin(Admin admin) {
        if (userRepo.adminExistsWithEmail(admin.getEmail())) return false;
        userRepo.save(admin);
        return true;
    }

    public boolean addNewJobSeeker(JobSeeker jobSeeker) {
        if (userRepo.jobSeekerExistsWithEmail(jobSeeker.getEmail())) return false;
        userRepo.save(jobSeeker);
        return true;
    }

    public boolean addNewEmployer(Employer employer) {
        if (userRepo.employerExistsWithEmail(employer.getEmail())) return false;
        userRepo.save(employer);
        return true;
    }

    public boolean authenticateUser(String id, String password) {
        boolean success = false;

        if (userRepo.employerExistsWithEmail(id)){
            success = userRepo.authenticateEmployerWithEmail(id, password);
        }else if (userRepo.jobSeekerExistsWithEmail(id)){
            success = userRepo.authenticateJobSeekerWithEmail(id, password);
        }else if (userRepo.adminExistsWithEmail(id)){
            success = userRepo.authenticateAdminWithEmail(id, password);
        }

        return success;
    }

    public boolean updateJobSeekerCategory(String id, String category) {
        if (userRepo.jobSeekerExistsWithEmail(id)) return false;
        userRepo.updateJobSeekerCategoryWithEmail(id, getJobSeekerCategoryFrom(category));
        return true;
    }

    public boolean updateEmployerCategory(String id, String category) {
        if (userRepo.employerExistsWithEmail(id)) return false;
        userRepo.updateEmployerCategoryWithEmail(id, getEmployerCategoryFrom(category));
        return false;
    }

    private JobSeekerCategory getJobSeekerCategoryFrom(String category) {
        if (category.equals("Gold") || category.equals("gold")) return JobSeekerCategory.GOLD;
        else if (category.equals("Prime") || category.contains("prime")) return JobSeekerCategory.PRIME;
        else return JobSeekerCategory.BASIC;
    }

    private EmployerCategory getEmployerCategoryFrom(String category) {
        if (category.equals("Gold") || category.equals("gold")) return EmployerCategory.GOLD;
        else return EmployerCategory.PRIME;
    }

}
