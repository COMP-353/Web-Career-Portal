package com.comp353.webcareerportal.models;


public enum EmployerCategory {
    PRIME ("Prime"),
    GOLD("Gold");

    private final String category;

    EmployerCategory(String employerCategory){
        this.category = employerCategory;
    }

    public String getValue(){
        return category;
    }
}
