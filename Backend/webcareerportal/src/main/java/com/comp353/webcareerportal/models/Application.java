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
    @JoinTable(name = "createdApplication",
            joinColumns =
                    {@JoinColumn(name = "applicationId")},
            inverseJoinColumns =
                    {@JoinColumn(name = "jobSeekerId")})
    private JobSeeker jobSeeker;

    @OneToOne
    @JoinTable(name = "jobApplication",
            joinColumns =
                    {@JoinColumn(name = "applicationId")},
            inverseJoinColumns =
                    {@JoinColumn(name = "jobId")})
    private Job job;
}
