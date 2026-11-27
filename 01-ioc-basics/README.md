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
- [Spring Boot reference: Using the ApplicationRunner or CommandLineRunner](https://docs.spring.io/spring-boot/reference/features/spring-application.html) (milestone 4's `TransferDemo`)

## Milestones
Each milestone is one feature branch and one PR.

- [x] **1. Account and repository** (PR #2): an immutable `Account` record (validated, with `withBalance`) and an in-memory `AccountRepository` (`findById` returning `Optional`, `save`). Plain JUnit tests, no Spring.
- [x] **2. Transfers wired by hand** (PR #5): the `FeePolicy` interface with `FlatFeePolicy` ($1.50) and `NoFeePolicy`, and a `TransferService` enforcing the rules (sender pays the fee, insufficient funds, same account, non-positive amount, currency mismatch, all-or-nothing). A `HandWiredApp` `main` builds everything with `new`. Tests pass fakes in through constructors.
- [x] **3. The Spring container, explicitly** (PR #7): an `AppConfig` with `@Configuration` and `@Bean`, started by `AnnotationConfigApplicationContext`. Prove beans are singletons, and swap the fee policy in one place.
- [x] **4. Spring Boot** (PR #10): component scanning (`@Service`, `@Repository`), fee policy chosen by `bank.fees.policy=flat|none` via `@ConditionalOnProperty`, a `CommandLineRunner` demo transfer, and a `@SpringBootTest` for each property value. Compare with milestones 2 and 3.
- [ ] **5. Scopes and lifecycle**: seed accounts in `@PostConstruct`, log on `@PreDestroy`, and run a singleton vs prototype experiment. Also: why a stateful singleton is a bug waiting for a second thread. That pays off the `ponytail:` plain-`HashMap` shortcut in `AccountRepository`, so fix it here.

## How to run
```bash
./mvnw spring-boot:run   # run the app (prints the demo transfer)
./mvnw test              # run the tests
```
`bank.fees.policy` (`flat` or `none`) is required. The app refuses to start without it. Turn the startup demo off with `bank.demo.enabled=false`.

## What I learned
<!-- Filled in by /wrap-up, in my own words. -->
**Milestone 1**
- Never compare money with `equals`. `BigDecimal.equals` also compares scale, so `5.00` isn't equal to `5`. Use `compareTo` (and `isEqualByComparingTo` in tests), and compare its result against 0, not `== 1`.
- Validate in the record's compact constructor. Every way of creating an `Account`, including `withBalance`, passes through it, so an invalid account can't exist.
- A test has to be able to fail. `assertThat(x)` with nothing chained checks nothing. Test right next to a boundary, on both sides (`0.00` accepted, `-0.01` rejected).
- Test state must not be shared. `static` means one copy per class, and JUnit creates a fresh test instance per `@Test`, so the repository goes in an instance field.

**Milestone 2**
- A class receives its collaborators through the constructor; it doesn't `new` them. Adding a fee policy means one new class that implements `FeePolicy` and one changed line in `HandWiredApp`. `TransferService` doesn't change.
- Validate before mutating. Every rule is checked before anything is saved, so a refused transfer leaves both balances untouched.
- `==` on objects compares memory location, not contents. Strings need `.equals`. The test uses `new String("A-001")` because two literals share one object and would hide the bug.
- The caller sends IDs and the service loads the current `Account` from the repository. An `Account` object the caller holds is a stale snapshot.

**Milestone 3**
- `@Bean` methods still use `new`. The difference is that Spring calls them: it builds the beans in dependency order and gives every class the same instance. With two repositories you wouldn't know which one your account is in, and balances would silently disagree.
- Collaborators go in as `@Bean` method **parameters** (`transferService(AccountRepository repo, FeePolicy fees)`), not as calls to other `@Bean` methods. That's constructor injection again, and it stays correct even without Spring's `@Configuration` subclass.
- Ask for a bean by type (`getBean(TransferService.class)`), not by name. A typo becomes a compile error and there's no cast.
- The context owns the beans, so close it. Use try-with-resources: `close()` runs even if an exception is thrown.
- At three classes, `new` in `main` is honestly simpler. The container pays off as the object graph grows, and later when Spring wraps beans (`@Transactional`), picks them from config, and runs lifecycle callbacks.

**Milestone 4**
- `@Service` and `@Component` are only labels. `@SpringBootApplication` turns on component scanning, which reads them, from its own package downward. My `TransferDemo` didn't run until I added `@Component`, because `implements CommandLineRunner` doesn't make it a bean.
- Each bean needs exactly one source. While `AppConfig` existed, its `@Bean transferService()` silently replaced my `@Service` class (same name, same type), so the annotation did nothing. That's why `AppConfig` was deleted, not kept beside scanning.
- Two `FeePolicy` beans means Spring can't choose. `@ConditionalOnProperty` lets each policy say when it applies, so `bank.fees.policy` picks one, and a third policy is just one new class.
- No default fee policy, on purpose. Whether a customer pays a fee is a business decision, so a missing property should stop startup (fail fast), not guess.
- `CommandLineRunner`s also run in `@SpringBootTest`. Startup side effects need an off switch (`bank.demo.enabled`), so tests only depend on data they set up themselves.
- Test what the customer sees: after a 50.00 transfer, Alice has 48.50 with `flat` and 50.00 with `none`. That proves the whole chain, property → bean → `TransferService` → balance. A type check would pass even if the fee were never charged.

**M2 → M3 → M4: who wires the app?**

| | M2 `HandWiredApp` | M3 `AppConfig` | M4 Spring Boot |
|---|---|---|---|
| Who calls `new` | me, in `main` | Spring, via my `@Bean` methods | Spring, via the constructor of each scanned class |
| Where beans are listed | `main` | `AppConfig` | nowhere: annotations on the classes |
| Choosing the fee policy | edit a line, recompile | edit `AppConfig`, recompile | change `bank.fees.policy`, no rebuild |
| Adding a fee policy | new class + edit `main` | new class + edit `AppConfig` | new class only |
| Who starts the context | n/a | me (`new AnnotationConfigApplicationContext`) | `SpringApplication.run` |
