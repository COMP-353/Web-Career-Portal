package com.comp353.webcareerportal.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class User {
    @Id
    private String email;
    private String password;
    private String status;
    private int accountBalance;
}
