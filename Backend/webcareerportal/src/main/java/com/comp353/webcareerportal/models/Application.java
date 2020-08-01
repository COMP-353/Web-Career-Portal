package com.comp353.webcareerportal.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Application {
    @Id
    private int applicationId;
    private Date applicationDate;
    private String applicationStatus;

    @ManyToOne
    @JoinColumn(name="jobseeker_email", nullable=false)
    private JobSeeker email;	 
    
    @ManyToOne
    @JoinColumn(name="job_id", nullable=false)
    private Job job;
}
