# Decorator Pattern

This package contains a Decorator example with `Coffee`, `SimpleCoffee`, `CoffeeDecorator`, `MilkDecorator`, and `SugarDecorator`.

## What it solves
Decorator adds behavior or data to an object **dynamically**, without changing the original class and without creating many subclasses.

## Classes in this package
- `Coffee` - component interface
- `SimpleCoffee` - concrete component
- `CoffeeDecorator` - base decorator
- `MilkDecorator`, `SugarDecorator` - concrete decorators
- `Main` / `DecoratorPatternDemo` - demo

## Schema
```text
Coffee
├── SimpleCoffee
└── CoffeeDecorator
    ├── MilkDecorator
    └── SugarDecorator

Main
  -> new SimpleCoffee()
  -> new MilkDecorator(coffee)
  -> new SugarDecorator(coffee)
```

## Real use cases

### 1. Java I/O streams
A classic real-world example is Java streams:
- `InputStream` is the base abstraction
- `FileInputStream` is a concrete component
- `BufferedInputStream` and `DataInputStream` decorate it

```text
InputStream -> BufferedInputStream -> DataInputStream
```

### 2. Adding optional features to orders or drinks
A shopping or restaurant system can start with a base item, then add extras like:
- milk
- sugar
- whipped cream
- gift wrap
- insurance

Each extra decorates the base object and adjusts description and price.

## Notes
- In this package, `MilkDecorator` and `SugarDecorator` wrap the same `Coffee` interface.
- The client code works only with `Coffee`, not with the concrete implementation details.

