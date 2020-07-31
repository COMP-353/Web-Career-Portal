package com.comp353.webcareerportal.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

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
