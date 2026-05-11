package com.endava.internship.patterns.builder;

public class KidsMeal {
    private String burger;
    private String drink;
    private String dessert;
    private String toy;

    public void setBurger(String burger) {
        this.burger = burger;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public void setDessert(String dessert) {
        this.dessert = dessert;
    }

    public void setToy(String toy) {
        this.toy = toy;
    }

    public void showItems() {
        System.out.println("Kids Meal includes:");
        System.out.println("Burger: " + burger);
        System.out.println("Drink: " + drink);
        System.out.println("Dessert: " + dessert);
        System.out.println("Toy: " + toy);
    }
}

