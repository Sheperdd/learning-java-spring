# Mission: Java + Spring Boot (and shipping it) for an EQ Bank internship

## Why
Shane is preparing for a software engineering internship at EQ Bank, a digital bank whose teams build Java/Spring Boot microservices and ship to prod about twice a week. He wants to arrive able to contribute to that codebase and its delivery workflow from week one.

## Success looks like
- Builds a Spring Boot REST service on his own, with validation, error handling, persistence (JPA + Flyway) and tests (JUnit 5, slices, Testcontainers).
- Can explain *why* Spring does what it does: IoC/DI, auto-configuration, proxies behind `@Transactional`, and the bean lifecycle.
- Handles money and transactions correctly: `BigDecimal`, transfer atomicity, optimistic locking, idempotency.
- Works the professional git/GitHub flow without help: feature branch → small commits → PR → review → squash-merge.
- Writes and debugs a GitHub Actions pipeline that tests every PR and deploys a container to a real environment on merge.
- Has a working understanding of event-driven services (Kafka), Docker and the basics of Kubernetes/Azure.

## Constraints
- Learning first: Shane writes the meaningful code himself and Claude teaches.
- Small projects, one concept per project, each finishable in a few sessions.
- Windows 11 machine. Java 25 LTS, Maven wrapper, Docker Desktop, git + gh CLI.
- Free-tier services only for deployment.

## Out of scope (for now)
- Frontend work (EQ uses Angular; revisit only if the internship role is full-stack).
- Spring WebFlux/reactive, unless a project specifically needs it.
- Deep Kubernetes operations or certification-level Azure.
