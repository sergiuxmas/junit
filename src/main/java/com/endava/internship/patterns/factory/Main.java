package com.endava.internship.patterns.factory;

public class Main {
    public static void main(String[] args) {
        LogisticsFactory railLogistics = new RailLogisticsFactory();
        railLogistics.planDelivery();

        LogisticsFactory seaLogistics = new SeaLogisticsFactory();
        seaLogistics.planDelivery();
    }
}

