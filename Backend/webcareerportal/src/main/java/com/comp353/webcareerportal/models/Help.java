package com.comp353.webcareerportal.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Help {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String jobSeekerId;
    private String employerId;
    private int applicationId;
    private String status;
    private String info;

    public Help(String jobSeekerId, String employerId, int applicationId, String status, String info){
        this.jobSeekerId = jobSeekerId;
        this.employerId = employerId;
        this.applicationId = applicationId;
        this.status = status;
        this.info = info;
    }
}
