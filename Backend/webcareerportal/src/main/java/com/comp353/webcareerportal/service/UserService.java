package com.comp353.webcareerportal.service;

import com.comp353.webcareerportal.dao.ApplicationDao;
import com.comp353.webcareerportal.service.JobService;
import com.comp353.webcareerportal.dao.UserDao;
import com.comp353.webcareerportal.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userRepo;
    
    @Autowired
    private ApplicationDao applicationRepo;
    
    @Autowired
    private JobService jobService;

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

        if (userRepo.employerExistsWithEmail(id)) {
            success = userRepo.authenticateEmployerWithEmail(id, password);
        } else if (userRepo.jobSeekerExistsWithEmail(id)) {
            success = userRepo.authenticateJobSeekerWithEmail(id, password);
        } else if (userRepo.adminExistsWithEmail(id)) {
            success = userRepo.authenticateAdminWithEmail(id, password);
        }

        return success;
    }

    public boolean updateUserPassword(String id, String newPassword) {
        boolean updatedPassword = false;

        if (userRepo.employerExistsWithEmail(id)) {
            userRepo.updateEmployerPasswordWithEmail(id, newPassword);
            updatedPassword = true;
        } else if (userRepo.jobSeekerExistsWithEmail(id)) {
            userRepo.updateJobSeekerPasswordWithEmail(id, newPassword);
            updatedPassword = true;
        } else if (userRepo.adminExistsWithEmail(id)) {
            userRepo.authenticateAdminWithEmail(id, newPassword);
            updatedPassword = true;
        }

        return updatedPassword;
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

    public boolean deleteUserWithEmail(String id) {
        boolean deleted = false;

        if (userRepo.employerExistsWithEmail(id)) {
        	jobService.deleteJobWithEmployerId(id);
            userRepo.deleteEmployerWithEmail(id);
            deleted = true;
        } else if (userRepo.jobSeekerExistsWithEmail(id)) {
        	JobSeeker jobSeeker = userRepo.getJobSeekerWithEmail(id);
        	applicationRepo.deleteApplicationWithJobSeeker(jobSeeker);
            userRepo.deleteJobSeekerWithEmail(id);
            deleted = true;
        } else if (userRepo.adminExistsWithEmail(id)) {
            userRepo.deleteAdminWithEmail(id);
            deleted = true;
        }
        return deleted;
    }

    public boolean activateUserWithId(String id) {
        boolean activated = false;

        if (userRepo.jobSeekerExistsWithEmail(id)) {
            userRepo.activateJobSeekerWithEmail(id);
            activated = true;
        } else if (userRepo.employerExistsWithEmail(id)) {
            userRepo.activateEmployerWithEmail(id);
            activated = true;
        } else if (userRepo.adminExistsWithEmail(id)) {
            userRepo.activateAdminWithEmail(id);
            activated = true;
        }

        return activated;
    }

    public boolean deactivateUserWithId(String id) {
        boolean deactivated = false;

        if (userRepo.jobSeekerExistsWithEmail(id)) {
            userRepo.deactivateJobSeekerWithEmail(id);
            deactivated = true;
        } else if (userRepo.employerExistsWithEmail(id)) {
            userRepo.deactivateEmployerWithEmail(id);
            deactivated = true;
        } else if (userRepo.adminExistsWithEmail(id)) {
            userRepo.deactivateAdminWithEmail(id);
            deactivated = true;
        }

        return deactivated;
    }


    public boolean makePayment(String id, int amount) {
        boolean paymentMade = false;

        if (userRepo.jobSeekerExistsWithEmail(id)) {
            userRepo.jobSeekerMadePayment(id, amount);
            paymentMade = true;
        } else if (userRepo.employerExistsWithEmail(id)) {
            userRepo.employerMadePayment(id, amount);
            paymentMade = true;
        }
        return paymentMade;
    }

}
