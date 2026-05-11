package com.endava.internship.patterns.builder.complex;

public class CheeseburgerBuilder implements BurgerBuilder {
    private final Burger burger = new Burger();

    @Override
    public void setBurgerType() {
        burger.setBurgerType("Cheeseburger");
        burger.setPrice(55);
    }

    @Override
    public void addSalad() {
        burger.setSalad("Iceberg");
    }

    @Override
    public void addSauce() {
        burger.setSauce("Ketchup");
    }

    @Override
    public void addBun() {
        burger.setBun("Sesame Bun");
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

