package com.comp353.webcareerportal.models;

import lombok.Getter;
import lombok.Setter;

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
    private String status;
    private int accountBalance;
}
