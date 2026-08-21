package com.piyush.jobtracker.service;

import com.piyush.jobtracker.dto.JobApplicationRequestDTO;
import com.piyush.jobtracker.dto.JobApplicationResponseDTO;
import com.piyush.jobtracker.entity.JobApplication;
import com.piyush.jobtracker.entity.User;
import com.piyush.jobtracker.enums.JobStatus;
import com.piyush.jobtracker.repository.JobApplicationRepository;
import com.piyush.jobtracker.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JobApplicationServiceTest {

    @Mock
    private JobApplicationRepository jobApplicationRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private JobApplicationService jobApplicationService;

    @Test
    void createJobApplication_shouldReturnResponseDTO() {

        // ARRANGE

        JobApplicationRequestDTO request =
                new JobApplicationRequestDTO();

        request.setCompanyName("Google");
        request.setJobRole("Software Engineer");
        request.setResumeVersion("v2");
        request.setJobLink("https://google.com");
        request.setNotes("Good opportunity");


        User user = new User();
        user.setId(1L);


        JobApplication savedJobApplication =
                new JobApplication();

        savedJobApplication.setId(1L);
        savedJobApplication.setCompanyName("Google");
        savedJobApplication.setJobRole("Software Engineer");
        savedJobApplication.setResumeVersion("v2");
        savedJobApplication.setJobLink("https://google.com");
        savedJobApplication.setNotes("Good opportunity");
        savedJobApplication.setStatus(JobStatus.APPLIED);
        savedJobApplication.setAppliedDate(LocalDate.now());
        savedJobApplication.setUser(user);


        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(jobApplicationRepository.save(any(JobApplication.class)))
                .thenReturn(savedJobApplication);


        // ACT

        JobApplicationResponseDTO result =
                jobApplicationService.createJobApplication(request, 1L);


        // ASSERT

        assertEquals(1L, result.getId());
        assertEquals("Google", result.getCompanyName());
        assertEquals("Software Engineer", result.getJobRole());
        assertEquals(JobStatus.APPLIED, result.getStatus());
        assertEquals(LocalDate.now(), result.getAppliedDate());
        assertEquals("https://google.com", result.getJobLink());


        // VERIFY

        verify(userRepository).findById(1L);
        verify(jobApplicationRepository).save(any(JobApplication.class));
    }
}