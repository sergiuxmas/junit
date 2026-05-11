package com.endava.internship.patterns.builder.simple;

public class BurgerBuilder {
    private final Burger burger;

    public BurgerBuilder() {
        burger = new Burger();
    }

    public BurgerBuilder setBun(String bun) {
        burger.setBun(bun);
        return this;
    }

    public BurgerBuilder setCheese(String cheese) {
        burger.setCheese(cheese);
        return this;
    }

    public BurgerBuilder setMeat(String meat) {
        burger.setMeat(meat);
        return this;
    }

    public BurgerBuilder setSauce(String sauce) {
        burger.setSauce(sauce);
        return this;
    }

    public BurgerBuilder setSalad(String salad) {
        burger.setSalad(salad);
        return this;
    }

    public BurgerBuilder setPrice(int price) {
        burger.setPrice(price);
        return this;
    }

    public Burger build() {
        return burger;
    }
}

