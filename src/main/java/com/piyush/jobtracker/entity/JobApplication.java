package com.piyush.jobtracker.entity;

import com.piyush.jobtracker.enums.JobStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "job_applications", indexes = {
        @Index(name = "idx_job_app_status_user", columnList = "status, user_id")
})
public class JobApplication {
    public JobApplication() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank(message = "user cannot be null")

    @NotBlank(message = "compnay name should not be blank")
    @Column(nullable = false)
    private String companyName;

    @NotBlank(message =  "Job role should not be blank")
    @Column(nullable = false)
    private String jobRole;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobStatus status;

    @NotNull(message = "applied date should not be blank")
    @Column(nullable = false)
    private LocalDate appliedDate;

    private String resumeVersion;

    private String jobLink;

    @Column(length = 1000)
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    public User getUser(){
        return   user;
    }
    public void setUser(User user){
        this.user=user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public LocalDate getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(LocalDate appliedDate) {
        this.appliedDate = appliedDate;
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