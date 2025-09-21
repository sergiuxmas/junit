package com.endava.internship.meeting;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public final class Recurrence {
    private Recurrence() {
    }

    public static List<ZonedDateTime> weekly(ZonedDateTime first, int count) {
        if (count <= 0) throw new IllegalArgumentException("count must be positive");
        List<ZonedDateTime> out = new ArrayList<>(count);
        ZonedDateTime cur = first;
        for (int i = 0; i < count; i++) {
            out.add(cur);
            cur = cur.plusWeeks(1);
        }
        return out;
    }
}