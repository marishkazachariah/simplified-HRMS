package org.applicant_tracking_system;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JobPositionTest {
    @DisplayName("Expect to return true if expectedSalary is within the offered salary range")
    @Test
    void testIsWithinBudget_True() {
        JobPosition jobPosition = new JobPosition();
        jobPosition.setOfferedSalaryRangeStart(50000.0);
        jobPosition.setOfferedSalaryRangeEnd(70000.0);

        Applicant applicant = new Applicant(ApplicationStatus.PENDING);

        applicant.setExpectedSalary(60000.0);
        assertTrue(jobPosition.isWithinBudget(applicant));
    }

    @DisplayName("Expect to return true if expectedSalary is outside the offered salary range")
    @Test
    void testIsWithinBudget_False() {
        JobPosition jobPosition = new JobPosition();
        jobPosition.setOfferedSalaryRangeStart(50000.0);
        jobPosition.setOfferedSalaryRangeEnd(70000.0);

        Applicant applicant = new Applicant(ApplicationStatus.PENDING);

        applicant.setExpectedSalary(75000.0);
        assertFalse(jobPosition.isWithinBudget(applicant));

        applicant.setExpectedSalary(45000.0);
        assertFalse(jobPosition.isWithinBudget(applicant));
    }

    @DisplayName("Expect isWithinBudget to be true when expectedSalary is at the minimum offered salary range")
    @Test
    void testIsWithinBudget_AtOfferedSalaryStart() {
        JobPosition jobPosition = new JobPosition();
        jobPosition.setOfferedSalaryRangeStart(50000.0);
        jobPosition.setOfferedSalaryRangeEnd(70000.0);

        Applicant applicant = new Applicant(ApplicationStatus.PENDING);

        applicant.setExpectedSalary(50000.0);
        assertTrue(jobPosition.isWithinBudget(applicant));
    }

    @DisplayName("Expect isWithinBudget to be true when expectedSalary is at the maximum offered salary range")
    @Test
    void testIsWithinBudget_AtOfferedSalaryEnd() {
        JobPosition jobPosition = new JobPosition();
        jobPosition.setOfferedSalaryRangeStart(50000.0);
        jobPosition.setOfferedSalaryRangeEnd(70000.0);

        Applicant applicant = new Applicant(ApplicationStatus.PENDING);

        applicant.setExpectedSalary(70000.0);
        assertTrue(jobPosition.isWithinBudget(applicant));
    }
}