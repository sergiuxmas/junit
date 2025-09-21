package com.endava.internship.health;

public final class BloodPressure {
    public enum Stage { NORMAL, ELEVATED, HTN1, HTN2, CRISIS }

    private BloodPressure() {}

    public static Stage stage(int systolic, int diastolic) {
        if (systolic <= 0 || diastolic <= 0) throw new IllegalArgumentException("values must be positive");
        if (systolic >= 180 || diastolic >= 120) return Stage.CRISIS;
        if (systolic >= 140 || diastolic >= 90) return Stage.HTN2;
        if (systolic >= 130 || diastolic >= 80) return Stage.HTN1;
        if (systolic >= 120 && diastolic < 80) return Stage.ELEVATED;
        return Stage.NORMAL;
    }
}
