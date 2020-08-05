package com.comp353.webcareerportal.controller;

import com.comp353.webcareerportal.models.Admin;
import com.comp353.webcareerportal.models.Employer;
import com.comp353.webcareerportal.models.JobSeeker;
import com.comp353.webcareerportal.service.UserService;
import com.comp353.webcareerportal.service.WcpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping(path = "user/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "employer/{id}")
    public Employer getEmployerWithId(@PathVariable(value = "id") String id){
        return userService.getEmployerWithId(id);
    }

    @GetMapping(path = "jobseeker/{id}")
    public JobSeeker getJobSeekerWithId(@PathVariable(value = "id") String id){
        return userService.getJobSeekerWithId(id);
    }

    @PostMapping(path = "newEmployer/{type}")
    public boolean addNewEmployer(@RequestBody Employer employer, @PathVariable(name = "type") String type) {
        employer.setEmployerCategory(type);
        return userService.addNewEmployer(employer); // ? "Employer added successfully" : "Employer already exists";
    }

    @PostMapping(path = "newAdmin")
    public boolean addNewAdmin(@RequestBody Admin admin) {
        return userService.addNewAdmin(admin); //? "Admin added successfully" : "Admin already exists";
    }

    @PostMapping(path = "newJobSeeker/{type}")
    public boolean addNewJobSeeker(@RequestBody JobSeeker jobSeeker, @PathVariable(name = "type") String type) {
        jobSeeker.setJobSeekerCategory(type);
        return userService.addNewJobSeeker(jobSeeker) ;//? "JobSeeker added successfully" : "JobSeeker already exists";
    }

    /**
     * @param employerId to be put via the url
     * @param password   to be put in the body as a simple text
     * @return true if user exists and password matches, false otherwise
     */
    //TODO use more secure way of handling password
    @GetMapping(path = "authenticateUser/{employerId}/{pw}")
    public boolean authenticateUser(@PathVariable(name = "employerId") String employerId, @PathVariable(name = "pw") String password) {
        return userService.authenticateUser(employerId, password);
    }

    @PutMapping(path = "updateJobSeekerCategory/{id}/{category}")
    public boolean changeJobSeekerCategory(@PathVariable(name = "id") String id, @PathVariable(name = "category") String category) {
        return userService.updateJobSeekerCategory(id, category);
    }

    @PutMapping(path = "updateEmployerCategory/{id}/{category}")
    public boolean changeEmployerCategory(@PathVariable(name = "id") String id, @PathVariable(name = "category") String category) {
        return userService.updateEmployerCategory(id, category);
    }

    /**
     * @param id          to be put via the url
     * @param newPassword to be put in the body as a simple text
     * @return true if user exists and password matches, false otherwise
     */
    //TODO use more secure way of handling password
    @PutMapping(path = "updateEmployerCategory/{id}/{pw}")
    public boolean updateUserPassword(@PathVariable(name = "id") String id, @PathVariable(name = "pw") String newPassword) {
        return userService.updateUserPassword(id, newPassword);
    }

    @DeleteMapping(path = "deleteUser/{id}")
    public boolean deleteUser(@PathVariable(name = "id") String id) {
        return userService.deleteUserWithEmail(id);
    }

    @PutMapping(path = "activate/{id}")
    public boolean activateUserWithId(@PathVariable(name = "id") String id) {
        return userService.activateUserWithId(id);
    }

    @PutMapping(path = "deactivate/{id}")
    public boolean deactivateUserWithId(@PathVariable(name = "id") String id) {
        return userService.deactivateUserWithId(id);
    }

    @PutMapping(path = "pay/{id}/{amount}")
    public boolean userMakePayment(@PathVariable(name = "id") String id, @PathVariable(name = "amount") int amount) {
        return userService.makePayment(id, amount);
    }

    @GetMapping(path = "check/{id}")
    public boolean checkAvailabilityOfUserId(@PathVariable(name = "id") String id) {
        return userService.checkIdAvailability(id);
    }

    @GetMapping(path = "forgot/{id}")
    public void userForgotPassword(@PathVariable(name = "id") String id){
        userService.userForgotPassword(id);
    }


    @PutMapping(path = "updateName")
    public boolean updateJobSeekerName(@RequestBody JobSeeker jobSeeker) {
        return userService.updateJobSeekerName(jobSeeker);
    }

    @PutMapping(path = "updateEmail/{oldId}/{newId}")
    public boolean updateJobSeekerName(@PathVariable(name = "oldId") String oldId, @PathVariable(name = "newId") String id) {
        return userService.updateUserEmailWithId(oldId, id);
    }

    @GetMapping(path = "testEmail/{to}/{s}/{t}")
    public void testEmail(@PathVariable(value = "to") String to, @PathVariable(value = "s") String subject, @PathVariable(value = "t") String text){
        WcpEmailService wcpEmailService = new WcpEmailService();
        try {
            wcpEmailService.sendmail(to, subject,text);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        wcpEmailService.sendSimpleMessage(to,subject,text);
    }
}
