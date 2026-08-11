package com.piyush.jobtracker.dto;

import jakarta.validation.constraints.NotBlank;

public class JobApplicationUpdatedDTO {
    @NotBlank(message = "companyName cannot be null")
    private String companyName;
    @NotBlank(message = "job role cannot be null")
    private String jobRole;

    private String ResumeVersion;
    private String jobLink;
    private String notes;

    public JobApplicationUpdatedDTO() {
    }

    public @NotBlank(message = "companyName cannot be null") String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(@NotBlank(message = "companyName cannot be null") String companyName) {
        this.companyName = companyName;
    }

    public @NotBlank(message = "job role cannot be null") String getJobRole() {
        return jobRole;
    }

    public void setJobRole(@NotBlank(message = "job role cannot be null") String jobRole) {
        this.jobRole = jobRole;
    }

    public String getResumeVersion() {
        return ResumeVersion;
    }

    public void setResumeVersion(String ResumeVersion) {
        this.ResumeVersion = ResumeVersion;
    }

    public String getJobLink() {
        return jobLink;
    }

    public void setJobLink(String jobLink) {
        this.jobLink = jobLink;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
