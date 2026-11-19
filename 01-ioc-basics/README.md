# 01 · IoC basics

## Goal
Wire a small money-transfer app by hand in plain Java, then hand the wiring to the Spring container and see exactly what it automates.

## Concepts
- **Dependency Injection / Inversion of Control**: classes ask for their collaborators through the constructor instead of building them. This is what makes Spring code swappable and testable.
- **Beans and the `ApplicationContext`**: the container builds objects in dependency order, shares singletons and runs lifecycle callbacks. That is wiring you'd otherwise write by hand in `main`.
- **`@Configuration` + `@Bean` vs component scanning**: explicit wiring vs "find my classes".
- **`@ConditionalOnProperty`**: choosing an implementation from config. It is the same mechanism Spring Boot's auto-configuration is built on.
- **Scopes and lifecycle**: singleton vs prototype, `@PostConstruct` / `@PreDestroy`, and why shared singletons must be stateless or thread-safe.

Domain terms (Account, Transfer, Sender, Fee policy, …) are defined in [`../CONTEXT.md`](../CONTEXT.md).

## Source
- [Spring Framework reference: Introduction to the IoC container](https://docs.spring.io/spring-framework/reference/core/beans/introduction.html)
- [Spring Framework reference: Java-based container configuration](https://docs.spring.io/spring-framework/reference/core/beans/java.html) (its `TransferService` / `AccountRepository` example is where this project's shape comes from)
- [Spring Framework reference: Dependency injection](https://docs.spring.io/spring-framework/reference/core/beans/dependencies/factory-collaborators.html)
- [Spring Boot reference: Creating your own auto-configuration](https://docs.spring.io/spring-boot/reference/features/developing-auto-configuration.html) (for milestone 4's `@ConditionalOnProperty`)

## Milestones
Each milestone is one feature branch and one PR.

- [ ] **1. Account and repository**: an immutable `Account` record (validated, with `withBalance`) and an in-memory `AccountRepository` (`findById` returning `Optional`, `save`). Plain JUnit tests, no Spring.
- [ ] **2. Transfers wired by hand**: the `FeePolicy` interface with `FlatFeePolicy` ($1.50) and `NoFeePolicy`, and a `TransferService` enforcing the rules (sender pays the fee, insufficient funds, same account, non-positive amount, currency mismatch, all-or-nothing). A `HandWiredApp` `main` builds everything with `new`. Tests pass fakes in through constructors.
- [ ] **3. The Spring container, explicitly**: an `AppConfig` with `@Configuration` and `@Bean`, started by `AnnotationConfigApplicationContext`. Prove beans are singletons, and swap the fee policy in one place.
- [ ] **4. Spring Boot**: component scanning (`@Service`, `@Repository`), fee policy chosen by `bank.fees.policy=flat|none` via `@ConditionalOnProperty`, a `CommandLineRunner` demo transfer, and a `@SpringBootTest` for each property value. Compare with milestones 2 and 3.
- [ ] **5. Scopes and lifecycle**: seed accounts in `@PostConstruct`, log on `@PreDestroy`, and run a singleton vs prototype experiment. Also: why a stateful singleton is a bug waiting for a second thread.

## How to run
```bash
./mvnw spring-boot:run   # run the app
./mvnw test              # run the tests
```

## What I learned
<!-- Filled in by /wrap-up, in my own words. -->
