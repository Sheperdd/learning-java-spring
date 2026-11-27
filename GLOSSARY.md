# Java Spring + Delivery Glossary

The canonical terms for this workspace, covering Spring, Java, git and CI/CD. A term is added only once Shane can use it correctly (see `/wrap-up`).

## Terms
### Git

**Commit**:
A permanent snapshot of the **staging area**. It records an author, a message and its parent commit, and it is identified by a hash.
_Avoid_: save, version

**Staging area**:
The proposed next **commit**: the version of every tracked file that `git commit` will snapshot. `git add` updates it.
_Avoid_: index (git's internal name, fine to recognise), cache

### GitHub

**Pull request**:
A request to merge a branch into `main`, shown as a reviewable diff with discussion. Every change in this workspace lands through one.
_Avoid_: PR is fine as an abbreviation; avoid "merge request" (GitLab's name)

**Squash merge**:
Merging a **pull request** by combining all its **commits** into a single new **commit** on `main`, whose message is the PR title.
_Avoid_: squash commit (that's the result, not the action)

### Java

**Immutable object**:
An object whose state can't change after it's constructed. To "change" it, you build a new one (e.g. a record and its `withBalance` copy).
_Avoid_: read-only, constant (a `final` field holding a mutable object is not immutable)

### Design

**Dependency injection**:
A class receives its collaborators (e.g. a `FeePolicy`) from outside, through its constructor, instead of creating them with `new` itself. In this workspace, injection is always constructor injection.
_Avoid_: DI is fine as an abbreviation; avoid "autowiring" (Spring's mechanism for it, not the idea)

**Bean**:
An object that the Spring container creates, wires and manages. It's declared either by a `@Bean` method in a `@Configuration` class or by a class found through **component scanning**. Its default scope is singleton: one shared instance per container. Each bean should have exactly one source. If a `@Bean` method and a scanned class share a name and type, the `@Bean` method silently wins.
_Avoid_: component (that's one way to declare a bean, via scanning), Java bean (the old getter/setter convention, unrelated)

**ApplicationContext**:
Spring's container. It reads configuration (`@Configuration` classes and **component scanning**), builds the **beans** in dependency order, hands them out (`getBean`), and runs their shutdown callbacks on `close()`. In Spring Boot, `SpringApplication.run` creates it for you.
_Avoid_: bean factory (the lower-level interface it extends), Spring (too vague)

**Component scanning**:
Spring walking the package of the `@SpringBootApplication` class, and every sub-package, and registering a **bean** for each class marked `@Component` or a stereotype built on it (`@Service`, `@Repository`, `@Configuration`). A class outside that package tree, or without the annotation, is never found.
_Avoid_: auto-wiring (that's injecting collaborators), auto-configuration (Boot's own conditional beans)

**Conditional bean**:
A **bean** that is registered only if a condition holds when the **ApplicationContext** starts, e.g. `@ConditionalOnProperty(name = "bank.fees.policy", havingValue = "flat")`. The condition is checked once, at startup, so changing the property means starting a new context.
_Avoid_: feature toggle (a use of it, not the mechanism), profile (a different, coarser switch)

**Fail fast**:
Refusing to start, or to continue, the moment required input is missing or invalid, instead of guessing a default. In this workspace a missing `bank.fees.policy` stops startup, because the fee is a business rule.
_Avoid_: crash (a fail-fast stop is deliberate and has a clear message), strict mode

**Composition root**:
The single place where the app's objects are created and connected, in dependency order, and so where choices like the fee policy are made. In 01 M2 it is `HandWiredApp.main`. In M3 it is the `ApplicationContext` reading `AppConfig`. From M4 on, Boot's **ApplicationContext** builds it from **component scanning** and `application.properties`.
_Avoid_: wiring code, setup, bootstrap
