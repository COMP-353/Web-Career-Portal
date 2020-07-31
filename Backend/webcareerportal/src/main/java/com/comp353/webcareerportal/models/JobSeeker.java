package com.comp353.webcareerportal.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "jobseeker")
public class JobSeeker extends User{

    @OneToMany //(mappedBy = "createdApplication")
    private Set<Application> applications;
}
