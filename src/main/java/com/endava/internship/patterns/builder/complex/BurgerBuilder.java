package com.endava.internship.patterns.builder.complex;

public interface BurgerBuilder {
    void setBurgerType();

    void addSalad();

    void addSauce();

    void addBun();

    void addCheese();

    void addMeat();

    Burger getBurger();
}

