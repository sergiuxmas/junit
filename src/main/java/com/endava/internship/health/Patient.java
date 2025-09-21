package com.endava.internship.health;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public final class Patient {
    private final String id;
    private final String name;
    private final double heightCm;
    private final double weightKg;
    private final LocalDate dob;

    public Patient(String id, String name, double heightCm, double weightKg, LocalDate dob) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.heightCm = heightCm;
        this.weightKg = weightKg;
        this.dob = Objects.requireNonNull(dob);
    }

    public String id() { return id; }
    public String name() { return name; }
    public double heightCm() { return heightCm; }
    public double weightKg() { return weightKg; }
    public LocalDate dob() { return dob; }

    public int ageYears(LocalDate at) {
        return Period.between(dob, at).getYears();
    }
}
