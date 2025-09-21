package com.endava.internship.health;

import java.util.Map;

public final class Labs {
    private Labs() {}
    private static final Map<String,double[]> RANGES = Map.of(
        "GLU_FASTING", new double[]{70, 99},
        "HB", new double[]{12.0, 17.5},
        "WBC", new double[]{4.0, 11.0}
    );
    public static boolean inRange(String code, double value) {
        double[] r = RANGES.get(code);
        if (r == null) throw new IllegalArgumentException("Unknown lab code: " + code);
        return value >= r[0] && value <= r[1];
    }
}
