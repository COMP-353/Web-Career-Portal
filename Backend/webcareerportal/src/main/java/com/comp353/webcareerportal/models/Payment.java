package com.comp353.webcareerportal.models;

import lombok.Getter;
import lombok.Setter;
import sun.util.resources.Bundles;

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

    @ManyToOne
    @JoinColumn(name="User")
    private Employer employer;

    @ManyToOne
    @JoinColumn(name="User")
    private JobSeeker jobSeeker;
}
