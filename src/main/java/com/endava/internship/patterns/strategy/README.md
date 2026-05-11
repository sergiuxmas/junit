# Strategy Pattern

This package contains a Strategy example with `LoginStrategy`, concrete strategies, `LoginContext`, and a demo `Main`.

## What it solves
Strategy lets you switch between algorithms or behaviors **at runtime** without changing the client code.

## Classes in this package
- `LoginStrategy` - strategy interface
- `UILoginStrategy` - UI-based login behavior
- `APILoginStrategy` - API-based login behavior
- `LoginContext` - context that delegates to the selected strategy
- `Main` / `StrategyPatternDemo` - demo

## Schema
```text
Client -> LoginContext -> LoginStrategy
                         /           \
                        /             \
               UILoginStrategy   APILoginStrategy
```

## Real use cases

### 1. Different authentication methods
An application may support:
- UI login
- API login
- SSO login
- token-based login

The context stays stable while the strategy changes according to environment or test scenario.

### 2. Pricing or routing algorithms
A system may choose a strategy for:
- cheapest shipping
- fastest shipping
- greenest route
- premium recommendation

```text
CheckoutService -> ShippingStrategy -> chosen algorithm
```

## Strategy + Factory together

Yes — **Factory Pattern is often used together with Strategy Pattern**, and they fit very well.

- **Strategy Pattern** defines a family of interchangeable algorithms or behaviors.
- **Factory Pattern** decides which concrete strategy object to create.

In short:

```text
Factory creates the right Strategy -> Strategy executes the behavior
```

### Payment example

```java
interface PaymentStrategy {
    void pay(double amount);
}

class CardPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid by card: " + amount);
    }
}

class PayPalPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid by PayPal: " + amount);
    }
}

class PaymentStrategyFactory {
    public static PaymentStrategy create(String type) {
        return switch (type) {
            case "card" -> new CardPayment();
            case "paypal" -> new PayPalPayment();
            default -> throw new IllegalArgumentException("Unknown payment type");
        };
    }
}
```

### Usage

```java
PaymentStrategy strategy = PaymentStrategyFactory.create("card");
strategy.pay(100);
```

Here:

- **Strategy Pattern** = `PaymentStrategy`, `CardPayment`, `PayPalPayment`
- **Factory Pattern** = `PaymentStrategyFactory`, which selects the correct strategy

This combination is commonly used when you want to avoid code like:

```java
if (type.equals("card")) {
   ...
} else if (type.equals("paypal")) {
   ...
}
```

## Notes
- This package demonstrates runtime switching via `setStrategy(...)`.
- Strategy is a strong fit when you want to replace condition-heavy logic with pluggable behavior.

