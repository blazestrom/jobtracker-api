package com.piyush.jobtracker.dto;

import com.piyush.jobtracker.enums.JobStatus;

import java.time.LocalDate;
public class JobApplicationResponseDTO {
    public JobApplicationResponseDTO(Long id, String companyName, String jobRole, JobStatus status, LocalDate appliedDate, String jobLink) {
        this.id=id;
        this.companyName=companyName;
        this.jobRole=jobRole;
        this.status=status;
        this.appliedDate=appliedDate;
        this.jobLink=jobLink;


    }

    public JobApplicationResponseDTO() {
    }

    private  Long id;
    private String companyName;
    private  String jobRole;
    private JobStatus status;
    private LocalDate appliedDate;
    private  String jobLink;

    public String getJobLink() {
        return jobLink;
    }

    public void setJobLink(String jobLink) {
        this.jobLink = jobLink;
    }

    public LocalDate getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(LocalDate appliedDate) {
        this.appliedDate = appliedDate;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }
}

