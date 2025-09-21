package com.endava.internship.health;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assumptions;

import java.time.LocalDate;
import java.time.Duration;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static com.endava.internship.health.WithinPercent.withinPercent;
import static org.junit.jupiter.api.Assertions.*;

public class HealthAssertionsTest {

    @Test
    void junit_basicAssertions() {
        assertEquals(22.5, Bmi.bmi(180, 73), 0.0001, "BMI should match");
        assertNotEquals(25.0, Bmi.bmi(180, 73));
        assertTrue(Validators.isValidMrn("A1B2C3D4"));
        assertFalse(Validators.isValidMrn("bad-mrn"));

        String same = "id";
        String sameRef = same;
        String different = new String("id");
        assertSame(same, sameRef);
        assertNotSame(same, different);

        assertNull(null);
        assertNotNull(same);

        byte[] a = "abc".getBytes();
        byte[] b = "abc".getBytes();
        assertArrayEquals(a, b);

        List<Integer> l1 = List.of(1,2,3);
        List<Integer> l2 = List.of(1,2,3);
        assertIterableEquals(l1, l2);

        List<String> expected = List.of("Patient: .*", "DOB: \\d{4}-\\d{2}-\\d{2}");
        List<String> actual = List.of("Patient: Jane Doe", "DOB: 1985-03-01");
        assertLinesMatch(expected, actual);
    }

    @Test
    void junit_groupingAndExceptions() {
        assertAll("BMI pipeline",
            () -> assertEquals(22.5, Bmi.bmi(180, 73), 0.0001),
            () -> assertEquals(Bmi.Category.NORMAL, Bmi.classify(22.5))
        );

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
            () -> Bmi.bmi(0, 70));
        assertTrue(ex.getMessage().contains("positive"));

        assertDoesNotThrow(() -> Bmi.bmi(170, 65));

        Object p = new Patient("X1234567","John",180,75, LocalDate.of(1990,1,2));
        Patient casted = assertInstanceOf(Patient.class, p);
        assertEquals("John", casted.name());
    }

    @Test
    void junit_timeouts() {
        assertTimeout(Duration.ofMillis(50), () -> {
            double v = Dosage.doseFor(70, 10, 800);
            assertEquals(700.0, v);
        });

        assertTimeoutPreemptively(Duration.ofMillis(50), () -> {
            Labs.inRange("HB", 13.5);
        });
    }

    @Test
    void junit_assumptions() {
        Assumptions.assumeTrue(true, "Demo assume");
        Assumptions.assumingThat(Locale.getDefault().getLanguage().equals("en"),
            () -> assertTrue(Validators.isValidMrn("ABCDEF12")));
    }

    @Test
    void hamcrest_coreMatchers() {
        double bmi = Bmi.bmi(180, 73);
        assertThat(bmi, is(closeTo(22.5, 0.0001)));
        assertThat(Bmi.classify(bmi).name(), anyOf(is("NORMAL"), is("OVERWEIGHT")));

        String mrn = "ZXCVBN12";
        assertThat(mrn, allOf(notNullValue(), matchesPattern("^[A-Z0-9]{8,12}$")));

        String name = "Dr. Jane Doe";
        assertThat(name, allOf(startsWith("Dr. "), containsString("Jane"), endsWith("Doe")));

        java.util.List<Integer> readings = java.util.Arrays.asList(119, 121, 118);
        assertThat(readings, hasSize(3));
        assertThat(readings, everyItem(lessThan(130)));
        assertThat(readings, containsInAnyOrder(118,119,121));

        java.util.Map<String, Double> labs = java.util.Map.of("HB", 14.2, "WBC", 6.0);
        assertThat(labs, hasKey("HB"));
        assertThat(labs, hasEntry("WBC", 6.0));

//        int[] arr = {1,2,3};
//        assertThat(arr, arrayWithSize(3));
//        assertThat(arr, arrayContaining(1,2,3));

        double dose = Dosage.doseFor(70, 10, 800);
        assertThat(dose, withinPercent(700.0, 1.0));
    }
}
