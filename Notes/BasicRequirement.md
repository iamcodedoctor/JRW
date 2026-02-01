# jrw — Java Read Write Utility

This document defines the **Command Line Interface (CLI) contract** and design decisions for `jrw`.

`jrw` is an **OS-independent, JVM-based text manipulation tool** designed to work consistently across:

* Windows
* Linux
* macOS
* Android
* Embedded / plugin environments

The goal is **clarity, predictability, and composability**.

---

## Design Philosophy

* Explicit over clever
* Order-independent flags
* JVM abstractions only (no OS assumptions)
* STDIN / STDOUT as first-class citizens
* Suitable for CLI usage **and** embedding

Text always flows as:

```
INPUT → PROCESSING → OUTPUT
```

---

## Core Command

```
jrw
```

---

## Input Options (Choose ONE)

### Inline String Input

```
-s <string>
```

Uses the provided string as input.

---

### File Input

```
-i <file>
```

Reads input text from the specified file.

---

### Standard Input (Default)

If no input flag is provided, `jrw` reads from **STDIN**.

Example:

```
echo "hello" | jrw -p
```

---

## Output Options (Choose AT LEAST ONE)

### Print to Console

```
-p
```

Writes output to STDOUT.

---

### Write to File (Overwrite)

```
-o <file>
```

Writes output to the specified file.
If the file exists, it is **overwritten**.
If it does not exist, it is created.

---

### Append to File

```
-a <file>
```

Appends output to the specified file.
If the file does not exist, it is created.

---

## Utility Option

### Help

```
-h
```

Prints the help guide and exits.

---

## Rules & Constraints

### Input Rules

* Only **one** input option may be specified
* `-s` and `-i` are mutually exclusive
* If no input flag is provided → STDIN is used

---

### Output Rules

* At least **one** output option is required
* `-o` and `-a` are mutually exclusive
* `-p` may be combined with `-o` or `-a`

---

## Flag Ordering

* Flag order **does not matter**
* Each flag is explicit and self-contained

Correct:

```
jrw -p -i input.txt -o output.txt
jrw -i input.txt -o output.txt -p
```

Incorrect (not supported):

```
jrw -pio input.txt output.txt
```

---

## Example Usages

```bash
jrw -s "hello world" -p
jrw -i input.txt -o output.txt
jrw -i input.txt -p -a log.txt
echo "hi" | jrw -p
```

---

## Error Philosophy

* Conflicting flags → fail with clear message
* Missing required output → fail loudly
* IO errors → meaningful, user-readable errors
* No silent failures

---

## OS Independence Guarantees

`jrw` guarantees OS independence by:

* Never assuming path formats
* Never invoking OS-specific commands
* Using Java standard IO / NIO only
* Explicit UTF-8 encoding
* Relying exclusively on STDIN / STDOUT

---

## Architectural Direction (Future)

Internally, `jrw` is designed to allow:

* A reusable **core engine** (no CLI assumptions)
* A thin **CLI adapter** layer
* Future embedding as a library or plugin

---

## Status

This document **freezes the CLI contract**.

Implementation must conform to this specification.
