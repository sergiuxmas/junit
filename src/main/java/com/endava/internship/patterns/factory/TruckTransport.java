package com.endava.internship.patterns.factory;

public class TruckTransport implements Transport {
    @Override
    public void deliver() {
        System.out.println("Delivering cargo by land using a truck.");
    }
}

