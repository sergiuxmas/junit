package com.endava.internship.patterns.strategy;

public class UILoginStrategy implements LoginStrategy {
    @Override
    public void login(String username, String password) {
        System.out.println("Logging in through UI with Selenium...");
        // Example: driver.findElement(By.id("user")).sendKeys(username);
    }
}

