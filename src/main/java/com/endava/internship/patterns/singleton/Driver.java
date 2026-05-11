package com.endava.internship.patterns.singleton;

public class Driver {

    private static Driver instance;

    private Driver() {
    }

    public static Driver getInstance() {
        if (instance == null) {
            instance = new Driver();
            System.out.println("First instance is created");
            return instance;
        }

        System.out.println("You see, only one instance!");
        return instance;
    }
}

