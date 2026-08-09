package com.piyush.jobtracker.repository;
import com.piyush.jobtracker.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
public interface JobApplicationRepository extends JpaRepository<JobApplication,Long>{
}
