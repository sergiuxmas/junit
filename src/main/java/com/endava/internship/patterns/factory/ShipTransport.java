package com.endava.internship.patterns.factory;

public class ShipTransport implements Transport {
    @Override
    public void deliver() {
        System.out.println("Delivering cargo by sea using a ship.");
    }
}

