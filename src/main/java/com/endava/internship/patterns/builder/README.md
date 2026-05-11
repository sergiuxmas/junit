# Builder Pattern

This package demonstrates Builder in multiple forms:
- classic builder roles with `KidsMeal`, `KidsMealBuilder`, `HappyMealBuilder`, and `Cashier`
- a fluent example under `simple`
- a director + multiple builder example under `complex`

## What it solves
Builder creates an object **step by step** when:
- there are many fields
- some fields are optional
- different variants share the same construction process

## Package map
- `KidsMeal` - product
- `KidsMealBuilder` - builder contract
- `HappyMealBuilder` - concrete builder
- `Cashier` - director
- `Main` - demo
- `simple/BurgerBuilder` - fluent builder
- `complex/BurgerDirector` and burger builders - variant-based builder flow

## Schema
```text
Client -> Director -> Builder -> Product
          Cashier    KidsMealBuilder   KidsMeal
                        |
                        +--> HappyMealBuilder
```

## Real use cases

### 1. Test data builders
A test suite may need many variations of the same object:
- admin user
- guest user
- invalid user
- premium customer

A builder makes setup short and readable.

```text
Test -> UserBuilder -> withRole("ADMIN") -> withEmail(...) -> build()
```

### 2. HTTP or messaging payload creation
When building a complex request or event object with headers, body, metadata, and options, Builder avoids long constructors and keeps creation explicit.

```text
Client -> RequestBuilder
       -> setBody()
       -> setHeaders()
       -> setRetryPolicy()
       -> build()
```

## Notes
- The top-level package shows the classic Builder pattern.
- The `simple` subpackage shows a fluent builder style.
- The `complex` subpackage shows how different builders create different burger variants through one director.

