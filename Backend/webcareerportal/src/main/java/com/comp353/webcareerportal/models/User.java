package com.comp353.webcareerportal.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

@Getter
@Setter
@Data
@MappedSuperclass
public abstract class User {
    @Id
    private String email;
    private String password;
    private String status = "Active";
    @Column(columnDefinition = "int default 0")
    private int accountBalance;
}
