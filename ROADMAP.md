# Roadmap

There are two tracks, and they run **together**: every Spring project is also shipped through the delivery track. Tick items off in `/wrap-up`. Real progress (what Shane actually knows) lives in `learning-records/`. Claude may reorder, split or swap projects when the learning records show a gap, but it confirms with Shane first.

## Spring track (banking theme where it fits)
Each project teaches one thing that makes Spring worth using.

- [ ] **01-ioc-basics**: wire a small app by hand in plain Java, then let the Spring container do it. *Why Spring exists:* IoC/DI, beans, scopes, `@Configuration`, the ApplicationContext.
- [ ] **02-accounts-api**: an accounts REST API (based on the spring.io "Building REST services with Spring" guide). Covers `@RestController`, records as DTOs, Bean Validation, `ProblemDetail` errors and `BigDecimal` money.
- [ ] **03-accounts-persistence**: Spring Data JPA + Postgres (Docker) + Flyway. Covers `@Transactional` money transfers, optimistic locking (`@Version`) and the N+1 problem.
- [ ] **04-testing-deep-dive**: JUnit 5, AssertJ, `@WebMvcTest`, `@DataJpaTest`, Testcontainers and test-first with `/tdd`. *Tests are what let a team ship twice a week.*
- [ ] **05-config-and-observability**: `@ConfigurationProperties`, profiles, Actuator health/readiness probes, metrics, structured logs.
- [ ] **06-security**: Spring Security. Start with the filter chain, then an OAuth2 resource server with JWT and method security.
- [ ] **07-events-and-aop**: an audit log built three ways: application events, `@Async`, and a custom aspect. *This is where you see the proxies.*
- [ ] **08-fx-client**: call an external FX-rate API with `RestClient`. Covers caching, timeouts/retries (resilience) and idempotency keys for payments.
- [ ] **09-kafka-transfers**: two services talking through Spring for Apache Kafka (transaction events). Covers event-driven design and the outbox idea.
- [ ] **10-k8s-and-azure**: run the services on a local Kubernetes (kind/Docker Desktop). Map them onto Azure concepts (AKS, App Service, Event Hubs).
- [ ] **11-modulith** (stretch): restructure a multi-feature app with Spring Modulith (a modular monolith).

## Delivery track (Git, GitHub, CI/CD)
- [x] **D0, before 01:**
  - What git is, then `git init`/status/add/commit.
  - Connect `gh auth login` and `gh repo create`, then push the root repo.
  - Why `.gitignore` matters.
- [ ] **D1, with 01:**
  - The professional loop: issue → feature branch → small Conventional Commits → PR (template + self-review) → squash-merge → delete the branch.
- [ ] **D2, with 02:**
  - First GitHub Actions CI workflow (`./mvnw verify` on PR, path filters per sub-project).
  - Branch protection on `main` with required checks.
- [ ] **D3, with 03–04:**
  - Tests and integration tests (Testcontainers in CI) become the merge gate, with test reports.
  - Dependabot and CodeQL: why banks scan everything.
- [ ] **D4, with 05:**
  - CD: `spring-boot:build-image` → push to GHCR → deploy on merge to a free host (Render or Azure, picked then).
  - Actuator health check as the deploy gate.
- [ ] **D5, with 06+:**
  - GitHub Environments (staging → prod with approval) and secrets management.
  - Tags/releases, a rollback drill, trunk-based development and feature flags.

## Ideas parking lot
<!-- Projects found online or suggested later go here before being slotted in. -->
