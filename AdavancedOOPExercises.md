# Banking Digital System - Complete Exercise Roadmap

## Module Coverage: Advanced Object-Oriented Programming

This comprehensive exercise roadmap ensures 100% coverage of Module 1 concepts through a progressive Banking Digital System implementation.

---

## 📊 Concept Mapping

### Core Concepts Covered in Module 1

| Concept | Description | Exercise Coverage |
|---------|-------------|-------------------|
| **Abstraction** | Hiding complex implementation details | Ex 1.1, 1.2, 4.1 |
| **Abstract Classes** | Templates for related classes that cannot be instantiated | Ex 1.1, 1.2, 2.1, 4.1 |
| **Abstract Methods** | Methods without implementation requiring override | Ex 1.1, 1.2, 2.1, 4.1 |
| **Interfaces** | Contracts defining capabilities | Ex 1.3, 2.2, 2.3, 3.1, 4.1 |
| **Multiple Interface Implementation** | Classes implementing multiple capabilities | Ex 2.3, 3.2, 4.1 |
| **Interface Inheritance** | Interfaces extending other interfaces | Ex 3.1, 3.3 |
| **Class Hierarchy Design** | Organized structure of related classes | Ex 2.1, 2.2, 4.1 |
| **Polymorphism** | Objects taking multiple forms | Ex 2.4, 3.4, 4.1 |
| **Encapsulation** | Data hiding and access control | All exercises |
| **Single Responsibility Principle** | One class, one purpose | Ex 2.1, 3.2, 4.1 |
| **DRY Principle** | Don't Repeat Yourself | Ex 2.1, 4.1 |
| **Exception Handling** | Error management with try-catch | Ex 3.5, 4.1 |
| **Custom Exceptions** | Creating domain-specific exceptions | Ex 3.5, 4.1 |
| **File I/O Operations** | Reading and writing data | Ex 3.6, 4.1 |
| **Diamond Problem Resolution** | Using interfaces to avoid multiple inheritance issues | Ex 2.3, 3.1 |

---

## 🎯 Exercise Progression Structure

```
Level 1: Core Concept Drills (Exercises 1.1 - 1.3)
    ↓
Level 2: Applied Banking Features (Exercises 2.1 - 2.4)
    ↓
Level 3: System Design Challenges (Exercises 3.1 - 3.6)
    ↓
Level 4: Full Banking Digital System (Exercise 4.1)
```

---

# LEVEL 1: Core Concept Drills

## Exercise 1.1: Abstract Account Foundation

### Banking Scenario Context
You are building the foundational structure for a digital banking system. All bank accounts share common properties (account number, balance, owner) but have different transaction rules and fee structures.

### Technical Objectives
- Understand and implement abstract classes
- Create abstract methods that enforce implementation in subclasses
- Implement concrete methods for shared functionality
- Apply encapsulation principles

### Implementation Instructions

**Step 1:** Create an abstract class `BankAccount` with:
- Private fields: `accountNumber` (String), `balance` (double), `accountHolder` (String)
- Constructor to initialize all fields
- Abstract method: `calculateInterest()` returns double
- Abstract method: `applyMonthlyFees()` returns void
- Concrete method: `deposit(double amount)` - adds to balance
- Concrete method: `withdraw(double amount)` - subtracts from balance (if sufficient funds)
- Concrete method: `getBalance()` - returns current balance
- Concrete method: `displayAccountInfo()` - prints account details

**Step 2:** Create two concrete subclasses:

**`SavingsAccount`**
- Private field: `interestRate` (double)
- Constructor accepting all necessary parameters
- Override `calculateInterest()`: returns balance * interestRate / 100
- Override `applyMonthlyFees()`: deducts $5 if balance < $1000

**`CheckingAccount`**
- Private field: `overdraftLimit` (double)
- Constructor accepting all necessary parameters
- Override `calculateInterest()`: returns 0 (no interest)
- Override `applyMonthlyFees()`: deducts $10 monthly maintenance fee
- Override `withdraw()`: allow overdraft up to overdraftLimit

### Required Classes/Interfaces
- `BankAccount` (abstract class)
- `SavingsAccount` (concrete class)
- `CheckingAccount` (concrete class)
- `TestBankAccount` (main class with test cases)

### Constraints
- Must use abstract class for `BankAccount`
- Must have at least 2 abstract methods
- Must demonstrate proper encapsulation (private fields, public getters/setters where needed)
- Cannot instantiate `BankAccount` directly

### Expected Learning Outcomes
- Understand that abstract classes cannot be instantiated
- Recognize when to use abstract vs concrete methods
- Apply inheritance to share common functionality
- Implement method overriding correctly

### Optional Advanced Extension
Add a `BusinessAccount` class with:
- Minimum balance requirement of $5000
- Transaction limit (maximum 50 transactions per month)
- Different fee structure based on transaction count

---
## Exercise 1.2: Customer Account Abstraction

### Banking Scenario Context
The bank serves different types of customers: individual customers, business customers, and premium customers. Each customer type has different authentication methods and service levels.

### Technical Objectives
- Design abstract class hierarchies
- Implement template method pattern using abstract classes
- Understand the relationship between abstract and concrete implementations

### Implementation Instructions

**Step 1:** Create abstract class `Customer` with:
- Protected fields: `customerId` (String), `name` (String), `email` (String), `registrationDate` (String)
- Constructor to initialize fields
- Abstract method: `authenticate()` returns boolean
- Abstract method: `getServiceLevel()` returns String
- Concrete method: `displayCustomerInfo()` - shows customer details
- Concrete method: `updateEmail(String newEmail)` - updates email

**Step 2:** Create concrete subclasses:

**`IndividualCustomer`**
- Private field: `ssn` (String) - Social Security Number
- Implement `authenticate()`: simple verification (for this exercise, return true if ssn is not empty)
- Implement `getServiceLevel()`: return "STANDARD"

**`BusinessCustomer`**
- Private fields: `taxId` (String), `businessName` (String), `employeeCount` (int)
- Implement `authenticate()`: verify taxId and businessName are not empty
- Implement `getServiceLevel()`: return "BUSINESS" if employeeCount > 50, else "STANDARD"

**`PremiumCustomer`**
- Private fields: `membershipLevel` (String), `dedicatedBankerId` (String)
- Implement `authenticate()`: always return true (pre-verified)
- Implement `getServiceLevel()`: return "PREMIUM" or "VIP" based on membershipLevel

### Required Classes/Interfaces
- `Customer` (abstract class)
- `IndividualCustomer` (concrete class)
- `BusinessCustomer` (concrete class)
- `PremiumCustomer` (concrete class)
- `CustomerManagementTest` (main class)

### Constraints
- Must use abstract class for base `Customer`
- All customer types must extend `Customer`
- Must demonstrate abstraction by hiding authentication complexity
- Must use proper access modifiers (protected for shared fields)

### Expected Learning Outcomes
- Design appropriate abstract class hierarchies
- Understand when to use protected vs private access
- Apply abstraction to hide implementation complexity
- Recognize common patterns in object-oriented design

### Optional Advanced Extension
Create an abstract `CorporateCustomer` class that extends `Customer` and is further extended by `SmallBusinessCustomer` and `EnterpriseCustomer`, demonstrating multi-level inheritance.

---

## Exercise 1.3: Transaction Capability Interfaces

### Banking Scenario Context
Different banking services offer various capabilities: some can transfer money, some can pay bills, some can invest. Not all accounts have all capabilities. You need a flexible way to define what each service can do.

### Technical Objectives
- Understand interface purpose and syntax
- Implement interfaces in classes
- Recognize the difference between interfaces and abstract classes

### Implementation Instructions

**Step 1:** Create the following interfaces:

