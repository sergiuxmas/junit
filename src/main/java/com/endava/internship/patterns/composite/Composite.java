package com.endava.internship.patterns.composite;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {
    private final String name;
    private final List<Component> components = new ArrayList<>();

    public Composite(String name) {
        this.name = name;
    }

    public void add(Component component) {
        components.add(component);
    }

    @Override
    public void showPrice() {
        System.out.println(name);
        for (Component component : components) {
            component.showPrice();
        }
    }
}

