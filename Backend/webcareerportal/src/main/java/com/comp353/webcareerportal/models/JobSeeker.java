package com.comp353.webcareerportal.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Data
@Entity(name = "jobseeker")
@NoArgsConstructor
public class JobSeeker extends User {

    private String firstName;
    private String lastName;
    private JobSeekerCategory jobSeekerCategory;

    public JobSeeker(String firstName, String lastName, String email, String password, String category) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.setEmail(email);
        this.setStatus("Active");
        if (password != null) this.setPassword(password);
        if (category != null) this.setJobSeekerCategory(category);
    }


    public void setJobSeekerCategory(String category) {

        if (category.equals("Gold") || category.equals("gold")) this.jobSeekerCategory = JobSeekerCategory.GOLD;
        else if (category.equals("Prime") || category.contains("prime"))
            this.jobSeekerCategory = JobSeekerCategory.PRIME;
        else this.jobSeekerCategory = JobSeekerCategory.BASIC;
    }

    public String getJobSeekerCategory() {
        return jobSeekerCategory.getValue();
    }
}
