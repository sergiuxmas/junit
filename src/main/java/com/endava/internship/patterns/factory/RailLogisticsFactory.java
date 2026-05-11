package com.endava.internship.patterns.factory;

public class RailLogisticsFactory extends LogisticsFactory {
    @Override
    public Transport createTransport() {
        return new TrainTransport();
    }
}

