package org.applicant_tracking_system;

import java.util.ArrayList;
import java.util.List;

public class JobPosition {
    private String title;
    private String description;
    private double offeredSalaryRangeStart;
    private double offeredSalaryRangeEnd;
    private List<String> requiredSkills;
    private String location;
    private String industry;
    private String role;

    public JobPosition() {
        this.title = "";
        this.description = "";
        this.offeredSalaryRangeStart = 0;
        this.offeredSalaryRangeEnd = 0;
        this.requiredSkills = new ArrayList<>();
        this.location = "";
        this.industry = "";
        this.role = "";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getOfferedSalaryRangeStart() {
        return offeredSalaryRangeStart;
    }

    public void setOfferedSalaryRangeStart(double offeredSalaryRangeStart) {
        this.offeredSalaryRangeStart = offeredSalaryRangeStart;
    }

    public double getOfferedSalaryRangeEnd() {
        return offeredSalaryRangeEnd;
    }

    public void setOfferedSalaryRangeEnd(double offeredSalaryRangeEnd) {
        this.offeredSalaryRangeEnd = offeredSalaryRangeEnd;
    }

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(List<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isWithinBudget(Applicant applicant) {
        return applicant.getExpectedSalary() >= getOfferedSalaryRangeStart() &&
                applicant.getExpectedSalary() <= getOfferedSalaryRangeEnd();
    }
}
