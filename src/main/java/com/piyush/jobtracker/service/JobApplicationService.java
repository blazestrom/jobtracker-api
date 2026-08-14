package com.piyush.jobtracker.service;

import com.piyush.jobtracker.JobtrackerApplication;
import com.piyush.jobtracker.dto.*;
import com.piyush.jobtracker.entity.JobApplication;
import com.piyush.jobtracker.enums.JobStatus;
import com.piyush.jobtracker.repository.JobApplicationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.piyush.jobtracker.repository.UserRepository;
import com.piyush.jobtracker.exception.UserNotFoundException;
import com.piyush.jobtracker.entity.User;
import com.piyush.jobtracker.exception.JobApplicationNotFoundException;
import java.time.LocalDate;
import java.util.*;
import org.springframework.data.domain.Sort;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository,UserRepository userRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.userRepository = userRepository;
    }
    public JobApplicationResponseDTO createJobApplication(JobApplicationRequestDTO request ,Long userId) {

        JobApplication jobApplication = new JobApplication();

        jobApplication.setCompanyName(request.getCompanyName());
        jobApplication.setJobRole(request.getJobRole());
        jobApplication.setResumeVersion(request.getResumeVersion());
        jobApplication.setJobLink(request.getJobLink());
        jobApplication.setNotes(request.getNotes());

        jobApplication.setStatus(JobStatus.APPLIED);
        jobApplication.setAppliedDate(LocalDate.now());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        jobApplication.setUser(user);

        JobApplication savedJobApplication =
                jobApplicationRepository.save(jobApplication);

        return new JobApplicationResponseDTO(
                savedJobApplication.getId(),
                savedJobApplication.getCompanyName(),
                savedJobApplication.getJobRole(),
                savedJobApplication.getStatus(),
                savedJobApplication.getAppliedDate(),
                savedJobApplication.getJobLink()
        );
    }
    public List<JobApplicationResponseDTO> getAllApplication(){
        List<JobApplication> application = jobApplicationRepository.findAll();
        List<JobApplicationResponseDTO> responseDTOList= new ArrayList<>();

        for(JobApplication app: application){
            JobApplicationResponseDTO dto= new JobApplicationResponseDTO(
                    app.getId(),
                    app.getCompanyName(),
                    app.getJobRole(),
                    app.getStatus(),
                    app.getAppliedDate(),
                    app.getJobLink()

            );
            responseDTOList.add(dto);
        }

        return responseDTOList;
    }
    public List<JobApplicationResponseDTO> getApplicationsByUserId(Long userId) {

        List<JobApplication> applications = jobApplicationRepository.findByUserId(userId);

        List<JobApplicationResponseDTO> responseList = new ArrayList<>();

        for (JobApplication app : applications) {
            JobApplicationResponseDTO dto = new JobApplicationResponseDTO(
                    app.getId(),
                    app.getCompanyName(),
                    app.getJobRole(),
                    app.getStatus(),
                    app.getAppliedDate(),
                    app.getJobLink()

            );
            responseList.add(dto);
        }

        return responseList;
    }
    public JobApplicationResponseDTO getApplicationById(Long id) {

        JobApplication app = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new JobApplicationNotFoundException("Application not found with id: " + id));

        return new JobApplicationResponseDTO(
                app.getId(),
                app.getCompanyName(),
                app.getJobRole(),
                app.getStatus(),
                app.getAppliedDate(),

                app.getJobLink()
        );
    }
    public JobApplicationResponseDTO UpdateStatus( Long id,JobStatusUpdateDTO request){

        JobApplication app=jobApplicationRepository.findById(id).orElseThrow(()-> new JobApplicationNotFoundException("application not found "+id));
        app.setStatus(request.getStatus());
        JobApplication updated =jobApplicationRepository.save(app);
        return  new JobApplicationResponseDTO(
                updated.getId(),
                updated.getCompanyName(),
                updated.getJobRole(),
                updated.getStatus(),
                updated.getAppliedDate(),
                updated.getJobLink()
        );
    }
    public JobApplicationResponseDTO UpdateApplication(Long id,JobApplicationUpdatedDTO request){
       JobApplication app = jobApplicationRepository.findById(id).orElseThrow(()-> new JobApplicationNotFoundException("application not found"));
        app.setCompanyName(request.getCompanyName());
        app.setJobRole(request.getJobRole());
        app.setResumeVersion(request.getResumeVersion());
        app.setJobLink(request.getJobLink());
        app.setNotes(request.getNotes());

        JobApplication updated = jobApplicationRepository.save(app);
        return new JobApplicationResponseDTO(
                updated.getId(),
                updated.getCompanyName(),
                updated.getJobRole(),
                updated.getStatus(),
                updated.getAppliedDate(),
                updated.getJobLink()
        );
    }
    public void deleteApplication(Long id) {

        JobApplication app = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new JobApplicationNotFoundException("Application not found with id: " + id));

        jobApplicationRepository.delete(app);
    }
    public List<JobApplicationResponseDTO> getApplicationsByStatus(JobStatus status) {

        List<JobApplication> applications = jobApplicationRepository.findByStatus(status);

        List<JobApplicationResponseDTO> responseList = new ArrayList<>();

        for (JobApplication app : applications) {
            JobApplicationResponseDTO dto = new JobApplicationResponseDTO(
                    app.getId(),
                    app.getCompanyName(),
                    app.getJobRole(),
                    app.getStatus(),
                    app.getAppliedDate(),
                    app.getJobLink()
            );
            responseList.add(dto);
        }

        return responseList;
    }
    public List<JobApplicationResponseDTO> getApplicationsByCompanyName(String companyName) {

        List<JobApplication> applications =
                jobApplicationRepository.findByCompanyNameContainingIgnoreCase(companyName);

        List<JobApplicationResponseDTO> responseList = new ArrayList<>();

        for (JobApplication app : applications) {
            JobApplicationResponseDTO dto = new JobApplicationResponseDTO(
                    app.getId(),
                    app.getCompanyName(),
                    app.getJobRole(),
                    app.getStatus(),
                    app.getAppliedDate(),
                    app.getJobLink()
            );
            responseList.add(dto);
        }

        return responseList;
    }
    public List<JobApplicationResponseDTO> getApplicationByJobRole(String jobRole){
        List<JobApplication >applications=jobApplicationRepository.findByJobRoleContainingIgnoreCase(jobRole);
        List<JobApplicationResponseDTO> responseList = new ArrayList<>();
        for(JobApplication app:applications){
            JobApplicationResponseDTO dto = new JobApplicationResponseDTO(
                    app.getId(),
                    app.getCompanyName(),
                    app.getJobRole(),
                    app.getStatus(),
                    app.getAppliedDate(),
                    app.getJobLink()
            );
            responseList.add(dto);
        }
        return responseList;
    }
    public Page<JobApplicationResponseDTO> getAllApplicationsPaginated(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
      Page<JobApplication> applicationPage= jobApplicationRepository.findAll(pageable);
      return applicationPage.map(app->new JobApplicationResponseDTO(
              app.getId(),
              app.getCompanyName(),
              app.getJobRole(),
              app.getStatus(),
              app.getAppliedDate(),
              app.getJobLink()
      ));
    }
    public Page<JobApplicationResponseDTO> getAllApplicationsPaginated(
            int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<JobApplication> applicationPage = jobApplicationRepository.findAll(pageable);

        return applicationPage.map(app -> new JobApplicationResponseDTO(
                app.getId(),
                app.getCompanyName(),
                app.getJobRole(),
                app.getStatus(),
                app.getAppliedDate(),
                app.getJobLink()
        ));
    }
    public Page<JobApplicationResponseDTO> getAllApplicationsPaginatedSort(int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<JobApplication> applicationPage = jobApplicationRepository.findAll(pageable);

        return applicationPage.map(app -> new JobApplicationResponseDTO(
                app.getId(),
                app.getCompanyName(),
                app.getJobRole(),
                app.getStatus(),
                app.getAppliedDate(),
                app.getJobLink()
        ));
    }

    public JobApplicationStatsDTO getApplicationStats(Long UserId){
        List<JobApplication> applications=jobApplicationRepository.findByUserId(UserId);

        long total =applications.size();
        long applied = applications.stream().filter(app->app.getStatus()==JobStatus.APPLIED).count();
        long interview=applications.stream().filter(app->app.getStatus()==JobStatus.INTERVIEW).count();
        long offered =applications.stream().filter(app->app.getStatus()==JobStatus.OFFERED).count();
        long rejected=applications.stream().filter(app->app.getStatus()==JobStatus.REJECTED).count();

        return new JobApplicationStatsDTO(total,applied,interview,offered,rejected);

    }

}
