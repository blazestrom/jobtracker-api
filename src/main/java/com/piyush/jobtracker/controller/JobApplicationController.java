package com.piyush.jobtracker.controller;

import com.piyush.jobtracker.dto.JobApplicationRequestDTO;
import com.piyush.jobtracker.dto.JobApplicationResponseDTO;
import com.piyush.jobtracker.dto.JobApplicationUpdatedDTO;
import com.piyush.jobtracker.dto.JobStatusUpdateDTO;
import com.piyush.jobtracker.entity.JobApplication;
import com.piyush.jobtracker.service.JobApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.List;
import  org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/applications")
public class JobApplicationController {
    private  final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService){
        this.jobApplicationService=jobApplicationService;}

    @PostMapping
    public ResponseEntity<JobApplicationResponseDTO> createApplication(@Valid @RequestBody JobApplicationRequestDTO jobApplication){
            return  ResponseEntity.status(HttpStatus.CREATED).body(jobApplicationService.createJobApplication(jobApplication));
    }

    @GetMapping
    public ResponseEntity<List<JobApplicationResponseDTO>> getAllApplication() {
        List<JobApplicationResponseDTO>application = jobApplicationService.getAllApplication();
        return  ResponseEntity.ok(application);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<JobApplicationResponseDTO>> getApplicationsByUserId(
            @PathVariable Long userId) {

        List<JobApplicationResponseDTO> applications =
                jobApplicationService.getApplicationsByUserId(userId);

        return ResponseEntity.ok(applications);
    }
    @GetMapping("/{id}")
    public ResponseEntity<JobApplicationResponseDTO> getApplicationById( @PathVariable Long id){
         JobApplicationResponseDTO application=jobApplicationService.getApplicationById(id);
        return ResponseEntity.ok(application);
    }
    @PatchMapping("/{id}/status")
    public  ResponseEntity<JobApplicationResponseDTO> updateStatus(@PathVariable Long id, @Valid @RequestBody JobStatusUpdateDTO request){
        JobApplicationResponseDTO updated=jobApplicationService.UpdateStatus(id,request);
        return ResponseEntity.ok(updated);
    }
    @PutMapping("/{id}")
    public  ResponseEntity<JobApplicationResponseDTO>  updateApplication(
            @PathVariable Long id,
            @Valid @RequestBody JobApplicationUpdatedDTO request){
        JobApplicationResponseDTO updated=jobApplicationService.UpdateApplication(id,request);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        jobApplicationService.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }

}
