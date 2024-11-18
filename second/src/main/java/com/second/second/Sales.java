package com.second.second;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Sales {
    @Id
    @GeneratedValue
    private Integer id;
    private String company;
    private String status;
    private String lead;


    public Sales() {
    }

    public Sales(Integer id, String company, String lead, String status) {
        this.id = id;
        this.company = company;
        this.lead = lead;
        this.status = status;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }





}
