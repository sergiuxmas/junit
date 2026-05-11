package com.endava.internship.patterns.builder.complex;

public class Main {
    public static void main(String[] args) {
        BurgerDirector director = new BurgerDirector();

        BurgerBuilder bigMacBuilder = new BigMacBurgerBuilder();
        director.buildBurger(bigMacBuilder);
        Burger bigMac = bigMacBuilder.getBurger();
        System.out.println(bigMac);

        BurgerBuilder cheeseburgerBuilder = new CheeseburgerBuilder();
        director.buildBurger(cheeseburgerBuilder);
        Burger cheeseburger = cheeseburgerBuilder.getBurger();
        System.out.println(cheeseburger);
    }
}

