package com.piyush.jobtracker.service;

import com.piyush.jobtracker.JobtrackerApplication;
import com.piyush.jobtracker.dto.JobApplicationRequestDTO;
import com.piyush.jobtracker.dto.JobApplicationResponseDTO;
import com.piyush.jobtracker.dto.JobApplicationUpdatedDTO;
import com.piyush.jobtracker.dto.JobStatusUpdateDTO;
import com.piyush.jobtracker.entity.JobApplication;
import com.piyush.jobtracker.enums.JobStatus;
import com.piyush.jobtracker.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;
import com.piyush.jobtracker.repository.UserRepository;
import com.piyush.jobtracker.exception.UserNotFoundException;
import com.piyush.jobtracker.entity.User;
import com.piyush.jobtracker.exception.JobApplicationNotFoundException;
import java.time.LocalDate;
import java.util.*;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository,UserRepository userRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.userRepository = userRepository;
    }
    public JobApplicationResponseDTO createJobApplication(JobApplicationRequestDTO request) {

        JobApplication jobApplication = new JobApplication();

        jobApplication.setCompanyName(request.getCompanyName());
        jobApplication.setJobRole(request.getJobRole());
        jobApplication.setResumeVersion(request.getResumeVersion());
        jobApplication.setJobLink(request.getJobLink());
        jobApplication.setNotes(request.getNotes());

        jobApplication.setStatus(JobStatus.APPLIED);
        jobApplication.setAppliedDate(LocalDate.now());

        User user = userRepository.findById(request.getUserId())
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

}
