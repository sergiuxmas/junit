package com.endava.internship.patterns.singleton;

/**
 * Basic lazy-initialized Singleton extracted from the presentation slides.
 */
public final class Singleton {

    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

