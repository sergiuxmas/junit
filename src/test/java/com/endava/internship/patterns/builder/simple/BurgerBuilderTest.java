package com.endava.internship.patterns.builder.simple;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BurgerBuilderTest {

    @Test
    void buildCreatesBurgerWithConfiguredFields() {
        Burger burger = new BurgerBuilder()
                .setBun("Sesame")
                .setCheese("Cheddar")
                .setMeat("Beef")
                .setSauce("BBQ")
                .setSalad("Lettuce")
                .setPrice(45)
                .build();

        assertAll(
                () -> assertEquals("Sesame", burger.getBun()),
                () -> assertEquals("Cheddar", burger.getCheese()),
                () -> assertEquals("Beef", burger.getMeat()),
                () -> assertEquals("BBQ", burger.getSauce()),
                () -> assertEquals("Lettuce", burger.getSalad()),
                () -> assertEquals(45, burger.getPrice())
        );
    }
}

