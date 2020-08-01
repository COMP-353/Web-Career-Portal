package com.comp353.webcareerportal.models;

public enum JobSeekerCategory {
    BASIC("Basic"), PRIME("Prime"), GOLD("Gold");

    private String category;

    JobSeekerCategory(String category){
        this.category = category;
    }

    public String getValue(){
        return category;
    }
}
