# Banking Digital System - Complete Exercise Roadmap

## Module Coverage: Advanced Object-Oriented Programming

This comprehensive exercise roadmap ensures 100% coverage of Module 1 concepts through a progressive Banking Digital System implementation.

---

## Concept Mapping

### Core Concepts Covered in Module 1

| Concept | Description | Exercise Coverage |
|---------|-------------|-------------------|
| **Abstraction** | Hiding complex implementation details | Ex 1.1, 1.2 |
| **Abstract Classes** | Templates for related classes that cannot be instantiated | Ex 1.1, 1.2, 2.1 |
| **Abstract Methods** | Methods without implementation requiring override | Ex 1.1, 1.2, 2.1 |
| **Interfaces** | Contracts defining capabilities | Ex 1.3, 2.2, 2.3, 3.1 |
| **Multiple Interface Implementation** | Classes implementing multiple capabilities | Ex 2.3, 3.2 |
| **Interface Inheritance** | Interfaces extending other interfaces | Ex 3.1 |
| **Class Hierarchy Design** | Organized structure of related classes | Ex 2.1, 2.2 |
| **Polymorphism** | Objects taking multiple forms | Ex 2.4 |
| **Encapsulation** | Data hiding and access control | All exercises |
| **Single Responsibility Principle** | One class, one purpose | Ex 2.1, 3.2 |
| **DRY Principle** | Don't Repeat Yourself | Ex 2.1 |
| **Diamond Problem Resolution** | Using interfaces to avoid multiple inheritance issues | Ex 2.3, 3.1 |

---

## Exercise Progression Structure

```mermaid
Level 1: Core Concept Drills (Exercises 1.1 - 1.3)
    
Level 2: Applied Banking Features (Exercises 2.1 - 2.4)
    
Level 3: Design Best Practices (Exercises 3.1 - 3.2)
``` 

---

# LEVEL 1: Core Concept Drills

## Exercise 1.1: Abstract Account Foundation

### Banking Scenario Context
I'm building the foundational structure for a digital banking system. All bank accounts share common properties (account number, balance, owner) but have different transaction rules and fee structures.

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

### Required Classes
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
- Abstract method: `widraw(double amount)` 
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

```mermaid
         BankAccount
           /      \
          /        \
   SavingsFeature  CheckingFeature
          \        /
           \      /
        StudentAccount Cannot extend both!
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

