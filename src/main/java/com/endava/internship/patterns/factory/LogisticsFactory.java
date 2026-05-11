package com.endava.internship.patterns.factory;

public abstract class LogisticsFactory {
    public abstract Transport createTransport();

    public void planDelivery() {
        Transport transport = createTransport();
        transport.deliver();
    }
}

