package com.piyush.jobtracker.dto;

public class JobApplicationStatsDTO {

    private long totalApplications;
    private long applied;
    private long interview;
    private long offered;
    private long rejected;

    public JobApplicationStatsDTO(long totalApplications, long applied, long interview, long offered, long rejected) {
        this.totalApplications = totalApplications;
        this.applied = applied;
        this.interview = interview;
        this.offered = offered;
        this.rejected = rejected;
    }

    public long getTotalApplications() { return totalApplications; }
    public long getApplied() { return applied; }
    public long getInterview() { return interview; }
    public long getOffered() { return offered; }
    public long getRejected() { return rejected; }
}