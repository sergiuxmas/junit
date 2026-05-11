# Composite Pattern

This package contains a Composite example with `Component`, `Composite`, `ComputerLeaf`, and a demo tree in `Main`.

## What it solves
Composite lets you build **tree structures** and treat single objects and grouped objects in the same way.

## Classes in this package
- `Component` - common contract
- `ComputerLeaf` - individual item with a price
- `Composite` - group containing children
- `Main` / `CompositePatternDemo` - demo of a computer parts hierarchy

## Schema
```text
Component
├── ComputerLeaf
└── Composite
    ├── add(Component)
    └── showPrice()

Computer
├── Cabinet
│   ├── HDD
│   └── Motherboard
│       ├── RAM
│       └── Processor
└── Peripherals
    ├── Keyboard
    └── Mouse
```

## Real use cases

### 1. File systems and folder trees
A file explorer treats a file and a folder through a common abstraction:
- file = leaf
- folder = composite

Actions like render, count size, or delete can work recursively.

### 2. UI component trees
A UI screen can contain panels, forms, sections, and fields.
A panel contains child components, while a button or text field is a leaf.

```text
Screen -> Panel -> Section -> Field
```

## Notes
- In this package, `showPrice()` recursively prints the structure.
- Composite is useful whenever a "part-whole" hierarchy appears.

