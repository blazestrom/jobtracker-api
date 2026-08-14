package com.piyush.jobtracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class JobApplicationRequestDTO {

    @NotBlank(message = "Company name cannot be blank")
    private String companyName;

    @NotBlank(message = "Job role cannot be blank")
    private String jobRole;

    private String resumeVersion;

    private String jobLink;

    private String notes;



    public JobApplicationRequestDTO() {
    }

    public JobApplicationRequestDTO(String companyName,
                                    String jobRole,
                                    String resumeVersion,
                                    String jobLink,
                                    String notes
                                    ) {

        this.companyName = companyName;
        this.jobRole = jobRole;
        this.resumeVersion = resumeVersion;
        this.jobLink = jobLink;
        this.notes = notes;

    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getResumeVersion() {
        return resumeVersion;
    }

    public void setResumeVersion(String resumeVersion) {
        this.resumeVersion = resumeVersion;
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