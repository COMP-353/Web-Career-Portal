package com.comp353.webcareerportal.service;

import com.comp353.webcareerportal.dao.ActivityDao;
import com.comp353.webcareerportal.dao.UserDao;
import com.comp353.webcareerportal.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Service
public class UserService {

    private final String EMPLOYER_ADDED = "EMPLOYER JOINED THE SYSTEM";
    private final String JOBSEEKER_ADDED = "JOB SEEKER JOINED THE SYSTEM";
    private final String AUTHENTICATED = "LOGGED INTO THE SYSTEM";
    private final String FAILED_LOGIN = "TRIED TO LOG INTO THE SYSTEM";
    private final String CHANGED_PASSWORD = "CHANGED PASSWORD";
    private final String CHANGED_CATEGORY = "CHANGED THEIR CATEGORY";
    private final String USER_DELETED = "WAS DELETED FROM THE SYSTEM";
    private final String USER_ACTIVATED = "HAD THEIR ACCOUNT ACTIVATED";
    private final String USER_DEACTIVATED = "HAD THEIR ACCOUNT DEACTIVATED";
    private final String MADE_PAYMENT = "MADE A PAYMENT OF ";

    @Autowired
    private UserDao userRepo;
    
    @Autowired
    private ApplicationService applicationService;
    
    @Autowired
    private JobService jobService;

    @Autowired
    private ActivityDao activityDao;

    public boolean addNewAdmin(Admin admin) {
        if (userRepo.adminExistsWithEmail(admin.getEmail())) return false;
        userRepo.save(admin);
        return true;
    }

    public boolean addNewJobSeeker(JobSeeker jobSeeker) {
        if (userRepo.jobSeekerExistsWithEmail(jobSeeker.getEmail())) return false;
        userRepo.save(jobSeeker);
        activityDao.save(new Activity(jobSeeker.getEmail(), JOBSEEKER_ADDED));
        return true;
    }

    public boolean addNewEmployer(Employer employer) {
        if (userRepo.employerExistsWithEmail(employer.getEmail())) return false;
        userRepo.save(employer);
        activityDao.save(new Activity(employer.getEmail(), EMPLOYER_ADDED));
        return true;
    }

    public boolean authenticateUser(String id, String password) {
        boolean success = false;

        if (userRepo.employerExistsWithEmail(id)) {
            success = userRepo.authenticateEmployerWithEmail(id, password);
            activityDao.save(new Activity(id,AUTHENTICATED));
        } else if (userRepo.jobSeekerExistsWithEmail(id)) {
            success = userRepo.authenticateJobSeekerWithEmail(id, password);
            activityDao.save(new Activity(id,AUTHENTICATED));
        } else if (userRepo.adminExistsWithEmail(id)) {
            success = userRepo.authenticateAdminWithEmail(id, password);
            activityDao.save(new Activity(id,AUTHENTICATED));
        }else {
            activityDao.save(new Activity(id,FAILED_LOGIN));
        }

        return success;
    }

    public boolean updateUserPassword(String id, String newPassword) {
        boolean updatedPassword = false;

        if (userRepo.employerExistsWithEmail(id)) {
            userRepo.updateEmployerPasswordWithEmail(id, newPassword);
            activityDao.save(new Activity(id, CHANGED_PASSWORD));
            updatedPassword = true;
        } else if (userRepo.jobSeekerExistsWithEmail(id)) {
            userRepo.updateJobSeekerPasswordWithEmail(id, newPassword);
            activityDao.save(new Activity(id, CHANGED_PASSWORD));
            updatedPassword = true;
        } else if (userRepo.adminExistsWithEmail(id)) {
            userRepo.authenticateAdminWithEmail(id, newPassword);
            activityDao.save(new Activity(id, CHANGED_PASSWORD));
            updatedPassword = true;
        }

        return updatedPassword;
    }

    public boolean updateJobSeekerCategory(String id, String category) {
        if (userRepo.jobSeekerExistsWithEmail(id)) return false;
        userRepo.updateJobSeekerCategoryWithEmail(id, getJobSeekerCategoryFrom(category));
        activityDao.save(new Activity(id, CHANGED_CATEGORY));
        return true;
    }

