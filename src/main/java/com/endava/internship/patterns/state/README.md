# State Pattern

This package is currently a placeholder, but the State pattern is typically used when an object changes its behavior depending on its current state.

## What it solves
State removes long `if/else` or `switch` blocks by moving behavior into dedicated state objects.

## Typical schema
```text
Context
  -> currentState : State
  -> request()
        |
        v
      State implementation

State
├── DraftState
├── PublishedState
└── ArchivedState
```

## Real use cases

### 1. Order lifecycle
An order changes behavior depending on whether it is:
- created
- paid
- shipped
- delivered
- cancelled

For example, `cancel()` may be allowed in `CreatedState` but blocked in `ShippedState`.

### 2. Document workflow
A document editor may have states like:
- draft
- review
- approved
- published

Each state controls what operations are allowed.

## Notes
- There is no Java implementation in this package yet.
- When added, a typical structure would contain a `Context`, a `State` interface, and several concrete states.

