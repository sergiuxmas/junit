package com.endava.internship.patterns.strategy;

public class StrategyPatternDemo {
    public static void main(String[] args) {
        LoginContext context = new LoginContext();

        context.setStrategy(new UILoginStrategy());
        context.performLogin("tester", "1234");

        context.setStrategy(new APILoginStrategy());
        context.performLogin("tester", "1234");
    }
}