**`Transferable`**
```java
public interface Transferable {
    boolean transferFunds(String toAccount, double amount);
    String getTransferHistory();
}
```

**`BillPayable`**
```java
public interface BillPayable {
    boolean payBill(String billerName, double amount);
    void schedulePayment(String billerName, double amount, String date);
}
```

**`Investable`**
```java
public interface Investable {
    boolean invest(String fundName, double amount);
    double getInvestmentReturns();
}
```

**Step 2:** Create service classes that implement these interfaces:

**`BasicBankingService`** implements `Transferable`
- Implement fund transfer functionality
- Maintain simple transfer history (ArrayList of strings)

**`PremiumBankingService`** implements `Transferable`, `BillPayable`
- Implement both fund transfer and bill payment
- Track both transfer and payment histories

**`WealthManagementService`** implements `Transferable`, `BillPayable`, `Investable`
- Implement all three capabilities
- Track investments and calculate returns

### Required Classes/Interfaces
- `Transferable` (interface)
- `BillPayable` (interface)
- `Investable` (interface)
- `BasicBankingService` (class)
- `PremiumBankingService` (class)
- `WealthManagementService` (class)
- `BankingServiceTest` (main class)

### Constraints
- Must use interfaces (not abstract classes) for capabilities
- Classes must implement ALL methods from their interfaces
- Must use `@Override` annotation
- Demonstrate that interfaces define WHAT, not HOW

### Expected Learning Outcomes
- Understand the purpose of interfaces
- Recognize when to use interfaces vs abstract classes
- Implement multiple interfaces in a single class
- Apply the concept of "contract" in programming

### Optional Advanced Extension
Create a `Notifiable` interface with methods `sendNotification(String message)` and `getNotificationPreference()`. Implement this in all service classes to demonstrate adding capabilities across unrelated classes.

---
# LEVEL 2: Applied Banking Features

## Exercise 2.1: Building a Complete Account Hierarchy

### Banking Scenario Context
Your bank offers multiple account types organized in a clear hierarchy. You need to design a system where common account behaviors are inherited, but each account type has its unique characteristics and rules.

