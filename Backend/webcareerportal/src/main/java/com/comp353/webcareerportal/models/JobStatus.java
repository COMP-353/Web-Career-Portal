package com.comp353.webcareerportal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity (name = "JobStatus")
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"status"})})
public class JobStatus {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int statusId;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private String description;
}