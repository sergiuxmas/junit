package com.endava.internship.patterns.builder;

public class HappyMealBuilder implements KidsMealBuilder {
    private final KidsMeal meal = new KidsMeal();

    @Override
    public void buildBurger() {
        meal.setBurger("Cheeseburger");
    }

    @Override
    public void buildDrink() {
        meal.setDrink("Apple Juice");
    }

    @Override
    public void buildDessert() {
        meal.setDessert("Donut");
    }

    @Override
    public void buildToy() {
        meal.setToy("Red Car");
    }

    @Override
    public KidsMeal getMeal() {
        return meal;
    }
}

