package com.endava.internship.patterns.builder;

public class Main {
    public static void main(String[] args) {
        KidsMealBuilder builder = new HappyMealBuilder();
        Cashier cashier = new Cashier();

        cashier.prepareMeal(builder);  // Director instructs builder
        KidsMeal meal = builder.getMeal();  // Get the finished meal

        meal.showItems();
    }
}

