package com.endava.internship.meeting;

import java.util.Objects;

public final class Participant {
    private final String name;
    private final String email;

    public Participant(String name, String email) {
        this.name = Objects.requireNonNull(name);
        this.email = Objects.requireNonNull(email);
    }

    public String name() {
        return name;
    }

    public String email() {
        return email;
    }

    @Override
    public String toString() {
        return name + " <" + email + ">";
    }
}

