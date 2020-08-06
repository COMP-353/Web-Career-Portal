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
@Entity (name = "JobCategory")
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"category"})})
public class JobCategory {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int categoryId;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String description;
}