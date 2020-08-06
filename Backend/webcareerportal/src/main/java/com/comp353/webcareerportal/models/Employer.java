package com.comp353.webcareerportal.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@NoArgsConstructor
@Entity (name = "employer")
public class Employer extends User{
    protected EmployerCategory employerCategory;

    public Employer(String email, String password, String category){
        this.setEmail(email);
        this.setPassword(password);
        this.setStatus("Active");
        this.setAccountBalance(0);
        this.setEmployerCategory(category);
    }

    public String getEmployerCategory() {
        return employerCategory.getValue();
    }

    public void setEmployerCategory(String category){

        if(category.equals("Gold") || category.equals("gold")) this.employerCategory = EmployerCategory.GOLD;
        else this.employerCategory = EmployerCategory.PRIME;

    }
}
