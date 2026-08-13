package com.piyush.jobtracker.controller;

import com.piyush.jobtracker.dto.*;
import com.piyush.jobtracker.entity.JobApplication;
import com.piyush.jobtracker.enums.JobStatus;
import com.piyush.jobtracker.service.JobApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.List;
import  org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
    @GetMapping("/stats/{userId}")
    public ResponseEntity<JobApplicationStatsDTO> getApplicationStats(@PathVariable Long userId){
        JobApplicationStatsDTO stats= jobApplicationService.getApplicationStats(userId);
        return ResponseEntity.ok(stats);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<JobApplicationResponseDTO>> getApplicationsByUserId(
            @PathVariable Long userId) {

        List<JobApplicationResponseDTO> applications =
                jobApplicationService.getApplicationsByUserId(userId);

        return ResponseEntity.ok(applications);
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<List<JobApplicationResponseDTO>> getApplicationsByStatus(
            @PathVariable JobStatus status) {

        List<JobApplicationResponseDTO> applications =
                jobApplicationService.getApplicationsByStatus(status);

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
    @GetMapping("/search")
    public ResponseEntity<List<JobApplicationResponseDTO>> getApplicationsByCompanyName(
            @RequestParam String companyName) {

        List<JobApplicationResponseDTO> applications =
                jobApplicationService.getApplicationsByCompanyName(companyName);

        return ResponseEntity.ok(applications);
    }
    @GetMapping("/search/role")
    public ResponseEntity<List<JobApplicationResponseDTO>> getApplicationByJobRole(
            @RequestParam String jobRole){
        List<JobApplicationResponseDTO>applications=jobApplicationService.getApplicationByJobRole(jobRole);
        return ResponseEntity.ok(applications);
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
    @GetMapping("/paginated")
    public ResponseEntity<Page<JobApplicationResponseDTO>> getAllApplicationsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Page<JobApplicationResponseDTO> result =
                jobApplicationService.getAllApplicationsPaginatedSort(page, size, sortBy, direction);

        return ResponseEntity.ok(result);
    }



}
