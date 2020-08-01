package com.comp353.webcareerportal.controller;

import com.comp353.webcareerportal.models.Admin;
import com.comp353.webcareerportal.models.Employer;
import com.comp353.webcareerportal.models.JobSeeker;
import com.comp353.webcareerportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * @param employerId to be put via the url
     * @param password to be put in the body as a simple text
     * @return true if user exists and password matches, false otherwise
     */
    //TODO use more secure way of handling password
    @GetMapping(path = "authenticateUser/{employerId}")
    public boolean authenticateUser(@PathVariable String employerId, @RequestBody String password){
        return userService.authenticateUser(employerId, password);
    }

    @PutMapping(path = "updateJobSeekerCategory/{id}/{category}")
    public boolean changeJobSeekerCategory(@PathVariable(name = "id") String id, @PathVariable(name = "category") String category){
        return userService.updateJobSeekerCategory(id, category);
    }

    @PutMapping(path = "updateEmployerCategory/{id}/{category}")
    public boolean changeEmployerCategory(@PathVariable(name = "id") String id, @PathVariable(name = "category") String category){
        return userService.updateEmployerCategory(id, category);
    }

    /**
     * @param id to be put via the url
     * @param newPassword to be put in the body as a simple text
     * @return true if user exists and password matches, false otherwise
     */
    //TODO use more secure way of handling password
    @PutMapping(path = "updateEmployerCategory/{id}")
    public boolean updateUserPassword(@PathVariable(name = "id") String id, @RequestBody String newPassword){
        return userService.updateUserPassword(id, newPassword);
    }
}
}
