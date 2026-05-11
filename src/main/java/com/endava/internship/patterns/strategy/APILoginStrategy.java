package com.endava.internship.patterns.strategy;

public class APILoginStrategy implements LoginStrategy {
    @Override
    public void login(String username, String password) {
        System.out.println("Logging in via API call...");
        // Example: RestAssured.given().body(...).post("/login");
    }
}

