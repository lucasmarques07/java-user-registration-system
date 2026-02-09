# Event Registration System (Java)

Small Java project created to practice collections, streams and service layer separation using an event registration domain

## Concepts practiced
- Object-Oriented Programming
- Enums
- Sets and Lists
- equals and hashCode
- Java Streams API
- Service layer separation
- Custom domain exceptions

## Business rules
- An event must have a valid name and category
- Participants must have name, email and valid age
- A participant is uniquely identified by email
- Duplicate participants are not allowed in the same event
- Reports must handle empty data safely

## Architecture overview
- **Entities** represent domain objects (`Event`, `Participant`)
- **Enums** define valid event categories
- **Services** are stateless and process domain data
  - `RegistersService`: input handling and validation
  - `EventService`: business rules and reports
- **Exceptions** encapsulate domain validation errors

## Implemented features
- Event registration by category
- Participant registration per event
- Total unique participants across all events
- Number of participants per category
- Most popular event
- Filtered participant emails by category and age
- Average age calculation using streams

## Technologies
- Java
- Java Collections (List, Set, Map)
- Java Streams
- Custom Exceptions
- Enums

## How to run
1. Clone the repository
2. Open the project in NetBeans
3. Run `Program.java`
4. Follow the console instructions

## What I learned
- When to use Set instead of List
- How equals and hashCode affect collections
- Designing stateless service classes
- Using streams for aggregation and filtering
- Separating input logic from business logic
- Writing safer code with domain exceptions
