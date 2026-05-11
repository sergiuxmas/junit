package com.endava.internship.patterns.factory;

public class SeaLogisticsFactory extends LogisticsFactory {
    @Override
    public Transport createTransport() {
        return new ShipTransport();
    }
}

