package com.endava.internship.patterns.singleton;

public class DriverThreadSafe {

    private static volatile DriverThreadSafe instance;

    private DriverThreadSafe() {
    }

    public static DriverThreadSafe getInstance() {
        if (instance == null) {
            synchronized (DriverThreadSafe.class) {
                if (instance == null) {
                    instance = new DriverThreadSafe();
                    System.out.println("First instance is created");
                }
            }
        } else {
            System.out.println("You see, only one instance!");
        }

        return instance;
    }
}

