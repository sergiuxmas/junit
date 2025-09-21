package com.endava.internship.health;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static com.endava.internship.health.WithinPercent.withinPercent;

@DisplayName("Lifecycle & Parameterized tests for health domain")
public class LifecycleAndParameterizedTest {

    private static LocalDate TODAY;
    private Patient templatePatient;

    @BeforeAll
    static void initSuite() {
        TODAY = LocalDate.of(2025, 9, 17);
    }

    @BeforeEach
    void initCase() {
        templatePatient = new Patient("X1234567","Jane Doe", 170, 65, LocalDate.of(1990, 1, 1));
        assertNotNull(templatePatient);
    }

    @ParameterizedTest(name = "BMI({0}cm, {1}kg) ~= {2}")
    @CsvSource({
            "180, 73, 22.5",
            "165, 60, 22.0",
            "150, 45, 20.0"
    })
    void bmiParameterized(int heightCm, double weightKg, double expectedBmi) {
        double bmi = Bmi.bmi(heightCm, weightKg);
        assertEquals(expectedBmi, bmi, 0.0001, "bmi value");
        assertThat(bmi, is(closeTo(expectedBmi, 0.0001)));
        Bmi.Category cat = Bmi.classify(bmi);
        assertThat(cat, anyOf(is(Bmi.Category.UNDERWEIGHT), is(Bmi.Category.NORMAL), is(Bmi.Category.OVERWEIGHT), is(Bmi.Category.OBESE)));
    }

    @ParameterizedTest(name = "BP stage for {0}/{1} -> {2}")
    @CsvSource({
            "118, 76, NORMAL",
            "125, 78, ELEVATED",
            "132, 82, HTN1",
            "142, 92, HTN2",
            "185, 121, CRISIS"
    })
    void bloodPressureStageParameterized(int sys, int dia, String expectedStage) {
        BloodPressure.Stage stage = BloodPressure.stage(sys, dia);
        assertEquals(BloodPressure.Stage.valueOf(expectedStage), stage);
    }

    static Stream<org.junit.jupiter.params.provider.Arguments> doseData() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(70.0, 10.0, 800.0, 700.0),
                org.junit.jupiter.params.provider.Arguments.of(90.0, 10.0, 800.0, 800.0),
                org.junit.jupiter.params.provider.Arguments.of(20.0, 7.5, 200.0, 150.0)
        );
    }

    @ParameterizedTest(name = "Dose for {0}kg @ {1}mg/kg (max {2}) -> {3}mg")
    @MethodSource("doseData")
    void dosageParameterized(double weight, double mgPerKg, double max, double expected) {
        double actual = Dosage.doseFor(weight, mgPerKg, max);
        assertEquals(expected, actual, 0.0001);
        assertThat(actual, withinPercent(expected, 0.5));
    }

    @ParameterizedTest(name = "Valid MRN? \"{0}\"")
    @ValueSource(strings = {"A1B2C3D4", "ZXCVBN12", "QWERTY1234"})
    void validMrnExamples(String mrn) {
        assertTrue(Validators.isValidMrn(mrn));
    }

    @ParameterizedTest(name = "Invalid MRN? \"{0}\"")
    @NullAndEmptySource
    @ValueSource(strings = {"abc", "BAD-MRN", "1234567", "TOO_LONG_12345"})
    void invalidMrnExamples(String mrn) {
        assertFalse(Validators.isValidMrn(mrn));
    }

    @ParameterizedTest(name = "Bad ISO date: \"{0}\" should throw")
    @ValueSource(strings = {"01-01-2020", "2020/01/01", "2020-13-01", "not-a-date"})
    void parseIsoDateFailures(String bad) {
        assertThrows(IllegalArgumentException.class, () -> Validators.parseIsoDate(bad));
    }

    @ParameterizedTest(name = "Age of template patient on {0} is >= 30")
    @ValueSource(strings = {"2024-01-01", "2025-09-17", "2030-01-01"})
    void patientAgeLifecycle(String at) {
        int age = templatePatient.ageYears(LocalDate.parse(at));
        assertThat(age, greaterThanOrEqualTo(30));
        assertThat(templatePatient.name(), is("Jane Doe"));
        assertEquals(LocalDate.of(2025, 9, 17), TODAY);
    }
}
