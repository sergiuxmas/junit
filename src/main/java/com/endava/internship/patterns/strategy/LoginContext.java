package com.endava.internship.patterns.strategy;

public class LoginContext {
    private LoginStrategy strategy;

    public void setStrategy(LoginStrategy strategy) {
        this.strategy = strategy;
    }

    public void performLogin(String username, String password) {
        strategy.login(username, password);
    }

    public static void main(String[] args) {
        LoginContext lc = new LoginContext();

        lc.setStrategy(new APILoginStrategy());
        lc.performLogin("admin", "admin");

        lc.setStrategy(new UILoginStrategy());
        lc.performLogin("admin", "admin");
    }
}

