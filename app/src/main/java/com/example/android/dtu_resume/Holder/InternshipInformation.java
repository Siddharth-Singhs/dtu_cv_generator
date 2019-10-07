package com.example.android.dtu_resume.Holder;



public class InternshipInformation {

    private String organisation;
    private String designation;
    private String date;
    private String role;

    public InternshipInformation(String organisation,String designation,String date,String role)
    {
        this.organisation=organisation;
        this.designation=designation;
        this.date=date;
        this.role=role;

    }
    public String getOrganisation()
    {
        return organisation;
    }

    public String getDesignation()
    {
        return  designation;
    }
    public String getDate()
    {
        return date;
    }
    public String getRole()
    {
        return  role;
    }

    public void setOrganisation(String organisation)
    {
        this.organisation=organisation;
    }
    public void setDesignation(String designation)
    {
        this.designation=designation;
    }
    public void setDate(String date)
    {
        this.date=date;
    }
    public void setRole(String role)
    {
        this.role=role;
    }



}
