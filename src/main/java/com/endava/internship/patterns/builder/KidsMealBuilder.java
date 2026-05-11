package com.endava.internship.patterns.builder;

public interface KidsMealBuilder {
    void buildBurger();

    void buildDrink();

    void buildDessert();

    void buildToy();

    KidsMeal getMeal();
}

