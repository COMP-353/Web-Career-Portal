package com.comp353.webcareerportal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int jobId;
    private String title;
    private String jobStatus;
    private String category;
    private String description;
    
    @ManyToOne
    @JoinColumn(name="employer_email", nullable=false)
    private Employer employer;

//    @OneToOne //(mappedBy = "createdApplication")
//    private Application application;
}
