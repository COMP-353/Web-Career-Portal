package com.comp353.webcareerportal.controller;

import com.comp353.webcareerportal.models.Admin;
import com.comp353.webcareerportal.models.Employer;
import com.comp353.webcareerportal.models.JobSeeker;
import com.comp353.webcareerportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "user/")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping(path = "newEmployer")
    public String addNewEmployer(@RequestBody Employer employer) {
        return userService.addNewEmployer(employer) ? "Employer added successfully" : "Employer already exists";
    }

    @PostMapping(path = "newAdmin")
    public String addNewAdmin(@RequestBody Admin admin) {
        return userService.addNewAdmin(admin)? "Admin added successfully" : "Admin already exists";
    }

    @PostMapping(path = "newJobSeeker")
    public String addNewJobSeeker(@RequestBody JobSeeker jobSeeker) {
        return userService.addNewJobSeeker(jobSeeker)? "JobSeeker added successfully" : "JobSeeker already exists";
    }
}
