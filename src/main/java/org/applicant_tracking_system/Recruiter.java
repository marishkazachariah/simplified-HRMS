package org.applicant_tracking_system;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Recruiter {
    private String name;
    private List<JobPosition> jobPositionsManaged;
    private Set<String> specializedIndustries;
    private Set<String> specializedRoles;

    public Recruiter(String name) {
        this.name = name;
        this.jobPositionsManaged = new ArrayList<>();
        this.specializedIndustries = new HashSet<>();
        this.specializedRoles = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<JobPosition> getJobPositionsManaged() {
        return jobPositionsManaged;
    }

    public void setJobPositionsManaged(List<JobPosition> jobPositionsManaged) {
        this.jobPositionsManaged = jobPositionsManaged;
    }

    public void assignJobPosition(JobPosition jobPosition) {
        jobPositionsManaged.add(jobPosition);
    }

    public Set<String> getSpecializedIndustries() {
        return specializedIndustries;
    }

    public void setSpecializedIndustries(Set<String> specializedIndustries) {
        this.specializedIndustries = specializedIndustries;
    }

    public Set<String> getSpecializedRoles() {
        return specializedRoles;
    }

    public void setSpecializedRoles(Set<String> specializedRoles) {
        this.specializedRoles = specializedRoles;
    }

    public void reviewApplicant(Applicant applicant) {
        if (applicant != null) {
            if (applicant.getPreviousCompanies() != null
                    && applicant.getCurrentCity() != null
                    && applicant.getPreferredLocation() != null
                    && applicant.getExpectedSalary() != 0
                    && applicant.getStatus() != null) {
                applicant.setStatus(ApplicationStatus.REVIEWED);
            }
        } else {
            System.err.println("Invalid application/missing information");
        }
    }

    public boolean isSpecializedFor(JobPosition jobPosition) {
        String jobIndustry = jobPosition.getIndustry();
        String jobRole = jobPosition.getRole();

        boolean specializedInIndustry = specializedIndustries.contains(jobIndustry);
        boolean specializedInRole = specializedRoles.contains(jobRole);

        return specializedInIndustry && specializedInRole;
    }
}
