package com.endava.internship.patterns.composite;

public class ComputerLeaf implements Component {
    private String name;
    private Double price;

    public ComputerLeaf() {
    }

    public ComputerLeaf(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public void showPrice() {
        System.out.println(name + ": $" + price);
    }
}

