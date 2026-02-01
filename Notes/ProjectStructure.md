# jrw — Project Directory Structure & Architecture

This document defines the **physical project layout** and the **architectural intent** behind `jrw`.

It is meant to be used alongside:

* `jrw-cli-design.md` (CLI contract)

This structure is intentionally **small, modular, and extensible**.

---

## 1. Project Goals (Why This Structure Exists)

`jrw` is designed to:

* Be OS-independent (Windows, Linux, macOS, Android)
* Ship initially as a runnable **JAR**
* Later evolve into native executables or embedded tools
* Support future features like:

  * `less` / paging
  * `more`
  * regex search
  * text transformations
* Remain clean and maintainable as it grows

This means:

> **Core logic must never depend on the CLI or the OS.**

---

## 2. High-Level Layout

```
jrw/
├── README.md
├── jrw-cli-design.md
├── jrw-project-structure.md
├── build.gradle / pom.xml
└── src/
    └── main/
        └── java/
            └── jrw/
```

Everything important lives under the `jrw` package.

---

## 3. Core Package Structure

```
jrw/
├── Main.java
├── cli/
├── core/
├── input/
├── output/
├── module/
├── text/
└── util/
```

Each package has **one clear responsibility**.

---

## 4. Main.java — The Launcher

**Role:**

* JVM entry point
* Delegates immediately to CLI layer
* Contains no business logic

Think of this as the **power button**, not the engine.

This makes it easy to replace later with:

* Native launchers
* Embedded entry points
* Platform-specific wrappers

---

## 5. cli/ — Command-Line Adapter

```
cli/
├── CliParser
├── CliConfig
├── HelpPrinter
└── CliRunner
```

**Responsibilities:**

* Parse command-line arguments
* Validate flag combinations
* Print help text
* Translate CLI options into core configuration

**Important rule:**

> Nothing in `core/` knows the CLI exists.

---

## 6. core/ — The Engine (Sacred)

```
core/
├── Engine
├── ExecutionPlan
└── JrwException
```

**Responsibilities:**

* Orchestrates input → processing → output
* Contains no CLI, no flags, no OS assumptions
* Designed to be embeddable as a library

This is the **heart of jrw**.

---

## 7. input/ — Where Text Comes From

```
input/
├── InputSource
├── StringInputSource
├── FileInputSource
└── StdInInputSource
```

All input sources share a common abstraction.

Design rule:

> Every input eventually becomes readable text.

Future extensions fit naturally here.

---

## 8. output/ — Where Text Goes

```
output/
├── OutputTarget
├── ConsoleOutput
├── FileOverwriteOutput
└── FileAppendOutput
```

Supports:

* Multiple outputs per execution
* Clean separation of responsibilities

Future outputs (network, UI, buffers) require no redesign.

---

## 9. text/ — Text as a First-Class Concept

```
text/
├── TextData
└── TextReader
```

Even if text is simple today, this package exists to support:

* Paging
* Chunked reading
* Highlighting
* Regex filtering

This avoids spreading `String` everywhere prematurely.

---

## 10. module/ — The Plugin Door

```
module/
├── JrwModule
├── ModuleRegistry
└── builtin/
    └── PassThroughModule
```

**Purpose:**

* Each behavior = a module
* Core engine executes modules
* New functionality means adding modules, not rewriting core

This enables future tools like:

* Less / More
* Grep-style search
* Regex replace
* Word count

---

## 11. util/ — Shared Utilities

```
util/
├── FileUtils
├── CharsetUtils
└── ValidationUtils
```

Rules:

* No orchestration logic
* No CLI or engine decisions
* Helpers only

---

## 12. Why This Structure Scales

This layout allows:

* One runnable JAR today
* Native executables tomorrow
* Embedded usage without refactoring
* Feature growth without architectural debt

This is how long-lived developer tools are structured.

---

## 13. Status

This document **freezes the project structure**.

Implementation must respect these boundaries.
