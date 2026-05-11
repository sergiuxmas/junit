package com.endava.internship.patterns.singleton;

public final class SingletonDemo {

    private SingletonDemo() {
    }

    public static void main(String[] args) {
        Singleton first = Singleton.getInstance();
        Singleton second = Singleton.getInstance();

        System.out.println("Same instance: " + (first == second));
        System.out.println("Browser from config: " + ConfigManager.getInstance().get("browser"));
    }
}

