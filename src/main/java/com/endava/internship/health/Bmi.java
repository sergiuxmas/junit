package com.endava.internship.health;

/*
  Calculate Body Mass Index and classify it.
 */
public final class Bmi {
    public enum Category {UNDERWEIGHT, NORMAL, OVERWEIGHT, OBESE}

    private Bmi() {
    }

    public static double bmi(double heightCm, double weightKg) {
        if (heightCm <= 0 || weightKg <= 0) throw new IllegalArgumentException("height and weight must be positive");
        double h = heightCm / 100.0;
        double val = weightKg / (h * h);
        return Math.round(val * 10.0) / 10.0;
    }

    public static Category classify(double bmi) {
        if (bmi < 18.5) return Category.UNDERWEIGHT;
        if (bmi < 25.0) return Category.NORMAL;
        if (bmi < 30.0) return Category.OVERWEIGHT;
        return Category.OBESE;
    }
}
