package com.endava.internship.health;

public final class Dosage {
    private Dosage() {}

    public static double doseFor(double weightKg, double mgPerKg, double maxPerDose) {
        if (weightKg <= 0 || mgPerKg < 0 || maxPerDose <= 0) throw new IllegalArgumentException("invalid args");
        double raw = weightKg * mgPerKg;
        double clipped = Math.min(raw, maxPerDose);
        return Math.round(clipped * 10.0) / 10.0;
    }
}
