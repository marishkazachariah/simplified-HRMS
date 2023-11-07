package org.applicant_tracking_system;

public enum ApplicationStatus {
    PENDING("Pending"),
    REVIEWED("Reviewed"),
    ACCEPTED("Accepted"),
    REJECTED("Rejected");

    private final String statusName;

    ApplicationStatus(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }
}
