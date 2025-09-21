package com.endava.internship.health;

public final class Risk {
    public enum Glycemia { LOW, PREDIABETES, DIABETES }

    private Risk() {}

    public static Glycemia fastingGlucoseRisk(double mgdl) {
        if (mgdl < 0) throw new IllegalArgumentException("negative glucose");
        if (mgdl < 100) return Glycemia.LOW;
        if (mgdl < 126) return Glycemia.PREDIABETES;
        return Glycemia.DIABETES;
    }
}
