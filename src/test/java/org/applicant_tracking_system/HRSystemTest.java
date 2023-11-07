package org.applicant_tracking_system;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HRSystemTest {
    private static JobPosition getJobPosition() {
        JobPosition jobPosition = new JobPosition() {{
            setTitle("Software Developer");
            setDescription("Work with Java here");
            setOfferedSalaryRangeStart(60000);
            setOfferedSalaryRangeEnd(80000);
            setRequiredSkills(new ArrayList<>(){{
                add("Java");
                add("Springboot");
            }});
            setLocation("New York City");
            setIndustry("IT");
            setRole("Software Engineer");
        }};

        jobPosition.setRequiredSkills(new ArrayList<>(){{
            add("Java");
            add("Springboot");
        }});
        return jobPosition;
    }

    private static Applicant getApplicant1() {
        return new Applicant(ApplicationStatus.PENDING) {{
            setPreviousCompanies(new ArrayList<>() {{add("Zalando"); add("Babel");}});
            setCurrentCity("Helsinki");
            setPreferredLocation("New York");
            setExpectedSalary(75000.0);
        }};
    }

    private static Applicant getApplicant2() {
        return new Applicant(ApplicationStatus.PENDING) {{
            setPreviousCompanies(new ArrayList<>() {{add("Toyota"); add("Mercedes");}});
            setCurrentCity("Toronto");
            setPreferredLocation("Berlin");
            setExpectedSalary(60000.0);
        }};
    }

    private static HRSystem getHRSystem() {
        Recruiter recruiter1 = new Recruiter("John");
        Recruiter recruiter2 = new Recruiter("Jamie");
        HRSystem hrSystem = new HRSystem() {{
            setApplicants(new ArrayList<>() {{
                add(getApplicant1());
                add(getApplicant2());
            }});
            setRecruiters(
                    new ArrayList<>() {{
                        add(recruiter1);
                        add(recruiter2);
                    }}
            );
            setJobPositions(new ArrayList<>() {{
                getJobPosition();
            }});
        }};
        return hrSystem;
    }
    public static HRSystem hrSystem = getHRSystem();

    @Test
    void testAddJobPosition() {
        JobPosition jobPosition = getJobPosition();
        hrSystem.addJobPosition(jobPosition);

        assertTrue(hrSystem.getJobPositions().contains(jobPosition));
    }

    @Test
    void testAddRecruiter() {
        Recruiter recruiter = new Recruiter("Mary");
        hrSystem.addRecruiter(recruiter);
        assertTrue(hrSystem.getRecruiters().contains(recruiter));
    }

    @Test
    void testAddApplicant() {
        Applicant applicant = getApplicant1();
        hrSystem.addApplicant(applicant);
        assertTrue(hrSystem.getApplicants().contains(applicant));
    }

    @Test
    void testGenerateReport() throws IOException {
        hrSystem.generateReport();
        assertTrue(Files.exists(Path.of("src/main/java/org/applicant_tracking_system/recruitment_reports.txt")));
    }
}