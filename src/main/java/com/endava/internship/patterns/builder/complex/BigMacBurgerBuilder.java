package com.endava.internship.patterns.builder.complex;

public class BigMacBurgerBuilder implements BurgerBuilder {
    private final Burger burger = new Burger();

    @Override
    public void setBurgerType() {
        burger.setBurgerType("Big Mac Burger");
        burger.setPrice(75);
    }

    @Override
    public void addSalad() {
        burger.setSalad("Rucola");
    }

    @Override
    public void addSauce() {
        burger.setSauce("Big Mac Sauce");
    }

    @Override
    public void addBun() {
        burger.setBun("Coal Bun");
    }

    @Override
    public void addCheese() {
        burger.setCheese("Cheddar");
    }

    @Override
    public void addMeat() {
        burger.setMeat("Beef");
    }

    @Override
    public Burger getBurger() {
        return burger;
    }
}

