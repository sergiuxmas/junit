# Factory Method Pattern

This package contains a Factory Method example with `Transport`, concrete transport classes, `LogisticsFactory`, and concrete logistics factories.

## What it solves
Factory Method moves object creation out of client code and lets subclasses decide **which implementation to instantiate**.

## Classes in this package
- `Transport` - product interface
- `TrainTransport`, `ShipTransport`, `TruckTransport` - concrete products
- `LogisticsFactory` - creator
- `RailLogisticsFactory`, `SeaLogisticsFactory`, `RoadLogisticsFactory` - concrete creators
- `Main` - demo

## Schema
```text
Client -> LogisticsFactory.planDelivery()
              |
              +--> createTransport()
                      |
          +-----------+------------+
          |                        |
 RailLogisticsFactory      SeaLogisticsFactory
          |                        |
  TrainTransport            ShipTransport
```

## Real use cases

### 1. Export by channel in logistics software
A shipping platform may choose different transport implementations depending on route type:
- road deliveries
- rail shipments
- sea freight

The client code asks the factory to plan delivery and does not create `TruckTransport` or `ShipTransport` directly.

### 2. Environment-specific service creation
In applications, factories are often used to create different integrations based on environment or provider:
- `AwsStorageFactory`
- `AzureStorageFactory`
- `LocalStorageFactory`

```text
Application -> StorageFactory -> createClient() -> concrete provider client
```

## Notes
- This package uses the Factory Method style: the base creator defines the workflow, while subclasses decide the created product.

