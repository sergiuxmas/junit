package com.endava.internship.patterns.composite;

public class Main {
    public static void main(String[] args) {
        Component hdd = new ComputerLeaf("HDD", 50);
        Component ram = new ComputerLeaf("RAM", 30);
        Component processor = new ComputerLeaf("Processor", 200);
        Component keyboard = new ComputerLeaf("Keyboard", 20);
        Component mouse = new ComputerLeaf("Mouse", 10);

        Composite motherboard = new Composite("Motherboard");
        motherboard.add(ram);
        motherboard.add(processor);

        Composite cabinet = new Composite("Cabinet");
        cabinet.add(hdd);
        cabinet.add(motherboard);

        Composite peripherals = new Composite("Peripherals");
        peripherals.add(keyboard);
        peripherals.add(mouse);

        Composite computer = new Composite("Computer");
        computer.add(cabinet);
        computer.add(peripherals);

        computer.showPrice();
    }
}

