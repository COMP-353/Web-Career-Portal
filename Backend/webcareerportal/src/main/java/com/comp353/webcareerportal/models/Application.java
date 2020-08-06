package com.comp353.webcareerportal.models;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity (name = "application")
public class Application {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int applicationId;
    @Column(nullable = false)
    private Date applicationDate;
    
    @ManyToOne
    @JoinColumn(name="application_status", nullable=false)
    private ApplicationStatus applicationStatus;

    @ManyToOne
    @JoinColumn(name="jobseeker_email", nullable=false)
    private JobSeeker jobseeker;	 
    
    @ManyToOne
    @JoinColumn(name="job_id", nullable=false)
    private Job job;
}
