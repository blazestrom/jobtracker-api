package com.piyush.jobtracker.dto;

import com.piyush.jobtracker.enums.JobStatus;
import jakarta.validation.constraints.NotNull;

public class JobStatusUpdateDTO {
    @NotNull(message ="status should not be empty" )
    private JobStatus status;
    public JobStatusUpdateDTO(){

    }
    public  JobStatus getStatus() {
        return status;
    }
    public  void setStatus(JobStatus status){
        this.status=status;
    }

}
