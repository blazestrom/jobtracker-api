package com.piyush.jobtracker.repository;
import com.piyush.jobtracker.entity.JobApplication;
import com.piyush.jobtracker.enums.JobStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.*;
public interface JobApplicationRepository extends JpaRepository<JobApplication,Long>{
    List<JobApplication> findByUserId(Long userId);
    List<JobApplication> findByStatus(JobStatus status);
    List<JobApplication> findByCompanyNameContainingIgnoreCase(String companyName);
    List<JobApplication> findByJobRoleContainingIgnoreCase(String jobRole);
    //for pagging
    Page<JobApplication> findAll(Pageable pageable);

}
