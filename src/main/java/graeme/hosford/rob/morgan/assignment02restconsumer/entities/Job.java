package graeme.hosford.rob.morgan.assignment02restconsumer.entities;

import java.time.LocalDate;

public class Job {
    private long jobId;
    private String jobName;
    private String jobDescription;
    private LocalDate jobPublishedDate;

    public Job() {}

    public Job(long jobId, String jobName, String jobDescription, LocalDate jobPublishedDate) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.jobDescription = jobDescription;
        this.jobPublishedDate = jobPublishedDate;
    }

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public LocalDate getJobPublishedDate() {
        return jobPublishedDate;
    }

    public void setJobPublishedDate(LocalDate jobPublishedDate) {
        this.jobPublishedDate = jobPublishedDate;
    }
}
