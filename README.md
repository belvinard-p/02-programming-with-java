# 🎓 Programming with Java — Advanced OOP, Defensive Programming & File I/O

[![Java](https://img.shields.io/badge/Java-SE_17+-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-Educational-blue.svg)]()
[![Status](https://img.shields.io/badge/Status-In_Progress-yellow.svg)]()

> An intermediate-to-advanced Java course covering advanced OOP design, exception handling, and file I/O operations — culminating in a full-scale digital banking platform with 35+ classes.

---

## 📋 Table of Contents

- [Overview](#overview)
- [Course Structure](#course-structure)
- [Learning Objectives](#learning-objectives)
- [Prerequisites](#prerequisites)
- [Setup & Installation](#setup--installation)
- [Module Breakdown](#module-breakdown)
- [Final Project](#final-project)
- [Running the Applications](#running-the-applications)
- [Key Concepts Covered](#key-concepts-covered)
- [Project Structure](#project-structure)
- [Best Practices Demonstrated](#best-practices-demonstrated)
- [Future Enhancements](#future-enhancements)
- [Progress Tracking](#progress-tracking)

---

## 🎯 Overview

This repository builds upon the foundational Java course (`01-java-introduction-to-software-development`) and dives into advanced object-oriented design, robust error handling, and file-based data persistence. The course follows a progressive exercise roadmap organized into 4 levels of increasing complexity, all within a real-world **Banking Digital System** context.

**Course Duration**: Self-paced
**Skill Level**: Intermediate to Advanced
**Language**: Java SE 17+
**IDE**: IntelliJ IDEA (recommended)
**Total Exercises**: 14 exercises across 4 levels + Level 4 integrative project

---

## 📚 Course Structure

```
02-programing-with-java/
├── module-1-advanced-opp/             # Advanced OOP: abstraction, interfaces, hierarchies
├── module-2-defensive-programming/    # Exception handling & custom exceptions
├── module-3-file-handling/            # File I/O, persistence & Level 4 integration
├── AdavancedOOPExercises.md           # Complete exercise roadmap (Levels 1–4)
├── COURSEOBJECTIVE.md                 # Course-level learning objectives
└── SUMMARY.md                         # Course summary & key takeaways
```

---

## 🎓 Learning Objectives

By completing this course, you will:

- ✅ Design **abstract classes and interfaces** for scalable architectures
- ✅ Resolve the **diamond problem** using interface composition
- ✅ Apply **SOLID principles** (SRP, ISP, OCP, LSP, DIP)
- ✅ Implement **custom exception hierarchies** for robust error handling
- ✅ Use **try-catch-finally** and **try-with-resources** for defensive programming
- ✅ Perform **file I/O operations** (read, write, append, backup, restore)
- ✅ Build a **complete banking platform** integrating all concepts (35+ classes)
- ✅ Write **production-quality code** with Logger, constants, and clean code patterns

---

## 🔧 Prerequisites

### Required
- **Completed**: `01-java-introduction-to-software-development` (or equivalent)
- **Java Development Kit (JDK)**: Version 17 or higher
- **IDE**: IntelliJ IDEA Community Edition (or Eclipse/VS Code)

### Knowledge Required
- Java classes, objects, constructors
- Inheritance, polymorphism, encapsulation
- Control flow and basic data structures
- `java.util.logging.Logger` basics

---

## 🚀 Setup & Installation

### 1. Verify Java Installation

```bash
java -version
# Should output: java version "17.x.x" or higher
```

### 2. Clone & Open

```bash
git clone <repository-url>
cd 02-programing-with-java
```

**IntelliJ IDEA:**
1. `File → Open`
2. Navigate to `02-programing-with-java`
3. Select the folder and click `Open`

### 3. Verify Setup

```bash
cd module-1-advanced-opp/src
javac advancedoop/level1/exercise1/*.java
java advancedoop.level1.exercise1.TestBankAccount
```

---

## 📖 Module Breakdown

### **Module 1: Advanced Object-Oriented Programming** 🏗️

**Focus**: Abstraction, interfaces, class hierarchies, polymorphism, and SOLID principles

**Topics Covered:**
- Abstract classes and abstract methods
- Interface definition and implementation
- Multiple interface implementation
- Interface inheritance and extension
- Class hierarchy design (multi-level)
- Diamond problem resolution
- Polymorphic collections and processing
- Single Responsibility & Interface Segregation Principles

**Exercise Progression:**

| Level | Exercise | Topic | Key Classes |
|-------|----------|-------|-------------|
| 1 | 1.1 | Abstract Account Foundation | `BankAccount`, `SavingsAccount`, `CheckingAccount` |
| 1 | 1.2 | Customer Account Abstraction | `Customer`, `IndividualCustomer`, `BusinessCustomer`, `PremiumCustomer` |
| 1 | 1.3 | Transaction Capability Interfaces | `Transferable`, `BillPayable`, `Investable`, service classes |
| 2 | 2.1 | Complete Account Hierarchy | `Account`, `InterestBearingAccount`, `MoneyMarketAccount`, `CertificateOfDeposit` |
| 2 | 2.2 | Transaction Interface System | `Withdrawable`, `Depositable`, `CheckWritable`, `WireTransferable` |
| 2 | 2.3 | Diamond Problem Resolution | `SavingsCapable`, `CheckingCapable`, `RewardCapable`, hybrid accounts |
| 2 | 2.4 | Polymorphism in Banking | `BankingProduct`, `TransactionProcessor`, polymorphic arrays |

**Skills Acquired:**
- Designing scalable abstract class hierarchies
- Applying interface segregation for flexible capabilities
- Solving multiple inheritance with interface composition
- Processing diverse types uniformly via polymorphism

📄 [Module 1 Objectives](./module-1-advanced-opp/MODULEOBJECTIVE.md)
📄 [Module 1 Summary](./module-1-advanced-opp/MODULESUMMARY.md)
📄 [Module 1 Exercises](./module-1-advanced-opp/Module1-BankingExercises.md)

---

### **Module 2: Defensive Programming** 🛡️

**Focus**: Exception handling, custom exceptions, retry patterns, and fault-tolerant design

**Topics Covered:**
- Checked vs unchecked exceptions
- Try-catch-finally blocks
- Custom exception hierarchies
- Exception propagation with `throws`
- Retry pattern for transient errors (network)
- Safe methods (boolean return instead of throwing)
- Batch processing with error collection
- Pattern matching with `instanceof`

**Key Exercise:**

| Level | Exercise | Topic | Key Classes |
|-------|----------|-------|-------------|
| 3 | 3.5 | Exception Handling in Banking | `BankingException`, `RobustBankAccount`, `BankingService` |

**Exception Hierarchy Implemented:**
```
Exception
  └── BankingException (base, with errorCode)
      ├── InsufficientFundsException
      ├── AccountClosedException
      ├── InvalidAccountException
      ├── TransactionLimitExceededException
      └── NetworkException (with retryable flag)
```

**Skills Acquired:**
- Creating domain-specific exception hierarchies
- Implementing retry logic for network errors
- Designing safe wrapper methods
- Batch processing with error aggregation
- Professional logging at appropriate severity levels

📄 [Module 2 Objectives](./module-2-defensive-programming/MODULEOBJECTIVE.md)
📄 [Module 2 Exercise Journal](./module-2-defensive-programming/module-2-template.md)

---

### **Module 3: File Handling** 📁

**Focus**: File I/O operations, data persistence, transaction logging, backup/restore

**Topics Covered:**
- `BufferedWriter` / `BufferedReader` with `java.nio.file`
- Try-with-resources for automatic resource management
- Write mode vs append mode (`StandardOpenOption.APPEND`)
- CSV format parsing with `split()` and index constants
- `ArrayList` for dynamic data loading
- Transaction logging and daily report generation
- Backup creation, restoration, and integrity verification
- `String.trim()` for robust parsing

**Key Exercises:**

| Level | Exercise | Topic | Key Classes |
|-------|----------|-------|-------------|
| 3 | 3.6 | File I/O for Banking Persistence | `AccountDataWriter`, `AccountDataReader`, `TransactionLogger`, `BackupManager` |

**File Operations Implemented:**
- `saveAccount()` / `loadAccount()` — single account persistence
- `saveMultipleAccounts()` / `loadMultipleAccounts()` — batch persistence
- `appendTransaction()` / `loadTransactions()` — transaction history
- `logTransaction()` / `generateDailyReport()` — reporting
- `archiveOldLogs()` — log rotation
- `createBackup()` / `restoreFromBackup()` / `verifyBackupIntegrity()` — backup system

**Skills Acquired:**
- Reading and writing files with modern Java NIO API
- Parsing structured data (CSV, pipe-delimited)
- Managing resources with try-with-resources
- Designing backup and restore systems
- Generating reports from transaction logs

📄 [Module 3 Objectives](./module-3-file-handling/MODULEOBJECTIVE.md)
📄 [Module 3 Exercise Journal](./module-3-file-handling/module-3-exercise_journal.md)

---

### **Level 4: Complete Banking Digital Platform** 🏦

**Focus**: Full system integration of all OOP, exception handling, and file I/O concepts

**Project Overview:**

A production-quality digital banking platform with 35+ classes organized into a clean layered architecture. This is the capstone project integrating every concept from Modules 1–3.

**Architecture:**
```
level4/
├── model/              # Domain entities (accounts, customers, transactions)
├── exception/          # Custom exception hierarchy
├── interfaces/         # Capability interfaces (ISP applied)
├── service/            # Business logic layer
├── persistence/        # File I/O and data persistence
├── ui/                 # Console-based user interface
└── Main.java           # Application entry point
```

**Core Features:**
- ✅ 5 account types (Savings, Checking, MoneyMarket, CD, Business)
- ✅ 3 customer types (Individual, Business, Premium)
- ✅ 6 transaction types (Deposit, Withdrawal, Transfer, BillPayment, LoanPayment, Investment)
- ✅ 6 capability interfaces (Depositable, Withdrawable, Transferable, BillPayable, Investable, Reportable)
- ✅ 7 custom exceptions with error codes
- ✅ File-based persistence (accounts, transactions, reports)
- ✅ 11-option interactive console menu
- ✅ Professional logging throughout (`java.util.logging`)

**Class Count by Category:**

| Category | Count | Examples |
|----------|-------|---------|
| Abstract Classes | 4 | `Account`, `InterestBearingAccount`, `Customer`, `Transaction` |
| Account Classes | 5 | `SavingsAccount`, `CheckingAccount`, `BusinessAccount`, `MoneyMarketAccount`, `CertificateOfDeposit` |
| Customer Classes | 3 | `IndividualCustomer`, `BusinessCustomer`, `PremiumCustomer` |
| Transaction Classes | 6 | `DepositTransaction`, `WithdrawalTransaction`, `TransferTransaction`, `BillPaymentTransaction`, `LoanPaymentTransaction`, `InvestmentTransaction` |
| Interfaces | 6 | `Depositable`, `Withdrawable`, `Transferable`, `BillPayable`, `Investable`, `Reportable` |
| Exceptions | 7 | `BankingException`, `InsufficientFundsException`, `InvalidAccountException`, `InvalidAmountException`, `TransactionLimitExceededException`, `AccountClosedException`, `AuthenticationFailedException` |
| Service Classes | 3 | `AccountManager`, `CustomerManager`, `TransactionProcessor` |
| Persistence Classes | 3 | `AccountPersistence`, `TransactionLogger`, `ReportGenerator` |
| UI & Main | 2 | `BankingSystemUI`, `Main` |
| **Total** | **39+** | |

**Design Patterns Applied:**
- Template Method — abstract `Account` and `Transaction` classes
- Strategy — capability interfaces for account behaviors
- Service Layer — `AccountManager`, `TransactionProcessor`
- Composition — `BankingSystemUI` delegates to service classes
- Factory Method — `AccountManager.createAccount()` with type switch

📄 [Full Exercise Roadmap](./AdavancedOOPExercises.md)

---

## 💻 Running the Applications

### Module Exercises

```bash
# Module 1 — Level 1 Exercise 1
cd module-1-advanced-opp/src
javac advancedoop/level1/exercise1/*.java
java advancedoop.level1.exercise1.TestBankAccount

# Module 1 — Level 2 Exercise 4
javac advancedoop/level2/exercise4/*.java
java advancedoop.level2.exercise4.PolymorphismTest

# Module 2 — Exception Handling
cd module-2-defensive-programming/src
javac defenssiveprogramming/level3/exercise5/*.java
java defenssiveprogramming.level3.exercise5.ExceptionHandlingTest

# Module 3 — File I/O
cd module-3-file-handling/src
javac filehandling/*.java
java filehandling.FileIOTest
```

### Level 4 — Banking Platform

```bash
cd module-3-file-handling/src
javac level4/**/*.java level4/*.java
java level4.Main
```

**Expected Output:**
```
=== Banking Digital Platform Started ===

========== BANKING MENU ==========
1. Create Account
2. Deposit
3. Withdrawal
4. Transfer
5. Bill Payment
6. View All Accounts
7. View Transaction History
8. Generate Report
9. Close Account
10. Account Statement
0. Exit & Save
==================================
Choose option:
```

### Using IntelliJ IDEA

1. Navigate to the desired class in Project Explorer
2. Right-click on the file with `main()` method
3. Select **Run '<ClassName>.main()'**
4. View output in the integrated console

---

## 🔑 Key Concepts Covered

### Advanced Object-Oriented Programming
- [x] Abstract classes and abstract methods
- [x] Interface definition and implementation
- [x] Multiple interface implementation
- [x] Interface inheritance (`extends` between interfaces)
- [x] Multi-level class hierarchies
- [x] Diamond problem resolution
- [x] Polymorphic collections and uniform processing
- [x] Method overloading and overriding

### Design Principles (SOLID)
- [x] Single Responsibility Principle (SRP)
- [x] Open/Closed Principle (OCP)
- [x] Liskov Substitution Principle (LSP)
- [x] Interface Segregation Principle (ISP)
- [x] Dependency Inversion Principle (DIP)

### Defensive Programming
- [x] Checked vs unchecked exceptions
- [x] Try-catch-finally blocks
- [x] Try-with-resources (automatic resource management)
- [x] Custom exception hierarchies with error codes
- [x] Exception propagation (`throws`)
- [x] Retry patterns for transient failures
- [x] Safe wrapper methods (boolean return)
- [x] Batch processing with error collection

### File I/O Operations
- [x] `BufferedWriter` / `BufferedReader` (Java NIO)
- [x] Write mode vs append mode (`StandardOpenOption`)
- [x] CSV and pipe-delimited file parsing
- [x] `String.split()` with regex escaping
- [x] Dynamic loading with `ArrayList` and `toArray()`
- [x] Transaction logging and report generation
- [x] Backup, restore, and integrity verification
- [x] `trim()` for robust data parsing

### Professional Practices
- [x] `java.util.logging.Logger` (no `System.out.println`)
- [x] Constants over magic values
- [x] DRY principle with helper methods
- [x] Meaningful naming conventions
- [x] Immutability with `final` fields
- [x] Proper access modifiers (`private`, `protected`)

---

## 📁 Project Structure

```
02-programing-with-java/
│
├── README.md                              # This file
├── COURSEOBJECTIVE.md                     # Course learning objectives
├── SUMMARY.md                             # Course summary
├── AdavancedOOPExercises.md               # Complete exercise roadmap (L1–L4)
│
├── module-1-advanced-opp/
│   ├── MODULEOBJECTIVE.md
│   ├── MODULESUMMARY.md
│   ├── Module1-BankingExercises.md
│   └── src/advancedoop/
│       ├── level1/
│       │   ├── exercise1/                 # Abstract Account Foundation
│       │   ├── exercise2/                 # Customer Account Abstraction
│       │   └── exercise3/                 # Transaction Capability Interfaces
│       └── level2/
│           ├── exercise1/                 # Complete Account Hierarchy
│           ├── exercise2/                 # Transaction Interface System
│           ├── exercise3/                 # Diamond Problem Resolution
│           └── exercise4/                 # Polymorphism in Banking
│
├── module-2-defensive-programming/
│   ├── MODULEOBJECTIVE.md
│   ├── module-2-template.md               # Exercise journal
│   └── src/defenssiveprogramming/
│       └── level3/
│           └── exercise5/                 # Exception Handling in Banking
│               ├── BankingException.java
│               ├── InsufficientFundsException.java
│               ├── AccountClosedException.java
│               ├── InvalidAccountException.java
│               ├── TransactionLimitExceededException.java
│               ├── NetworkException.java
│               ├── RobustBankAccount.java
│               ├── BankingService.java
│               └── ExceptionHandlingTest.java
│
├── module-3-file-handling/
│   ├── MODULEOBJECTIVE.md
│   ├── module-3-exercise_journal.md       # Exercise journal
│   ├── src/filehandling/                  # Level 3 — File I/O exercises
│   │   ├── BankAccount.java
│   │   ├── Transaction.java
│   │   ├── AccountDataWriter.java
│   │   ├── AccountDataReader.java
│   │   ├── TransactionLogger.java
│   │   ├── BackupManager.java
│   │   └── FileIOTest.java
│   └── src/level4/                        # Level 4 — Full Banking Platform
│       ├── Main.java
│       ├── model/                         # 18 domain classes
│       ├── exception/                     # 7 custom exceptions
│       ├── interfaces/                    # 6 capability interfaces
│       ├── service/                       # 3 service classes
│       ├── persistence/                   # 3 persistence classes
│       └── ui/                            # Console UI
│
└── out/                                   # Compiled .class files
    └── production/
```

---

## ⭐ Best Practices Demonstrated

### Code Quality
- **Logger over println**: `java.util.logging.Logger` used throughout all classes
- **Constants**: `LOGGER`, `DELIMITER`, index constants — no magic values
- **DRY**: Helper methods (`parseAccount()`, `createTestAccount()`) eliminate duplication
- **Immutability**: `final` fields where appropriate

### Object-Oriented Design
- **Abstraction**: Abstract `Account`, `Customer`, `Transaction` as templates
- **Interface Segregation**: 6 focused interfaces — each account implements only what it needs
- **Multi-level Inheritance**: `Account → InterestBearingAccount → SavingsAccount`
- **Polymorphism**: Uniform processing via parent-type references and `instanceof` checks

### Error Handling
- **Custom Hierarchy**: `BankingException` base with 6 specific subtypes
- **Error Codes**: Each exception carries a domain-specific error code
- **Retry Logic**: Network exceptions with configurable retry attempts
- **Graceful Degradation**: Batch processing continues despite individual failures

### File I/O
- **Try-with-resources**: All file operations use automatic resource management
- **Defensive Parsing**: `trim()`, length validation, empty checks on every field
- **Append Mode**: Transaction logs use `StandardOpenOption.APPEND`
- **Backup Integrity**: Content comparison to verify backup correctness

---

## 🚀 Future Enhancements

### Technical Improvements
- [ ] Add unit tests using JUnit 5
- [ ] Implement JSON serialization (replace CSV)
- [ ] Add database integration (JDBC)
- [ ] Create REST API using Spring Boot
- [ ] Implement file encryption for sensitive data

### Feature Additions
- [ ] Multi-currency support with exchange rates
- [ ] Scheduled/recurring transactions
- [ ] Loan management system
- [ ] Credit card accounts
- [ ] PDF statement generation
- [ ] Transaction search and filtering

### Architecture Upgrades
- [ ] Repository pattern for data access
- [ ] Dependency Injection framework
- [ ] Builder pattern for complex object creation
- [ ] Observer pattern for transaction notifications
- [ ] Circuit Breaker for network resilience

---

## 📊 Progress Tracking

### Exercise Completion

**Level 1: Core Concept Drills**
- [x] Exercise 1.1: Abstract Account Foundation
- [x] Exercise 1.2: Customer Account Abstraction
- [x] Exercise 1.3: Transaction Capability Interfaces

**Level 2: Applied Banking Features**
- [x] Exercise 2.1: Complete Account Hierarchy
- [x] Exercise 2.2: Transaction Interface System
- [x] Exercise 2.3: Diamond Problem Resolution
- [x] Exercise 2.4: Polymorphism in Banking

**Level 3: System Design Challenges**
- [ ] Exercise 3.1: Interface Inheritance and Extension
- [ ] Exercise 3.2: Single Responsibility and Interface Segregation
- [ ] Exercise 3.3: Advanced Interface Patterns
- [ ] Exercise 3.4: Polymorphic Collections and Processing
- [x] Exercise 3.5: Exception Handling in Banking
- [x] Exercise 3.6: File I/O for Banking Persistence

**Level 4: Full System Integration**
- [x] Exercise 4.1: Complete Banking Digital Platform (39+ classes)

### Module Completion
- [x] Module 1: Advanced OOP
- [x] Module 2: Defensive Programming
- [x] Module 3: File Handling
- [x] Level 4: Banking Platform Integration

---

<div align="center">

**Happy Coding! 🚀**

*Building production-quality Java applications with advanced OOP, robust error handling, and data persistence*

[⬆ Back to Top](#-programming-with-java--advanced-oop-defensive-programming--file-io)

</div>
