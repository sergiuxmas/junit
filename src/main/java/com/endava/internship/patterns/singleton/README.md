# Singleton Pattern

This package contains singleton examples such as `Driver`, `DriverThreadSafe`, `Singleton`, and `ConfigManager`.

## What it solves
Use Singleton when the application must work with **exactly one shared instance** of something.

## Classes in this package
- `Singleton` - basic lazy singleton
- `Driver` - synchronized singleton entry point
- `DriverThreadSafe` - thread-safe singleton with double-checked locking
- `ConfigManager` - shared configuration access
- `SingletonDemo` - simple usage example

## Simple schema
```text
Client code
   |
   v
getInstance()
   |
   +--> existing instance? ---- yes ----> return same object
   |
   no
   |
   +--> create one object -----> store -----> return it
```

## Real use cases

### 1. Shared test configuration
A test framework often needs one place to read values like:
- base URL
- browser name
- timeouts
- environment flags

Example from this package:
```text
Tests --> ConfigManager.getInstance() --> shared values map
```

### 2. One browser/session manager in UI automation
In Selenium or Playwright-based test frameworks, a single driver/session manager may be reused during one scenario or one test run.

```text
Test steps --> DriverThreadSafe.getInstance() --> one driver/session object
```

## Notes
- `DriverThreadSafe` is the safer example when multiple threads may call `getInstance()`.
- Singleton is useful, but too much global state can make tests harder to isolate and reset.

