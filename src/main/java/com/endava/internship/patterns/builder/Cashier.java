package com.endava.internship.patterns.builder;

public class Cashier {
    public void prepareMeal(KidsMealBuilder builder) {
        builder.buildBurger();
        builder.buildDrink();
        builder.buildDessert();
        builder.buildToy();
    }
}

