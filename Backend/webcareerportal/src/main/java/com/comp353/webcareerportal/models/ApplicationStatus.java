package com.comp353.webcareerportal.models;

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
@Entity (name = "ApplicationStatus")
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"status"})})
public class ApplicationStatus {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int statusId;
    private String status;
    private String description;
}