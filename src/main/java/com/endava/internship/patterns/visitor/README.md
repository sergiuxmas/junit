# Visitor Pattern

This package is currently documentation-only, but the Visitor pattern is used when you want to add new operations to an object structure **without changing the element classes every time**.

## What it solves
Visitor separates:
- the **data structure** (`Element` objects)
- from the **operations** performed on that structure (`Visitor` implementations)

This is especially useful when the object model is stable, but the operations change often.

## Typical roles
- `Element` - declares `accept(Visitor visitor)`
- `ConcreteElement` - real element that accepts a visitor
- `Visitor` - declares one `visit(...)` method per element type
- `ConcreteVisitor` - implements a specific operation
- `Client` - builds the object structure and sends visitors through it

## Schema
```text
Client
  |
  v
Element.accept(visitor)
  |
  v
Visitor.visit(ConcreteElement)

Visitor
├── PriceVisitor
├── ExportVisitor
└── ValidationVisitor

Element
├── FileNode
├── FolderNode
└── ReportNode
```

## Real use cases

### 1. AST processing in compilers or interpreters
Compilers often have a tree of nodes such as:
- expression node
- statement node
- variable node

Different visitors can then be applied to the same tree:
- type checking visitor
- code generation visitor
- pretty-print visitor

```text
AST -> TypeCheckVisitor
AST -> CodeGenVisitor
AST -> PrettyPrintVisitor
```

### 2. Exporting the same object structure to multiple formats
Imagine a reporting system with objects like:
- invoice
- line item
- customer summary

The structure stays the same, but you may want different operations:
- export to PDF
- export to Excel
- export to JSON
- calculate totals

Visitor avoids adding all those methods directly into the domain classes.

```text
Report structure -> PdfExportVisitor
Report structure -> ExcelExportVisitor
Report structure -> TotalsVisitor
```

## Notes
- Visitor is strongest when the element hierarchy is stable, but new operations are added frequently.
- It is often used with trees, document models, parsers, and reporting systems.
- This package does not yet contain Java classes, but the README documents the pattern for future implementation.

