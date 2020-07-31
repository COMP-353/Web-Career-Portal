package com.comp353.webcareerportal.service;

import com.comp353.webcareerportal.dao.UserDao;
import com.comp353.webcareerportal.models.Admin;
import com.comp353.webcareerportal.models.Employer;
import com.comp353.webcareerportal.models.JobSeeker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userRepo;

    public boolean addNewAdmin(Admin admin){
        if (userRepo.adminExistsWithEmail(admin.getEmail())) return false;
         userRepo.save(admin);
         return true;
    }

    public boolean addNewJobSeeker(JobSeeker jobSeeker){
        if (userRepo.jobSeekerExistsWithEmail(jobSeeker.getEmail())) return false;
        userRepo.save(jobSeeker);
        return true;
    }

    public boolean addNewEmployer(Employer employer){
        if (userRepo.employerExistsWithEmail(employer.getEmail())) return false;
        userRepo.save(employer);
        return true;
    }
}
