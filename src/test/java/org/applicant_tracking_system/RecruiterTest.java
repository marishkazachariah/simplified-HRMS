package org.applicant_tracking_system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class RecruiterTest {
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

    @DisplayName("Asserts true if recruiter's specialized industries or roles has at least one match to the job's role or industry")
    @Test
    void testIsSpecializedFor_True() {
        Recruiter recruiter = new Recruiter("John");
        recruiter.setSpecializedIndustries(new HashSet<>(Arrays.asList("IT", "Agriculture")));
        recruiter.setSpecializedRoles(new HashSet<>(Arrays.asList("Software Engineer", "Farmer")));

        JobPosition jobPosition = getJobPosition();

        assertTrue(recruiter.isSpecializedFor(jobPosition));
    }

    @DisplayName("Asserts false if recruiter's specialized industries or roles does not match job role or industry")
    @Test
    void testIsSpecializedFor_False() {
        Recruiter recruiter = new Recruiter("John");
        recruiter.setSpecializedIndustries(new HashSet<>(Arrays.asList("Finance", "Pharmaceuticals")));
        recruiter.setSpecializedRoles(new HashSet<>(Arrays.asList("Software Engineer", "Farmer")));

        JobPosition jobPosition = getJobPosition();

        assertFalse(recruiter.isSpecializedFor(jobPosition));

        Recruiter recruiter2 = new Recruiter("Mary");
        recruiter.setSpecializedIndustries(new HashSet<>(Arrays.asList("IT", "Pharmaceuticals")));
        recruiter.setSpecializedRoles(new HashSet<>(Arrays.asList("Music Production", "Farmer")));
        assertFalse(recruiter2.isSpecializedFor(jobPosition));
    }
}