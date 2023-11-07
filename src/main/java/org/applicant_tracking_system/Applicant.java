package org.applicant_tracking_system;

import java.util.ArrayList;
import java.util.List;

public class Applicant {
    private List<String> previousCompanies;
    private String currentCity;
    private String preferredLocation;
    private double expectedSalary;
    private ApplicationStatus status;

    public Applicant(ApplicationStatus status) {
        this.previousCompanies = new ArrayList<>();
        this.currentCity = "";
        this.preferredLocation = "";
        this.expectedSalary = 0;
        this.status = status;
    }

    public List<String> getPreviousCompanies() {
        return previousCompanies;
    }

    public void setPreviousCompanies(List<String> previousCompanies) {
        this.previousCompanies = previousCompanies;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getPreferredLocation() {
        return preferredLocation;
    }

    public void setPreferredLocation(String preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public double getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(double expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public boolean isRelocationPreferred() {
        return preferredLocation.equalsIgnoreCase(currentCity);
    }
}
