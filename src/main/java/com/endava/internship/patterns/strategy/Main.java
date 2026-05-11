package com.endava.internship.patterns.strategy;

public class Main {
    public static void main(String[] args) {
        LoginContext lc = new LoginContext();

        lc.setStrategy(new APILoginStrategy());
        lc.performLogin("admin", "admin");

        lc.setStrategy(new UILoginStrategy());
        lc.performLogin("admin", "admin");
    }
}