### Technical Objectives
- Design comprehensive class hierarchies
- Apply DRY (Don't Repeat Yourself) principle
- Implement proper inheritance relationships
- Use both abstract and concrete methods appropriately

### Implementation Instructions

**Step 1:** Create abstract class `Account` with:
- Protected fields: `accountId` (String), `balance` (double), `dateOpened` (String), `isActive` (boolean)
- Constructor to initialize fields
- Abstract method: `getAccountType()` returns String
- Abstract method: `calculateMinimumBalance()` returns double
- Concrete method: `deposit(double amount)` - validates amount > 0, then adds to balance
- Concrete method: `getBalance()` returns double
- Concrete method: `closeAccount()` sets isActive to false
- Concrete method: `displaySummary()` shows account overview

**Step 2:** Create abstract class `InterestBearingAccount` extends `Account`:
- Protected field: `annualInterestRate` (double)
- Constructor
- Abstract method: `calculateInterest()` returns double
- Concrete method: `applyInterest()` - adds calculated interest to balance

**Step 3:** Create concrete classes:

**`SavingsAccount`** extends `InterestBearingAccount`
- Private field: `minimumBalance` (double) = 500.0
- Override `getAccountType()`: return "SAVINGS"
- Override `calculateMinimumBalance()`: return minimumBalance
- Override `calculateInterest()`: return balance * annualInterestRate / 100 / 12

**`CheckingAccount`** extends `Account`
- Private fields: `transactionCount` (int), `freeTransactionsLimit` (int) = 10
- Override `getAccountType()`: return "CHECKING"
- Override `calculateMinimumBalance()`: return 100.0
- Override `withdraw()`: increment transactionCount, charge fee if over limit

**`MoneyMarketAccount`** extends `InterestBearingAccount`
- Private field: `tierLevel` (int) - determines interest rate tier
- Override `getAccountType()`: return "MONEY_MARKET"
- Override `calculateMinimumBalance()`: return 2500.0
- Override `calculateInterest()`: tiered interest based on balance

**`CertificateOfDeposit`** extends `InterestBearingAccount`
- Private fields: `termMonths` (int), `maturityDate` (String), `earlyWithdrawalPenalty` (double)
- Override `getAccountType()`: return "CD"
- Override `calculateMinimumBalance()`: return 1000.0
- Override `withdraw()`: apply penalty if before maturity date
- Override `calculateInterest()`: higher rate for longer terms

### Required Classes/Interfaces
- `Account` (abstract class)
- `InterestBearingAccount` (abstract class)
- `SavingsAccount` (concrete class)
- `CheckingAccount` (concrete class)
- `MoneyMarketAccount` (concrete class)
- `CertificateOfDeposit` (concrete class)
- `AccountHierarchyTest` (main class)

### Constraints
- Must demonstrate multi-level inheritance
- Must apply DRY principle - no duplicate code
- Must use proper access modifiers (protected where appropriate)
- Must show proper method overriding with `@Override`

### Expected Learning Outcomes
- Design logical class hierarchies
- Apply inheritance to eliminate code duplication
- Understand multi-level inheritance
- Recognize when to create intermediate abstract classes

### Optional Advanced Extension
Add `JointAccount` class that can wrap any account type and add multiple owners with different access levels.

---

## Exercise 2.2: Account Transaction Interface System

### Banking Scenario Context
Different accounts support different transaction types. Some allow withdrawals, some allow check writing, some allow wire transfers. You need a flexible interface-based system to manage these capabilities.

### Technical Objectives
- Design interface-based capability system
- Implement multiple interfaces in classes
- Understand interface segregation principle
- Apply interfaces to solve the diamond problem

### Implementation Instructions

**Step 1:** Create transaction interfaces:

**`Withdrawable`**
```java
public interface Withdrawable {
    boolean withdraw(double amount);
    double getAvailableBalance();
}
```

**`Depositable`**
```java
public interface Depositable {
    boolean deposit(double amount);
    String getDepositMethod(); // cash, check, transfer, etc.
}
```

**`CheckWritable`**
```java
public interface CheckWritable {
    boolean writeCheck(String payee, double amount, String checkNumber);
    int getRemainingChecks();
}
```

**`WireTransferable`**
```java
public interface WireTransferable {
    boolean sendWire(String recipientBank, String recipientAccount, double amount);
    double getWireTransferLimit();
}
```

**Step 2:** Implement account classes with appropriate interfaces:

**`StandardSavingsAccount`** implements `Depositable`, `Withdrawable`
- Allows deposits and withdrawals only
- Limited to 6 withdrawals per month (federal regulation)

**`PremiumCheckingAccount`** implements `Depositable`, `Withdrawable`, `CheckWritable`
- Supports deposits, withdrawals, and check writing
- Unlimited transactions

**`BusinessAccount`** implements `Depositable`, `Withdrawable`, `CheckWritable`, `WireTransferable`
- Supports all transaction types
- Higher wire transfer limits
- Track all transaction types separately

### Required Classes/Interfaces
- `Withdrawable` (interface)
- `Depositable` (interface)
- `CheckWritable` (interface)
- `WireTransferable` (interface)
- `StandardSavingsAccount` (class)
- `PremiumCheckingAccount` (class)
- `BusinessAccount` (class)
- `TransactionInterfaceTest` (main class)

### Constraints
- Must use interfaces (not abstract classes) for transaction capabilities
- Must implement ALL methods from each interface
- Demonstrate interface segregation - accounts only implement what they need
- Must use `@Override` annotation

### Expected Learning Outcomes
- Design capability-based systems using interfaces
- Implement multiple interfaces in a single class
- Understand interface segregation principle
- Apply interfaces to create flexible systems

### Optional Advanced Extension
Create `TransactionLogger` interface with methods to log all transactions, then implement it in all account classes to demonstrate cross-cutting concerns.

---

## Exercise 2.3: Solving the Diamond Problem with Interfaces

### Banking Scenario Context
Your bank needs to create hybrid account types that combine features from multiple sources. For example, a Student Account might need features from both Savings and Checking accounts. In languages that don't allow multiple inheritance, you must use interfaces.

### Technical Objectives
- Understand the diamond problem in inheritance
- Solve multiple inheritance issues using interfaces
- Implement classes with multiple interface implementations
- Design flexible capability combinations

### Implementation Instructions

**Step 1:** Identify the diamond problem scenario:

```
         BankAccount
           /      \
          /        \
   SavingsFeature  CheckingFeature
          \        /
           \      /
        StudentAccount ← Cannot extend both!
```

**Step 2:** Solve using interfaces:

Create interfaces for capabilities:

**`SavingsCapable`**
```java
public interface SavingsCapable {
    double earnInterest();
    boolean hasMinimumBalanceRequirement();
    double getMinimumBalance();
}
```

**`CheckingCapable`**
```java
public interface CheckingCapable {
    boolean allowsOverdraft();
    double getOverdraftLimit();
    int getFreeTransactions();
}
```

**`RewardCapable`**
```java
public interface RewardCapable {
    double earnRewards();
    String getRewardType(); // cashback, points, miles
    double getRewardBalance();
}
```

**Step 3:** Create hybrid account classes:

**`StudentAccount`** implements `SavingsCapable`, `CheckingCapable`
- Combines savings and checking features
- No minimum balance requirement
- Limited overdraft protection
- No monthly fees

**`RewardCheckingAccount`** implements `CheckingCapable`, `RewardCapable`
- Checking features with rewards
- Earns cashback on debit card purchases
- Premium features

**`HighYieldSavingsAccount`** implements `SavingsCapable`, `RewardCapable`
- High interest savings with bonus rewards
- Higher minimum balance requirement
- Tiered interest rates

**`PremiumHybridAccount`** implements `SavingsCapable`, `CheckingCapable`, `RewardCapable`
- All-in-one account with all features
- Highest tier account
- Combines benefits from all interfaces

### Required Classes/Interfaces
- `SavingsCapable` (interface)
- `CheckingCapable` (interface)
- `RewardCapable` (interface)
- `StudentAccount` (class)
- `RewardCheckingAccount` (class)
- `HighYieldSavingsAccount` (class)
- `PremiumHybridAccount` (class)
- `DiamondProblemTest` (main class)

### Constraints
- Must demonstrate the diamond problem conceptually
- Must solve it using interfaces instead of multiple inheritance
- All classes must implement ALL methods from their interfaces
- Show how interfaces provide flexibility that multiple inheritance cannot

### Expected Learning Outcomes
- Understand the diamond problem in object-oriented programming
- Recognize Java's solution to multiple inheritance
- Design flexible systems using interface composition
- Apply multiple interfaces to create hybrid classes

### Optional Advanced Extension
Create an interface `FeatureCombinable` that allows accounts to dynamically add/remove features at runtime, demonstrating composition over inheritance.

---

## Exercise 2.4: Polymorphism in Banking Operations

### Banking Scenario Context
Your bank's transaction processing system needs to handle different account types uniformly. A teller should be able to process transactions without knowing the specific account type. This requires polymorphism.

### Technical Objectives
- Understand and apply polymorphism
- Use parent class references for child class objects
- Implement polymorphic method calls
- Design systems that leverage polymorphic behavior

### Implementation Instructions

**Step 1:** Create base structure:

**Abstract class `BankingProduct`**
- Protected fields: `productId` (String), `productName` (String), `customerName` (String)
- Abstract method: `processTransaction(String transactionType, double amount)` returns boolean
- Abstract method: `generateStatement()` returns String
- Concrete method: `getProductInfo()` returns String with product details

**Step 2:** Create diverse product classes:

**`SavingsProduct`** extends `BankingProduct`
- Override `processTransaction()`: handle deposit/withdrawal with savings rules
- Override `generateStatement()`: return savings account statement format

**`LoanProduct`** extends `BankingProduct`
- Override `processTransaction()`: handle payment/advance with loan rules
- Override `generateStatement()`: return loan statement with balance and payment schedule

**`CreditCardProduct`** extends `BankingProduct`
- Override `processTransaction()`: handle purchase/payment with credit rules
- Override `generateStatement()`: return credit card statement with transactions

**`InvestmentProduct`** extends `BankingProduct`
- Override `processTransaction()`: handle buy/sell with investment rules
- Override `generateStatement()`: return investment statement with portfolio details

**Step 3:** Create polymorphic processing system:

**`TransactionProcessor`** class
- Method: `processMultipleProducts(BankingProduct[] products, String transactionType, double amount)`
    - Uses polymorphism to process different product types uniformly
    - Calls `processTransaction()` on each product regardless of actual type
- Method: `generateAllStatements(BankingProduct[] products)`
    - Generates statements for all products polymorphically

### Required Classes/Interfaces
- `BankingProduct` (abstract class)
- `SavingsProduct` (concrete class)
- `LoanProduct` (concrete class)
- `CreditCardProduct` (concrete class)
- `InvestmentProduct` (concrete class)
- `TransactionProcessor` (class)
- `PolymorphismTest` (main class)

### Constraints
- Must use parent class (`BankingProduct`) references to hold child class objects
- Must demonstrate polymorphic behavior through method calls
- Show that the same method call produces different behaviors based on actual object type
- Use arrays or collections of parent type holding different child types

### Expected Learning Outcomes
- Understand polymorphism in object-oriented programming
- Apply polymorphic method calls effectively
- Design systems that process different types uniformly
- Recognize the power of programming to interfaces/abstractions

### Optional Advanced Extension
Add a `ProductFactory` class that creates different product types based on input, returning `BankingProduct` references, demonstrating the Factory pattern with polymorphism.

---
# LEVEL 3: System Design Challenges

## Exercise 3.1: Interface Inheritance and Extension

### Banking Scenario Context
Your banking system needs a hierarchical interface structure. Basic accounts have simple operations, but premium accounts have extended capabilities. You need interface inheritance to build this capability hierarchy.

### Technical Objectives
- Understand interface inheritance (extending interfaces)
- Create hierarchical interface structures
- Implement extended interfaces
- Apply interface composition patterns

### Implementation Instructions

**Step 1:** Create base interfaces:

**`BasicAccountOperations`**
```java
public interface BasicAccountOperations {
    boolean deposit(double amount);
    boolean withdraw(double amount);
    double checkBalance();
    String getAccountStatus();
}
```

**`BasicCustomerService`**
```java
public interface BasicCustomerService {
    String getCustomerSupport();
    void reportIssue(String issue);
}
```

**Step 2:** Create extended interfaces:

**`PremiumAccountOperations`** extends `BasicAccountOperations`
```java
public interface PremiumAccountOperations extends BasicAccountOperations {
    boolean transferInternational(String country, String account, double amount);
    double getCurrencyExchangeRate(String currency);
    boolean investInFunds(String fundName, double amount);
}
```

**`PremiumCustomerService`** extends `BasicCustomerService`
```java
public interface PremiumCustomerService extends BasicCustomerService {
    String getDedicatedBanker();
    void scheduleMeeting(String date, String time);
    String getPersonalizedAdvice();
}
```

**`EliteServices`** extends `PremiumAccountOperations`, `PremiumCustomerService`
```java
public interface EliteServices extends PremiumAccountOperations, PremiumCustomerService {
    boolean accessPrivateBanking();
    String getConciergeService();
    boolean requestCustomProduct(String productType);
}
```

**Step 3:** Implement classes for different service tiers:

**`BasicAccountService`** implements `BasicAccountOperations`, `BasicCustomerService`
- Implements all basic methods
- Standard service level

**`PremiumAccountService`** implements `PremiumAccountOperations`, `PremiumCustomerService`
- Implements all basic + premium methods
- Enhanced service level

**`EliteAccountService`** implements `EliteServices`
- Implements all methods (basic + premium + elite)
- Highest service level

### Required Classes/Interfaces
- `BasicAccountOperations` (interface)
- `BasicCustomerService` (interface)
- `PremiumAccountOperations` (interface extending BasicAccountOperations)
- `PremiumCustomerService` (interface extending BasicCustomerService)
- `EliteServices` (interface extending multiple interfaces)
- `BasicAccountService` (class)
- `PremiumAccountService` (class)
- `EliteAccountService` (class)
- `InterfaceInheritanceTest` (main class)

### Constraints
- Must use interface inheritance (`extends` keyword for interfaces)
- Must demonstrate that extended interfaces include all parent methods
- Classes implementing extended interfaces must implement ALL methods in hierarchy
- Show progressive capability enhancement through interface hierarchy

### Expected Learning Outcomes
- Understand interface inheritance and extension
- Design hierarchical interface structures
- Recognize how interface inheritance differs from class inheritance
- Apply interface composition to build complex capabilities

### Optional Advanced Extension
Create a `ServiceTierFactory` that returns appropriate service implementations based on customer tier, demonstrating polymorphism with interface types.

---

## Exercise 3.2: Single Responsibility and Interface Segregation

### Banking Scenario Context
Your previous system has grown unwieldy with large interfaces containing many methods. Not all classes need all methods. You need to refactor using the Single Responsibility Principle and Interface Segregation Principle.

### Technical Objectives
- Apply Single Responsibility Principle (SRP)
- Apply Interface Segregation Principle (ISP)
- Refactor large interfaces into focused ones
- Design cohesive, maintainable interfaces

### Implementation Instructions

**Step 1:** Identify the problem - create a BAD example:

**`BankingOperations`** (anti-pattern - too large)
```java
public interface BankingOperations {
    // Account operations
    boolean deposit(double amount);
    boolean withdraw(double amount);
    
    // Loan operations
    boolean applyForLoan(double amount);
    double calculateLoanPayment();
    
    // Investment operations
    boolean buyStocks(String symbol, int shares);
    boolean sellStocks(String symbol, int shares);
    
    // Report operations
    String generateAccountReport();
    String generateLoanReport();
    String generateInvestmentReport();
    
    // Notification operations
    void sendEmail(String message);
    void sendSMS(String message);
}
```

**Step 2:** Refactor into focused interfaces following ISP:

**`AccountOperations`**
```java
public interface AccountOperations {
    boolean deposit(double amount);
    boolean withdraw(double amount);
}
```

**`LoanOperations`**
```java
public interface LoanOperations {
    boolean applyForLoan(double amount);
    double calculateLoanPayment();
}
```

**`InvestmentOperations`**
```java
public interface InvestmentOperations {
    boolean buyStocks(String symbol, int shares);
    boolean sellStocks(String symbol, int shares);
}
```

**`ReportGenerator`**
```java
public interface ReportGenerator {
    String generateReport(String reportType);
}
```

**`NotificationService`**
```java
public interface NotificationService {
    void sendNotification(String channel, String message);
}
```

**Step 3:** Create focused implementations:

**`BasicAccountManager`** implements `AccountOperations`
- Only handles account operations
- Single responsibility

**`LoanManager`** implements `LoanOperations`, `ReportGenerator`
- Handles loans and loan reports
- Related responsibilities grouped

**`InvestmentManager`** implements `InvestmentOperations`, `ReportGenerator`
- Handles investments and investment reports
- Related responsibilities grouped

**`NotificationManager`** implements `NotificationService`
- Only handles notifications
- Single responsibility

**`ComprehensiveBankingService`** (composition approach)
- Contains instances of all managers
- Delegates to appropriate manager
- Demonstrates composition over large inheritance

### Required Classes/Interfaces
- `BankingOperations` (interface - bad example)
- `AccountOperations` (interface - refactored)
- `LoanOperations` (interface - refactored)
- `InvestmentOperations` (interface - refactored)
- `ReportGenerator` (interface - refactored)
- `NotificationService` (interface - refactored)
- `BasicAccountManager` (class)
- `LoanManager` (class)
- `InvestmentManager` (class)
- `NotificationManager` (class)
- `ComprehensiveBankingService` (class using composition)
- `SRPandISPTest` (main class)

### Constraints
- Must show both bad and good examples
- Refactored interfaces must be focused and cohesive
- Demonstrate that classes only implement what they need
- Show composition as alternative to implementing many interfaces

### Expected Learning Outcomes
- Understand Single Responsibility Principle
- Apply Interface Segregation Principle
- Recognize code smells in interface design
- Refactor large interfaces into focused ones
- Use composition to combine focused components

### Optional Advanced Extension
Add metrics to show how ISP reduces coupling: count methods in large interface vs. focused interfaces, demonstrate that classes implementing focused interfaces have fewer unused methods.

---

## Exercise 3.3: Advanced Interface Patterns

### Banking Scenario Context
You're building an advanced banking platform with complex relationships between different services. You need to use advanced interface patterns including marker interfaces, functional interfaces, and default methods (Java 8+).

### Technical Objectives
- Understand marker interfaces
- Use functional interfaces for callbacks
- Implement default methods in interfaces (Java 8+)
- Apply advanced interface patterns

### Implementation Instructions

**Step 1:** Create marker interfaces:

**`Auditable`** (marker interface)
```java
public interface Auditable {
    // Empty - marks classes that should be audited
}
```

**`Secure`** (marker interface)
```java
public interface Secure {
    // Empty - marks classes requiring security checks
}
```

**Step 2:** Create functional interfaces for callbacks:

**`TransactionCallback`**
```java
@FunctionalInterface
public interface TransactionCallback {
    void onTransactionComplete(String transactionId, boolean success);
}
```

**`ValidationRule`**
```java
@FunctionalInterface
public interface ValidationRule {
    boolean validate(double amount);
}
```

**Step 3:** Create interfaces with default methods:

**`TransactionProcessor`**
```java
public interface TransactionProcessor {
    boolean processTransaction(String type, double amount);
    
    default boolean isValidAmount(double amount) {
        return amount > 0 && amount <= 1000000;
    }
    
    default String formatCurrency(double amount) {
        return String.format("$%,.2f", amount);
    }
    
    default void logTransaction(String message) {
        System.out.println("[TRANSACTION LOG] " + message);
    }
}
```

**`AccountValidator`**
```java
public interface AccountValidator {
    boolean validateAccount(String accountId);
    
    default boolean isAccountNumberValid(String accountNumber) {
        return accountNumber != null && accountNumber.length() == 10;
    }
    
    default boolean isRoutingNumberValid(String routingNumber) {
        return routingNumber != null && routingNumber.length() == 9;
    }
}
```

**Step 4:** Implement classes using these patterns:

**`SecureTransaction`** implements `TransactionProcessor`, `Auditable`, `Secure`
- Uses marker interfaces to indicate special handling
- Uses default methods from TransactionProcessor
- Implements callback mechanism

**`StandardAccount`** implements `AccountValidator`
- Uses default validation methods
- Overrides where needed for custom logic

**`CallbackEnabledService`**
- Accepts `TransactionCallback` as parameter
- Demonstrates functional interface usage
- Uses lambda expressions for callbacks

### Required Classes/Interfaces
- `Auditable` (marker interface)
- `Secure` (marker interface)
- `TransactionCallback` (functional interface)
- `ValidationRule` (functional interface)
- `TransactionProcessor` (interface with default methods)
- `AccountValidator` (interface with default methods)
- `SecureTransaction` (class)
- `StandardAccount` (class)
- `CallbackEnabledService` (class)
- `AdvancedInterfaceTest` (main class)

### Constraints
- Must use marker interfaces appropriately
- Must use `@FunctionalInterface` annotation
- Must implement default methods in interfaces
- Demonstrate lambda expressions with functional interfaces
- Show how default methods reduce code duplication

### Expected Learning Outcomes
- Understand marker interface pattern
- Use functional interfaces and lambdas
- Implement and override default methods
- Apply advanced interface features effectively
- Recognize when to use each pattern

### Optional Advanced Extension
Create a `SecurityManager` that checks for `Secure` marker interface and applies additional security measures, demonstrating reflection with marker interfaces.

---

## Exercise 3.4: Polymorphic Collections and Processing

### Banking Scenario Context
Your bank processes thousands of different transactions daily. You need efficient systems to handle different transaction types uniformly using polymorphic collections.

### Technical Objectives
- Use polymorphic collections (arrays, ArrayLists)
- Process different types uniformly
- Apply polymorphism at scale
- Design efficient processing systems

### Implementation Instructions

**Step 1:** Create transaction hierarchy:

**Abstract class `Transaction`**
- Protected fields: `transactionId` (String), `amount` (double), `timestamp` (String), `status` (String)
- Abstract method: `process()` returns boolean
- Abstract method: `getTransactionType()` returns String
- Concrete method: `getTransactionDetails()` returns formatted string
- Concrete method: `markCompleted()` sets status to "COMPLETED"
- Concrete method: `markFailed()` sets status to "FAILED"

**Step 2:** Create specific transaction types:

**`DepositTransaction`** extends `Transaction`
- Private field: `depositMethod` (String) - cash, check, transfer
- Override `process()`: validate and process deposit
- Override `getTransactionType()`: return "DEPOSIT"

**`WithdrawalTransaction`** extends `Transaction`
- Private field: `withdrawalMethod` (String) - ATM, teller, transfer
- Override `process()`: validate and process withdrawal
- Override `getTransactionType()`: return "WITHDRAWAL"

**`TransferTransaction`** extends `Transaction`
- Private fields: `fromAccount` (String), `toAccount` (String)
- Override `process()`: validate both accounts and process transfer
- Override `getTransactionType()`: return "TRANSFER"

**`BillPaymentTransaction`** extends `Transaction`
- Private fields: `payee` (String), `billAccount` (String)
- Override `process()`: validate payee and process payment
- Override `getTransactionType()`: return "BILL_PAYMENT"

**`LoanPaymentTransaction`** extends `Transaction`
- Private fields: `loanId` (String), `principalAmount` (double), `interestAmount` (double)
- Override `process()`: allocate payment to principal and interest
- Override `getTransactionType()`: return "LOAN_PAYMENT"

**Step 3:** Create polymorphic processing system:

**`TransactionBatchProcessor`** class
- Method: `processTransactionBatch(Transaction[] transactions)`
    - Processes array of different transaction types
    - Returns success count
- Method: `filterByType(Transaction[] transactions, String type)`
    - Returns array of transactions matching type
- Method: `calculateTotalAmount(Transaction[] transactions)`
    - Sums amounts across all transaction types
- Method: `generateBatchReport(Transaction[] transactions)`
    - Creates report showing breakdown by type
- Method: `processWithPriority(ArrayList<Transaction> transactions)`
    - Demonstrates ArrayList usage
    - Processes high-priority transactions first

### Required Classes/Interfaces
- `Transaction` (abstract class)
- `DepositTransaction` (class)
- `WithdrawalTransaction` (class)
- `TransferTransaction` (class)
- `BillPaymentTransaction` (class)
- `LoanPaymentTransaction` (class)
- `TransactionBatchProcessor` (class)
- `PolymorphicCollectionTest` (main class)

### Constraints
- Must use polymorphic collections (`Transaction[]` or `ArrayList<Transaction>`)
- Must process different types without type checking (`instanceof`)
- Demonstrate uniform processing through polymorphic method calls
- Show both array and ArrayList usage

### Expected Learning Outcomes
- Use polymorphic collections effectively
- Process diverse types uniformly
- Apply polymorphism to real-world batch processing
- Work with both arrays and ArrayLists polymorphically

### Optional Advanced Extension
Add `PriorityTransaction` interface with `getPriority()` method. Create `PriorityQueue` implementation that sorts transactions by priority, demonstrating interface-based sorting.

---

## Exercise 3.5: Exception Handling in Banking Systems

### Banking Scenario Context
Banking operations must be robust and handle errors gracefully. Insufficient funds, invalid accounts, transaction limits, and network errors all require proper exception handling.

### Technical Objectives
- Implement try-catch blocks
- Create custom exceptions
- Use exception hierarchy
- Apply proper error handling patterns

### Implementation Instructions

**Step 1:** Create custom exception hierarchy:

**`BankingException`** extends `Exception`
```java
public class BankingException extends Exception {
    private String errorCode;
    
    public BankingException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
}
```

**`InsufficientFundsException`** extends `BankingException`
- Additional field: `requiredAmount` (double), `availableAmount` (double)
- Custom constructor and methods

**`InvalidAccountException`** extends `BankingException`
- Additional field: `accountNumber` (String)
- Custom error message generation

**`TransactionLimitExceededException`** extends `BankingException`
- Additional fields: `transactionAmount` (double), `limitAmount` (double)
- Methods to report violation details

**`AccountClosedException`** extends `BankingException`
- Additional field: `closureDate` (String)
- Custom message indicating account status

**`NetworkException`** extends `BankingException`
- Additional field: `retryable` (boolean)
- Methods to determine if operation can be retried

**Step 2:** Create account class with exception handling:

**`RobustBankAccount`** class
- Method: `withdraw(double amount) throws InsufficientFundsException, AccountClosedException`
    - Checks balance and account status
    - Throws appropriate exceptions
- Method: `transfer(String toAccount, double amount) throws BankingException`
    - Validates accounts
    - Checks limits
    - Throws various exceptions as needed
- Method: `processTransaction(String type, double amount) throws BankingException`
    - Central transaction processing with comprehensive error handling

**Step 3:** Create service layer with exception handling:

**`BankingService`** class
- Method: `safeWithdraw(RobustBankAccount account, double amount)`
    - Uses try-catch to handle exceptions
    - Returns success/failure status
    - Logs errors appropriately
- Method: `safeTransfer(RobustBankAccount from, String to, double amount)`
    - Multiple catch blocks for different exceptions
    - Implements retry logic for network errors
- Method: `processBatchWithErrorHandling(RobustBankAccount[] accounts, double[] amounts)`
    - Processes multiple operations
    - Continues processing even if some fail
    - Collects and reports all errors

**Step 4:** Create exception reporter:

**`ExceptionReporter`** class
- Method: `reportException(BankingException e)`
    - Generates detailed error report
    - Includes error code and context
- Method: `generateErrorLog(BankingException[] exceptions)`
    - Creates comprehensive error log

### Required Classes/Interfaces
- `BankingException` (custom exception)
- `InsufficientFundsException` (custom exception)
- `InvalidAccountException` (custom exception)
- `TransactionLimitExceededException` (custom exception)
- `AccountClosedException` (custom exception)
- `NetworkException` (custom exception)
- `RobustBankAccount` (class)
- `BankingService` (class)
- `ExceptionReporter` (class)
- `ExceptionHandlingTest` (main class)

### Constraints
- Must create custom exception hierarchy
- Must use try-catch blocks appropriately
- Methods must declare exceptions with `throws` clause
- Demonstrate multiple catch blocks
- Show finally block usage for resource cleanup

### Expected Learning Outcomes
- Create custom exception classes
- Implement proper exception hierarchy
- Use try-catch-finally blocks effectively
- Apply exception handling best practices
- Design robust error handling systems

### Optional Advanced Extension
Implement `AutoCloseable` interface for resources and use try-with-resources statement to demonstrate automatic resource management.

---

## Exercise 3.6: File I/O for Banking Data Persistence

### Banking Scenario Context
Your banking system needs to save account data, transaction logs, and customer information to files. You also need to read this data when the system starts up.

### Technical Objectives
- Implement file writing operations
- Implement file reading operations
- Handle file I/O exceptions
- Apply proper resource management

### Implementation Instructions

**Step 1:** Create data persistence classes:

**`AccountDataWriter`** class
- Method: `saveAccount(BankAccount account, String filename)`
    - Writes account data to file
    - Uses `FileWriter` and `BufferedWriter`
    - Handles `IOException`
- Method: `saveMultipleAccounts(BankAccount[] accounts, String filename)`
    - Writes multiple accounts
    - CSV or structured text format
- Method: `appendTransaction(String filename, Transaction transaction)`
    - Appends transaction to log file
    - Does not overwrite existing data

**`AccountDataReader`** class
- Method: `loadAccount(String filename)`
    - Reads account data from file
    - Uses `FileReader` and `BufferedReader`
    - Returns `BankAccount` object
    - Handles `IOException` and `FileNotFoundException`
- Method: `loadMultipleAccounts(String filename)`
    - Reads multiple accounts
    - Returns array or ArrayList
- Method: `readTransactionLog(String filename)`
    - Reads transaction history
    - Parses and creates Transaction objects

**Step 2:** Create transaction logger:

**`TransactionLogger`** class
- Method: `logTransaction(Transaction transaction, String logFile)`
    - Appends to log file with timestamp
    - Uses try-with-resources for automatic closing
- Method: `generateDailyReport(String logFile, String reportFile)`
    - Reads transaction log
    - Processes and summarizes
    - Writes report to new file
- Method: `archiveOldLogs(String logFile, String archiveFile)`
    - Reads and moves old data
    - Demonstrates file operations

**Step 3:** Create backup system:

**`BackupManager`** class
- Method: `createBackup(String sourceFile, String backupFile)`
    - Copies file contents
    - Handles errors gracefully
- Method: `restoreFromBackup(String backupFile, String targetFile)`
    - Restores data from backup
- Method: `verifyBackupIntegrity(String sourceFile, String backupFile)`
    - Compares files to ensure backup is valid

**Step 4:** Create comprehensive file management test:

**`FileIOTest`** class (main)
- Create sample accounts
- Write to files
- Read back and verify
- Test error scenarios (missing files, corrupted data)
- Demonstrate backup and restore
- Show transaction logging

### Required Classes/Interfaces
- `AccountDataWriter` (class)
- `AccountDataReader` (class)
- `TransactionLogger` (class)
- `BackupManager` (class)
- `FileIOTest` (main class)
- Sample data files (created during execution)

### Constraints
- Must use `FileWriter` and `FileReader` (or similar)
- Must use try-catch for `IOException`
- Must use try-with-resources for automatic resource closing
- Demonstrate both reading and writing
- Handle file not found scenarios

### Expected Learning Outcomes
- Implement file writing in Java
- Implement file reading in Java
- Handle file I/O exceptions properly
- Manage resources correctly
- Design data persistence systems

### Optional Advanced Extension
Implement JSON or XML serialization for more structured data storage. Add encryption for sensitive banking data. Create file versioning system.

---
# LEVEL 4: Full Banking Digital System Integrative Project

## Exercise 4.1: Complete Banking Digital Platform

### Banking Scenario Context
You are architecting a complete digital banking platform that integrates all the concepts you've learned. This system must handle multiple account types, diverse customer types, various transaction capabilities, robust error handling, and data persistence. The system must be scalable, maintainable, and follow SOLID principles.

### Technical Objectives
- Integrate ALL concepts from Module 1
- Apply abstraction, interfaces, abstract classes, inheritance, and polymorphism
- Implement comprehensive exception handling
- Incorporate file I/O for data persistence
- Design clean architecture following SOLID principles
- Create a realistic, production-quality banking system

### System Requirements

#### 1. Account Management System
**Must Include:**
- Abstract base class for all accounts
- At least 5 different account types (Savings, Checking, Money Market, CD, Business)
- Account hierarchy with shared and unique behaviors
- Interest calculation for applicable accounts
- Fee structure and monthly maintenance

#### 2. Customer Management System
**Must Include:**
- Abstract customer base class
- Multiple customer types (Individual, Business, Premium, VIP)
- Customer authentication mechanism
- Service level determination
- Customer-Account relationship (one customer can have multiple accounts)

#### 3. Transaction Processing System
**Must Include:**
- Abstract transaction base class
- At least 6 transaction types (Deposit, Withdrawal, Transfer, Bill Payment, Loan Payment, Investment)
- Transaction validation and processing
- Transaction history tracking
- Batch transaction processing

#### 4. Interface-Based Capabilities System
**Must Include:**
- Multiple interfaces for different capabilities (Transferable, Withdrawable, Depositable, etc.)
- Classes implementing multiple interfaces
- Interface inheritance (extended interfaces)
- Demonstration of solving the diamond problem

#### 5. Exception Handling System
**Must Include:**
- Custom exception hierarchy (minimum 6 custom exceptions)
- Try-catch blocks throughout the application
- Proper exception propagation
- Error logging and reporting
- Graceful error recovery

#### 6. File I/O and Persistence
**Must Include:**
- Save accounts to file
- Load accounts from file
- Transaction logging to file
- Daily/monthly report generation
- Backup and restore functionality

#### 7. User Interface (Console-Based)
**Must Include:**
- Main menu system
- Account creation workflow
- Transaction processing interface
- Report viewing
- Admin functions

### Detailed Implementation Instructions

#### Phase 1: Core Architecture (Foundation)

**Step 1.1:** Design the account hierarchy
```java
// Abstract base
public abstract class Account {
    protected String accountId;
    protected String accountHolder;
    protected double balance;
    protected boolean isActive;
    protected String dateOpened;
    
    public abstract String getAccountType();
    public abstract double calculateMonthlyFees();
    public abstract boolean meetsMinimumBalance();
    
    // Concrete methods for common functionality
    public void deposit(double amount) throws InvalidAmountException {...}
    public double getBalance() {...}
    // ... more methods
}

// Intermediate abstract class
public abstract class InterestBearingAccount extends Account {
    protected double interestRate;
    public abstract double calculateInterest();
    // ... more methods
}

// Concrete implementations
public class SavingsAccount extends InterestBearingAccount {...}
public class CheckingAccount extends Account {...}
public class MoneyMarketAccount extends InterestBearingAccount {...}
public class CertificateOfDeposit extends InterestBearingAccount {...}
public class BusinessAccount extends Account {...}
```

**Step 1.2:** Design the customer hierarchy
```java
public abstract class Customer {
    protected String customerId;
    protected String name;
    protected String email;
    protected String phone;
    protected ArrayList<Account> accounts;
    
    public abstract boolean authenticate(String credential);
    public abstract String getServiceLevel();
    
    public void addAccount(Account account) {...}
    public Account[] getAccounts() {...}
    // ... more methods
}

public class IndividualCustomer extends Customer {...}
public class BusinessCustomer extends Customer {...}
public class PremiumCustomer extends Customer {...}
```

**Step 1.3:** Design the transaction hierarchy
```java
public abstract class Transaction {
    protected String transactionId;
    protected double amount;
    protected String timestamp;
    protected String status;
    
    public abstract boolean process() throws BankingException;
    public abstract String getTransactionType();
    
    public String getTransactionDetails() {...}
    // ... more methods
}

public class DepositTransaction extends Transaction {...}
public class WithdrawalTransaction extends Transaction {...}
public class TransferTransaction extends Transaction {...}
public class BillPaymentTransaction extends Transaction {...}
public class LoanPaymentTransaction extends Transaction {...}
public class InvestmentTransaction extends Transaction {...}
```

#### Phase 2: Interface-Based Capabilities

**Step 2.1:** Create capability interfaces
```java
public interface Transferable {
    boolean transferFunds(String toAccount, double amount) throws BankingException;
    String[] getTransferHistory();
}

public interface Withdrawable {
    boolean withdraw(double amount) throws InsufficientFundsException;
    double getAvailableBalance();
}

public interface Depositable {
    boolean deposit(double amount) throws InvalidAmountException;
}

public interface Investable {
    boolean invest(String fundName, double amount) throws BankingException;
    double getInvestmentReturns();
}

public interface BillPayable {
    boolean payBill(String payee, double amount) throws BankingException;
    void schedulePayment(String payee, double amount, String date);
}

public interface Reportable {
    String generateStatement();
    String generateMonthlyReport();
}
```

**Step 2.2:** Create service classes implementing multiple interfaces
```java
public class BasicBankingService implements Depositable, Withdrawable, Reportable {...}
public class PremiumBankingService implements Depositable, Withdrawable, Transferable, BillPayable, Reportable {...}
public class WealthManagementService implements Depositable, Withdrawable, Transferable, Investable, Reportable {...}
```

#### Phase 3: Exception Handling System

**Step 3.1:** Create exception hierarchy
```java
public class BankingException extends Exception {
    private String errorCode;
    public BankingException(String message, String errorCode) {...}
}

public class InsufficientFundsException extends BankingException {...}
public class InvalidAccountException extends BankingException {...}
public class InvalidAmountException extends BankingException {...}
public class TransactionLimitExceededException extends BankingException {...}
public class AccountClosedException extends BankingException {...}
public class AuthenticationFailedException extends BankingException {...}
```

**Step 3.2:** Implement exception handling in all operations
- Every transaction must have proper exception handling
- Service layer must catch and handle exceptions appropriately
- User interface must display user-friendly error messages

#### Phase 4: File I/O System

**Step 4.1:** Create persistence layer
```java
public class AccountPersistence {
    public void saveAccount(Account account, String filename) throws IOException {...}
    public Account loadAccount(String filename) throws IOException, ClassNotFoundException {...}
    public void saveAllAccounts(ArrayList<Account> accounts, String filename) {...}
    public ArrayList<Account> loadAllAccounts(String filename) {...}
}

public class TransactionLogger {
    public void logTransaction(Transaction transaction) throws IOException {...}
    public ArrayList<Transaction> readTransactionLog(String date) {...}
}

public class ReportGenerator {
    public void generateDailyReport(String date, String filename) throws IOException {...}
    public void generateMonthlyReport(String month, String filename) {...}
    public void generateCustomerReport(String customerId, String filename) {...}
}
```

#### Phase 5: Business Logic Layer

**Step 5.1:** Create core service classes
```java
public class AccountManager {
    private ArrayList<Account> accounts;
    
    public Account createAccount(String type, Customer owner, double initialDeposit) {...}
    public boolean closeAccount(String accountId) {...}
    public Account getAccount(String accountId) {...}
    public ArrayList<Account> getAccountsByCustomer(String customerId) {...}
}

public class TransactionProcessor {
    public boolean processTransaction(Transaction transaction) throws BankingException {...}
    public void processBatch(ArrayList<Transaction> transactions) {...}
    public ArrayList<Transaction> getTransactionHistory(String accountId) {...}
}

public class CustomerManager {
    private ArrayList<Customer> customers;
    
    public Customer createCustomer(String type, String name, String email) {...}
    public Customer getCustomer(String customerId) {...}
    public boolean authenticateCustomer(String customerId, String credential) {...}
}
```

#### Phase 6: User Interface Layer

**Step 6.1:** Create console-based menu system
```java
public class BankingSystemUI {
    private Scanner scanner;
    private AccountManager accountManager;
    private CustomerManager customerManager;
    private TransactionProcessor transactionProcessor;
    
    public void displayMainMenu() {...}
    public void handleAccountCreation() {...}
    public void handleTransaction() {...}
    public void handleReports() {...}
    public void handleCustomerManagement() {...}
}
```

**Step 6.2:** Implement main application class
```java
public class BankingSystemApp {
    public static void main(String[] args) {
        // Initialize system
        // Load existing data from files
        // Start user interface
        // Handle user interactions
        // Save data on exit
    }
}
```

### Required Classes/Interfaces (Minimum 35 Classes)

#### Abstract Classes (5)
- `Account`
- `InterestBearingAccount`
- `Customer`
- `Transaction`
- `BankingService`

#### Concrete Account Classes (5)
- `SavingsAccount`
- `CheckingAccount`
- `MoneyMarketAccount`
- `CertificateOfDeposit`
- `BusinessAccount`

#### Concrete Customer Classes (3)
- `IndividualCustomer`
- `BusinessCustomer`
- `PremiumCustomer`

#### Concrete Transaction Classes (6)
- `DepositTransaction`
- `WithdrawalTransaction`
- `TransferTransaction`
- `BillPaymentTransaction`
- `LoanPaymentTransaction`
- `InvestmentTransaction`

#### Interfaces (8)
- `Transferable`
- `Withdrawable`
- `Depositable`
- `Investable`
- `BillPayable`
- `Reportable`
- `Authenticatable`
- `Auditable`

#### Service Classes (3)
- `BasicBankingService`
- `PremiumBankingService`
- `WealthManagementService`

#### Exception Classes (6)
- `BankingException`
- `InsufficientFundsException`
- `InvalidAccountException`
- `InvalidAmountException`
- `TransactionLimitExceededException`
- `AccountClosedException`

#### Persistence Classes (3)
- `AccountPersistence`
- `TransactionLogger`
- `ReportGenerator`

#### Manager Classes (3)
- `AccountManager`
- `CustomerManager`
- `TransactionProcessor`

#### UI and Main Classes (2)
- `BankingSystemUI`
- `BankingSystemApp`

### Implementation Constraints

1. **Abstraction**
    - Must use abstract classes where appropriate
    - Must hide implementation complexity from users
    - Must expose only necessary methods publicly

2. **Interfaces**
    - Must use interfaces for capabilities
    - Must implement multiple interfaces where appropriate
    - Must demonstrate interface inheritance

3. **Inheritance**
    - Must create logical class hierarchies
    - Must apply DRY principle through inheritance
    - Must demonstrate multi-level inheritance

4. **Polymorphism**
    - Must use polymorphic collections
    - Must process different types uniformly
    - Must demonstrate runtime polymorphism

5. **Encapsulation**
    - All fields must be private or protected
    - Must use getters/setters appropriately
    - Must validate data in setters

6. **Exception Handling**
    - All risky operations must have try-catch blocks
    - Must create custom exception hierarchy
    - Must handle exceptions gracefully

7. **File I/O**
    - Must save and load account data
    - Must log all transactions
    - Must generate reports to files

8. **SOLID Principles**
    - Single Responsibility: Each class has one purpose
    - Open/Closed: Extensible without modification
    - Liskov Substitution: Subtypes are substitutable
    - Interface Segregation: Focused interfaces
    - Dependency Inversion: Depend on abstractions

### Expected Features

#### Core Features
- [ ] Create new customer accounts
- [ ] Open multiple account types for each customer
- [ ] Deposit money into accounts
- [ ] Withdraw money from accounts
- [ ] Transfer money between accounts
- [ ] Pay bills from accounts
- [ ] Calculate and apply interest
- [ ] Calculate and apply fees
- [ ] Generate account statements
- [ ] View transaction history
- [ ] Close accounts
- [ ] Authenticate customers

#### Advanced Features
- [ ] Batch transaction processing
- [ ] Investment operations
- [ ] Loan payment processing
- [ ] Automated backup and restore
- [ ] Daily/monthly reporting
- [ ] Customer service level management
- [ ] Account status management
- [ ] Transaction validation and limits
- [ ] Error logging and reporting
- [ ] Data persistence across sessions

### Testing Requirements

Create comprehensive test scenarios:

**Test Scenario 1: Account Lifecycle**
1. Create customer
2. Open multiple accounts
3. Perform various transactions
4. Generate statements
5. Close accounts

**Test Scenario 2: Exception Handling**
1. Test insufficient funds
2. Test invalid account
3. Test transaction limits
4. Test closed account access
5. Verify error messages

**Test Scenario 3: Polymorphism**
1. Create array of different account types
2. Process uniformly
3. Demonstrate polymorphic behavior

**Test Scenario 4: Persistence**
1. Create and save accounts
2. Exit system
3. Restart and load accounts
4. Verify data integrity

**Test Scenario 5: Interface Implementation**
1. Test different service levels
2. Verify capability segregation
3. Demonstrate multiple interface implementation

### Expected Learning Outcomes

By completing this integrative project, you will:

1. **Master Abstraction**
    - Design abstract classes for common structure
    - Hide complexity from users
    - Expose clean interfaces

2. **Master Interfaces**
    - Create focused capability interfaces
    - Implement multiple interfaces
    - Use interface inheritance

3. **Master Inheritance**
    - Build logical class hierarchies
    - Apply DRY principle
    - Implement multi-level inheritance

4. **Master Polymorphism**
    - Use polymorphic collections
    - Process diverse types uniformly
    - Apply runtime polymorphism

5. **Master Exception Handling**
    - Create custom exception hierarchies
    - Implement robust error handling
    - Design fault-tolerant systems

6. **Master File I/O**
    - Persist data to files
    - Load data from files
    - Generate reports

7. **Apply SOLID Principles**
    - Write maintainable code
    - Design extensible systems
    - Follow professional best practices

8. **Build Real Systems**
    - Integrate multiple concepts
    - Solve real-world problems
    - Create production-quality code

### Optional Advanced Extensions

1. **Multi-Currency Support**
    - Add currency types
    - Implement exchange rates
    - Handle currency conversions

2. **Scheduled Transactions**
    - Implement recurring payments
    - Add future-dated transactions
    - Create transaction queue

3. **Security Features**
    - Add password encryption
    - Implement transaction signing
    - Add audit trail

4. **Notification System**
    - Email notifications
    - SMS alerts
    - Transaction confirmations

5. **Reporting Dashboard**
    - Graphical report generation
    - Statistical analysis
    - Trend visualization

6. **Multi-Threading**
    - Concurrent transaction processing
    - Thread-safe operations
    - Parallel batch processing

7. **Database Integration**
    - Replace file I/O with database
    - Use JDBC
    - Implement connection pooling

8. **Web Service API**
    - REST API endpoints
    - JSON request/response
    - API authentication

### Deliverables

1. **Source Code**
    - All required classes (minimum 35)
    - Properly commented
    - Following Java naming conventions

2. **Documentation**
    - Class diagrams showing hierarchy
    - Interface relationship diagrams
    - User manual for the system
    - Developer documentation

3. **Test Cases**
    - Comprehensive test scenarios
    - Test data files
    - Expected vs actual results

4. **Sample Data Files**
    - Account data files
    - Transaction logs
    - Generated reports

5. **README File**
    - How to compile and run
    - System requirements
    - Feature list
    - Known limitations

### Success Criteria

Your project will be considered successful if it:

- ✅ Implements ALL required classes and interfaces
- ✅ Demonstrates ALL module concepts (abstraction, interfaces, inheritance, polymorphism, exceptions, file I/O)
- ✅ Follows SOLID principles
- ✅ Handles errors gracefully
- ✅ Persists data correctly
- ✅ Provides clean, intuitive user interface
- ✅ Includes comprehensive testing
- ✅ Is well-documented
- ✅ Compiles without errors
- ✅ Runs without crashes
- ✅ Produces correct results
- ✅ Demonstrates professional coding standards

---

## 🎓 Conclusion

This exercise roadmap provides comprehensive coverage of all Module 1 concepts through progressive, contextualized banking system exercises. Each exercise builds on previous knowledge and prepares you for the next challenge, culminating in a complete, production-quality banking digital system.

### Concept Coverage Summary

| Module Concept | Coverage | Exercises |
|----------------|----------|-----------|
| **Abstraction** | ✅ Complete | 1.1, 1.2, 2.1, 4.1 |
| **Abstract Classes** | ✅ Complete | 1.1, 1.2, 2.1, 2.4, 3.4, 4.1 |
| **Abstract Methods** | ✅ Complete | 1.1, 1.2, 2.1, 4.1 |
| **Interfaces** | ✅ Complete | 1.3, 2.2, 2.3, 3.1, 3.2, 3.3, 4.1 |
| **Multiple Interface Implementation** | ✅ Complete | 1.3, 2.2, 2.3, 3.1, 4.1 |
| **Interface Inheritance** | ✅ Complete | 3.1, 3.3, 4.1 |
| **Class Hierarchy Design** | ✅ Complete | 1.2, 2.1, 2.4, 3.4, 4.1 |
| **Polymorphism** | ✅ Complete | 2.4, 3.4, 4.1 |
| **Encapsulation** | ✅ Complete | All exercises |
| **Single Responsibility Principle** | ✅ Complete | 2.1, 3.2, 4.1 |
| **DRY Principle** | ✅ Complete | 2.1, 4.1 |
| **Diamond Problem Resolution** | ✅ Complete | 2.3, 3.1, 4.1 |
| **Exception Handling** | ✅ Complete | 3.5, 4.1 |
| **Custom Exceptions** | ✅ Complete | 3.5, 4.1 |
| **File I/O Operations** | ✅ Complete | 3.6, 4.1 |
| **Code Organization** | ✅ Complete | All exercises |
| **Best Practices** | ✅ Complete | All exercises |
| **SOLID Principles** | ✅ Complete | 3.2, 4.1 |

### Learning Path

`
Start Here
    ↓
Level 1: Core Concept Drills
├─ Exercise 1.1: Abstract Account Foundation
├─ Exercise 1.2: Customer Account Abstraction
└─ Exercise 1.3: Transaction Capability Interfaces
    ↓
Level 2: Applied Banking Features
├─ Exercise 2.1: Building a Complete Account Hierarchy
├─ Exercise 2.2: Account Transaction Interface System
├─ Exercise 2.3: Solving the Diamond Problem with Interfaces
└─ Exercise 2.4: Polymorphism in Banking Operations
    ↓
Level 3: System Design Challenges
├─ Exercise 3.1: Interface Inheritance and Extension
├─ Exercise 3.2: Single Responsibility and Interface Segregation
├─ Exercise 3.3: Advanced Interface Patterns
├─ Exercise 3.4: Polymorphic Collections and Processing
├─ Exercise 3.5: Exception Handling in Banking Systems
└─ Exercise 3.6: File I/O for Banking Data Persistence
    ↓
Level 4: Full System Integration
└─ Exercise 4.1: Complete Banking Digital Platform
    ↓
Mastery Achieved! 🎉
`

**Total Exercises:** 14 (3 Level 1 + 4 Level 2 + 6 Level 3 + 1 Level 4)

**Estimated Completion Time:**
- Level 1: 6-8 hours
- Level 2: 10-12 hours
- Level 3: 15-18 hours
- Level 4: 20-25 hours
- **Total: 51-63 hours**

Good luck with your banking system development journey! 🚀
