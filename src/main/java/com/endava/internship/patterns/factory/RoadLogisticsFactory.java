package com.endava.internship.patterns.factory;

public class RoadLogisticsFactory extends LogisticsFactory {
    @Override
    public Transport createTransport() {
        return new TruckTransport();
    }
}
