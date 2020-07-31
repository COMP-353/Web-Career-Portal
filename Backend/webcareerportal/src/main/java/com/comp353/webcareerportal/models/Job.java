package com.comp353.webcareerportal.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
public class Job {
    @Id
    private int jobId;
    private String jobStatus;
    private String category;
    private String description;

    @OneToOne //(mappedBy = "createdApplication")
    private Application application;
}
