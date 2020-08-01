package com.comp353.webcareerportal.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

@Getter
@Setter
@MappedSuperclass
public abstract class User {
    @Id
    private String email;
    private String password;
    @Column(columnDefinition = "VARCHAR(20) default 'Active'")
    private String status;
    @Column(columnDefinition = "int default 0")
    private int accountBalance;
}
