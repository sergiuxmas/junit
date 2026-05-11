package com.endava.internship.patterns.builder.simple;

public class Burger {
    private String bun;
    private String cheese;
    private String meat;
    private String sauce;
    private String salad;
    private int price;

    public Burger() {
    }

    public Burger(String bun, String cheese, String meat, String sauce, int price, String salad) {
        this.bun = bun;
        this.cheese = cheese;
        this.meat = meat;
        this.sauce = sauce;
        this.price = price;
        this.salad = salad;
    }

    public Burger(String bun, String meat, String sauce, int price, String salad) {
        this.bun = bun;
        this.meat = meat;
        this.sauce = sauce;
        this.price = price;
        this.salad = salad;
    }

    public String getBun() {
        return bun;
    }

    public void setBun(String bun) {
        this.bun = bun;
    }

    public String getCheese() {
        return cheese;
    }

    public void setCheese(String cheese) {
        this.cheese = cheese;
    }

    public String getMeat() {
        return meat;
    }

    public void setMeat(String meat) {
        this.meat = meat;
    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public String getSalad() {
        return salad;
    }

    public void setSalad(String salad) {
        this.salad = salad;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Burger{" +
                "bun='" + bun + '\'' +
                ", cheese='" + cheese + '\'' +
                ", meat='" + meat + '\'' +
                ", sauce='" + sauce + '\'' +
                ", salad='" + salad + '\'' +
                ", price=" + price +
                '}';
    }

}
