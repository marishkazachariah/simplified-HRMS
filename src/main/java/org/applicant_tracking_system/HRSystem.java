package org.applicant_tracking_system;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class HRSystem {
    private List<JobPosition> jobPositions;
    private List<Recruiter> recruiters;
    private List<Applicant> applicants;

    public HRSystem() {
        this.jobPositions = new ArrayList<>();
        this.recruiters = new ArrayList<>();
        this.applicants = new ArrayList<>();
    }

    public List<JobPosition> getJobPositions() {
        return jobPositions;
    }

    public void setJobPositions(List<JobPosition> jobPositions) {
        this.jobPositions = jobPositions;
    }

    public List<Recruiter> getRecruiters() {
        return recruiters;
    }

    public void setRecruiters(List<Recruiter> recruiters) {
        this.recruiters = recruiters;
    }

    public List<Applicant> getApplicants() {
        return applicants;
    }

    public void setApplicants(List<Applicant> applicants) {
        this.applicants = applicants;
    }

    public void addJobPosition(JobPosition jobPosition) {
        if (jobPosition != null && jobPosition.getOfferedSalaryRangeStart() <= jobPosition.getOfferedSalaryRangeEnd()) {
            jobPositions.add(jobPosition);
        } else {
            System.err.println("Invalid job position. Please check the provided details.");
        }
    }

    public void addRecruiter(Recruiter recruiter) {
        if (recruiter != null) {
            recruiters.add(recruiter);
        } else {
            System.err.println("Invalid recruiter. Please check the provided details.");
        }
    }

    public void addApplicant(Applicant applicant) {
        if (applicant != null) {
            applicants.add(applicant);
        } else {
            System.out.println("Invalid applicant. Please check the provided details.");
        }
    }

    public void generateReport() {
        // Number of applicants per status
        Map<ApplicationStatus, Long> statusCounts = applicants.stream()
                .collect(Collectors.groupingBy(
                        Applicant::getStatus,
                        Collectors.counting()
                ));

        // Number of applicants per job position based on salary and preferred city
        Map<JobPosition, Long> positionCounts = applicants.stream()
                .map(this::findMatchingJobPosition)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.groupingBy(jp -> jp, Collectors.counting()));


        // Save report to text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/org/applicant_tracking_system/recruitment_reports.txt"))) {
            writer.write("***HR Report***\n");

            writer.write("Number of applicants per status\n");
            statusCounts.forEach((status, count) ->
                    {
                        try {
                            writer.write(status + ": " + count + "\n");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );

            writer.write("\nNumber of applicants per job position based on salary and preferred city\n");
            positionCounts.forEach((jobPosition, count) ->
                    {
                        try {
                            writer.write(jobPosition.getTitle() + ": " + count + "\n");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );

            System.out.println("Report saved successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Optional<JobPosition> findMatchingJobPosition(Applicant applicant) {
        if (!jobPositions.isEmpty()) {
            return jobPositions.stream()
                    .filter(jobPosition ->
                            applicant.getExpectedSalary() >= jobPosition.getOfferedSalaryRangeStart() &&
                                    applicant.getExpectedSalary() <= jobPosition.getOfferedSalaryRangeEnd() &&
                                    jobPosition.getLocation().equalsIgnoreCase(applicant.getPreferredLocation())
                    )
                    .findFirst();
        }
        return Optional.empty();
    }
}