    public boolean updateEmployerCategory(String id, String category) {
        if (userRepo.employerExistsWithEmail(id)) return false;
        userRepo.updateEmployerCategoryWithEmail(id, getEmployerCategoryFrom(category));
        activityDao.save(new Activity(id, CHANGED_CATEGORY));
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
            activityDao.save(new Activity(id,USER_DELETED));
            deleted = true;
        } else if (userRepo.jobSeekerExistsWithEmail(id)) {
        	applicationService.deleteApplicationWithJobSeekerId(id);
            userRepo.deleteJobSeekerWithEmail(id);
            activityDao.save(new Activity(id,USER_DELETED));
            deleted = true;
        } else if (userRepo.adminExistsWithEmail(id)) {
            userRepo.deleteAdminWithEmail(id);
            activityDao.save(new Activity(id,USER_DELETED));
            deleted = true;
        }
        return deleted;
    }

    public boolean activateUserWithId(String id) {
        boolean activated = false;

        if (userRepo.jobSeekerExistsWithEmail(id)) {
            userRepo.activateJobSeekerWithEmail(id);
            activityDao.save(new Activity(id, USER_ACTIVATED));
            activated = true;
        } else if (userRepo.employerExistsWithEmail(id)) {
            userRepo.activateEmployerWithEmail(id);
            activityDao.save(new Activity(id, USER_ACTIVATED));
            activated = true;
        } else if (userRepo.adminExistsWithEmail(id)) {
            userRepo.activateAdminWithEmail(id);
            activityDao.save(new Activity(id, USER_ACTIVATED));
            activated = true;
        }

        return activated;
    }

    public boolean deactivateUserWithId(String id) {
        boolean deactivated = false;

        if (userRepo.jobSeekerExistsWithEmail(id)) {
            userRepo.deactivateJobSeekerWithEmail(id);
            activityDao.save(new Activity(id, USER_DEACTIVATED));
            deactivated = true;
        } else if (userRepo.employerExistsWithEmail(id)) {
            userRepo.deactivateEmployerWithEmail(id);
            activityDao.save(new Activity(id, USER_DEACTIVATED));
            deactivated = true;
        } else if (userRepo.adminExistsWithEmail(id)) {
            userRepo.deactivateAdminWithEmail(id);
            activityDao.save(new Activity(id, USER_DEACTIVATED));
            deactivated = true;
        }

        return deactivated;
    }


    public boolean makePayment(String id, int amount) {
        boolean paymentMade = false;

        if (userRepo.jobSeekerExistsWithEmail(id)) {
            userRepo.jobSeekerMadePayment(id, amount);
            activityDao.save(new Activity(id, MADE_PAYMENT + amount));
            paymentMade = true;
        } else if (userRepo.employerExistsWithEmail(id)) {
            userRepo.employerMadePayment(id, amount);
            activityDao.save(new Activity(id, MADE_PAYMENT + amount));
            paymentMade = true;
        }
        return paymentMade;
    }

    public boolean updateJobSeekerName(JobSeeker jobSeeker) {
        if (!userRepo.jobSeekerExistsWithEmail(jobSeeker.getEmail())) return false;
        userRepo.updateJobSeekerName(jobSeeker.getEmail(), jobSeeker.getFirstName(), jobSeeker.getLastName());
        return true;
    }

    public boolean checkIdAvailability(String id) {
        boolean legit = true;
        if (userRepo.jobSeekerExistsWithEmail(id)) {
            legit = false;
        } else if (userRepo.employerExistsWithEmail(id)) {
            legit = false;
        } else if (userRepo.adminExistsWithEmail(id)) {
            legit = false;
        }
        return legit;
    }

    public boolean updateUserEmailWithId(String oldId, String newId) {
        //new id is not available
        if (!checkIdAvailability(newId)) return false;
            //old id is not available
        else {
            updateUserId(oldId, newId);
            return true;
        }
    }

    private boolean updateUserId(String id, String newId) {
        boolean changed = false;
        if (userRepo.jobSeekerExistsWithEmail(id)) {
            userRepo.updateJobSeekerEmail(id, newId);
            changed = true;
        } else if (userRepo.employerExistsWithEmail(id)) {
            userRepo.updateEmployerEmail(id, newId);
            changed = true;
        } else if (userRepo.adminExistsWithEmail(id)) {
            userRepo.updateAdminEmail(id, newId);
            changed = true;
        }
        return changed;
    }


}
