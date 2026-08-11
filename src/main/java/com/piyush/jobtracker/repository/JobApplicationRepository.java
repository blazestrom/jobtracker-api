package com.piyush.jobtracker.repository;
import com.piyush.jobtracker.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface JobApplicationRepository extends JpaRepository<JobApplication,Long>{
    List<JobApplication> findByUserId(Long userId);

}
