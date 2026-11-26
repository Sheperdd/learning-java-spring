---
name: new-project
description: Start the next learning project from ROADMAP.md (or a named one). Teaches the concepts, generates a Maven Spring Boot project via Spring Initializr, writes its README and Learning output-style settings, and splits it into milestones. Use when the user says "new project", "next project", "start project NN", or /new-project.
argument-hint: "[roadmap item or project idea]"
---

# New learning project

Work from the repo root (`learning-java-spring/`). Follow the teaching contract in the root `AGENTS.md`.

## 1. Pick the project
- Argument given: use it, and slot it into `ROADMAP.md` if it isn't there yet.
- No argument: take the first unticked Spring-track item in `ROADMAP.md`.
  - Check `learning-records/` for gaps that suggest a different order. If so, propose the change and confirm.
- Check the paired **delivery-track** item. If its prerequisite isn't done (e.g. D0 before project 01), do that first.
- Find the best online source for this project: an official spring.io guide first, Baeldung second. Use Context7 or the web, not memory. Note it for the README and `RESOURCES.md`.

## 2. Design before code
- Run a **light** `/grill-with-docs` on the design: about 3–6 questions about the domain and choices. Example: "should a transfer between the same account be allowed?"
  - Record domain terms via domain-modeling.
- Teach the core concept before generating anything: what / why / how, plus what Spring does for you vs plain Java.
  - Offer a `/teach` lesson if the concept is big.

## 3. Generate the project
- Folder: `NN-short-name/` at the root.
- **First project ever:** have Shane click through https://start.spring.io himself. Explain each option: Maven, Java 25, group/artifact, the reverse-domain package name, and each dependency and why it's needed.
- **Later projects:** use curl. Pick only the dependencies this project needs, and explain each one:
  ```bash
  curl -s https://start.spring.io/starter.zip \
    -d type=maven-project -d language=java -d javaVersion=25 \
    -d groupId=io.github.<github-username> -d artifactId=<short-name> \
    -d name=<short-name> -d packageName=io.github.<github-username>.<shortname> \
    -d dependencies=<comma-separated ids> -o <session-scratchpad-dir>/<short-name>.zip
  ```
  - Unzip into `NN-short-name/`.
  - Don't pass `bootVersion`, so Initializr gives the current release.
  - If Java 25 is rejected, check https://start.spring.io/metadata/client for supported values.
- If `java -version` isn't 25, stop and help Shane fix it in **Git Bash**:
  - Install with `winget install EclipseAdoptium.Temurin.25.JDK`.
  - Set `JAVA_HOME` and prepend `$JAVA_HOME/bin` to `PATH` in `~/.bashrc`. This is needed because Oracle Java 23's `javapath` is on the system PATH.
- Write `NN-short-name/.Codex/settings.json`:
  ```json
  {
    "outputStyle": "Learning",
    "permissions": { "additionalDirectories": ["../"] }
  }
  ```
  `additionalDirectories` gives sessions started in the sub-project access to the root's `@MISSION.md`/`@ROADMAP.md` imports and the teaching-workspace files. Without it they're blocked as "outside the working directory".
- Run `./mvnw -q verify` once to prove the skeleton builds. Explain what the wrapper is and why there's no global Maven.

## 4. README
Write `NN-short-name/README.md`, using `/create-readme` as a base and keeping it short, with these sections:
- **Goal**: one sentence.
- **Concepts**: what this project teaches, and why it's a Spring strength.
- **Source**: a link to the guide or doc it's based on.
- **Milestones**: see step 5.
- **How to run**: `./mvnw spring-boot:run` and `./mvnw test`.
- **What I learned**: left empty and filled in by `/wrap-up`, in Shane's words.

## 5. Milestones
- Split the work into 3–6 small vertical slices. Each slice should be finishable in one session and shippable as one PR.
- **If D1 is done** (GitHub Issues in use): turn them into issues with `/to-issues`. Run `/setup-matt-pocock-skills` first if it hasn't been run.
- **Otherwise:** a checklist in the README.

## 6. Hand over
- Show the first milestone.
- Have Shane create the feature branch himself (explain `git switch -c feat/NN-...`).
- Leave the first `TODO(human)`.
