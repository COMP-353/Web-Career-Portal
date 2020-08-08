package com.comp353.webcareerportal.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public abstract class Payment {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int id;
    private boolean automaticWithdrawal;
    private boolean defaultPayment;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="employer")
    private Employer employer;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="jobseeker")
    private JobSeeker jobSeeker;
}
