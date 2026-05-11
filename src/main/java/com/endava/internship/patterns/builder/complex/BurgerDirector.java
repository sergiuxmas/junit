package com.endava.internship.patterns.builder.complex;

public class BurgerDirector {

    public void buildBurger(BurgerBuilder builder) {
        builder.setBurgerType();
        builder.addBun();
        builder.addCheese();
        builder.addSauce();
        builder.addSalad();
        builder.addMeat();
    }
}

