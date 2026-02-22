package advancedoop.level1.exercise3;

import java.util.logging.Logger;
import java.util.logging.Level;

public class BankingServiceTest {
    private static final Logger logger = Logger.getLogger(BankingServiceTest.class.getName());
    
    public static void main(String[] args) {
        logger.info("\n" + "=".repeat(80));
        logger.info("EXERCISE 1.3: INTERFACE IMPLEMENTATION - COMPREHENSIVE TEST SUITE");
        logger.info("=".repeat(80) + "\n");
        
        // TEST 1: Create and test SavingsAccount (implements both interfaces)
        test1_CreateSavingsAccounts();
        
        // TEST 2: Create and test CheckingAccount (implements Transactable)
        test2_CreateCheckingAccounts();
        
        // TEST 3: Demonstrate polymorphism with Transactable interface
        test3_PolymorphismWithTransactable();
        
        // TEST 4: Test transaction processing
        test4_TransactionProcessing();
        
        // TEST 5: Test reporting capabilities
        test5_ReportingCapabilities();
        
        // TEST 6: Test data export in different formats
        test6_DataExport();
        
        // TEST 7: Test overdraft protection in CheckingAccount
        test7_OverdraftProtection();
        
        // TEST 8: Test interest calculation
        test8_InterestCalculation();
        
        // TEST 9: Interface concepts demonstration
        test9_InterfaceConcepts();
        
        logger.info("\n" + "=".repeat(80));
        logger.info("ALL TESTS COMPLETED SUCCESSFULLY!");
        logger.info("=".repeat(80));
    }
    
    private static void test1_CreateSavingsAccounts() {
        logger.info("\n╔════════════════════════════════════════════════════════════╗");
        logger.info("║ TEST 1: Creating Savings Accounts                        ║");
        logger.info("╚════════════════════════════════════════════════════════════╝");
        
        SavingsAccount savings1 = new SavingsAccount("SAV-001",
                "Alice Johnson", 5000.0, 3.5);
        SavingsAccount savings2 = new SavingsAccount("SAV-002", "Bob Smith",
                10000.0, 4.0);
        
        logger.log(Level.INFO, "Created savings account: {0} for {1} with balance ${2}", 
                  new Object[]{savings1.getAccountNumber(), savings1.getAccountHolder(), savings1.getBalance()});
        logger.log(Level.INFO, "Created savings account: {0} for {1} with balance ${2}", 
                  new Object[]{savings2.getAccountNumber(), savings2.getAccountHolder(), savings2.getBalance()});
        
        logger.info("✓ Savings accounts created successfully");
    }
    
    private static void test2_CreateCheckingAccounts() {
        logger.info("\n╔════════════════════════════════════════════════════════════╗");
        logger.info("║ TEST 2: Creating Checking Accounts                       ║");
        logger.info("╚════════════════════════════════════════════════════════════╝");
        
        CheckingAccount checking1 = new CheckingAccount("CHK-001", "Charlie Brown",
                3000.0, 500.0);
        CheckingAccount checking2 = new CheckingAccount("CHK-002", "Diana Prince",
                7500.0, 1000.0);
        
        logger.log(Level.INFO, "Created checking account: {0} for {1} with balance ${2}", 
                  new Object[]{checking1.getAccountNumber(), checking1.getAccountHolder(), checking1.getBalance()});
        logger.log(Level.INFO, "Overdraft limit: ${0}", checking1.getOverdraftLimit());
        logger.log(Level.INFO, "Created checking account: {0} for {1} with balance ${2}", 
                  new Object[]{checking2.getAccountNumber(), checking2.getAccountHolder(), checking2.getBalance()});
        logger.log(Level.INFO, "Overdraft limit: ${0}", checking2.getOverdraftLimit());
        
        logger.info("✓ Checking accounts created successfully");
    }
    
    private static void test3_PolymorphismWithTransactable() {
        logger.info("\n╔════════════════════════════════════════════════════════════╗");
        logger.info("║ TEST 3: Polymorphism with Transactable Interface         ║");
        logger.info("╚════════════════════════════════════════════════════════════╝");
        
        // Using interface reference for polymorphism
        Transactable account1 = new SavingsAccount("SAV-100", "Emma Watson",
                2000.0, 3.0);
        Transactable account2 = new CheckingAccount("CHK-100", "Frank Castle",
                1500.0, 300.0);
        
        logger.info("Processing transactions through Transactable interface reference:");
        
        boolean result1 = account1.processTransaction(500.0, "DEPOSIT");
        logger.log(Level.INFO, "Deposit to SavingsAccount: {0}", result1 ? "SUCCESS" : "FAILED");
        
        boolean result2 = account2.processTransaction(200.0, "WITHDRAWAL");
        logger.log(Level.INFO, "Withdrawal from CheckingAccount: {0}", result2 ? "SUCCESS" : "FAILED");
        
        logger.info("✓ Polymorphism demonstrated successfully");
    }
    
    private static void test4_TransactionProcessing() {
        logger.info("\n╔════════════════════════════════════════════════════════════╗");
        logger.info("║ TEST 4: Transaction Processing                           ║");
        logger.info("╚════════════════════════════════════════════════════════════╝");
        
        SavingsAccount savings = new SavingsAccount("SAV-200", "Grace Hopper",
                1000.0, 2.5);
        
        logger.info("Initial balance: $" + savings.getBalance());
        
        // Test various transactions
        logger.info("\n--- Performing Multiple Transactions ---");
        savings.processTransaction(500.0, "DEPOSIT");
        logger.info("After deposit of $500: Balance = $" + savings.getBalance());
        
        savings.processTransaction(200.0, "WITHDRAWAL");
        logger.info("After withdrawal of $200: Balance = $" + savings.getBalance());
        
        savings.processTransaction(2000.0, "WITHDRAWAL");
        logger.info("After attempting to withdraw $2000: Balance = $" + savings.getBalance());
        
        boolean invalidTransaction = savings.processTransaction(-100.0, "DEPOSIT");
        logger.log(Level.INFO, "Attempting invalid transaction (negative amount): {0}", 
                  invalidTransaction ? "SUCCESS" : "FAILED (as expected)");
        
        logger.info("\n" + savings.getTransactionHistory());
        logger.info("✓ Transaction processing tested successfully");
    }
    
    private static void test5_ReportingCapabilities() {
        logger.info("\n╔════════════════════════════════════════════════════════════╗");
        logger.info("║ TEST 5: Reporting Capabilities                           ║");
        logger.info("╚════════════════════════════════════════════════════════════╝");
        
        SavingsAccount savings = new SavingsAccount("SAV-300", "Patou Tchoko",
                15000.0, 4.5);
        savings.processTransaction(2000.0, "DEPOSIT");
        savings.processTransaction(500.0, "WITHDRAWAL");
        
        // Test Reportable interface
        logger.info("Generating account report:");
        logger.info(savings.generateReport());
        
        logger.info("✓ Report generation successful");
    }
    
    private static void test6_DataExport() {
        logger.info("\n╔════════════════════════════════════════════════════════════╗");
        logger.info("║ TEST 6: Data Export in Multiple Formats                  ║");
        logger.info("╚════════════════════════════════════════════════════════════╝");
        
        SavingsAccount savings = new SavingsAccount("SAV-400", "Irene Curie",
                8000.0, 3.75);
        savings.processTransaction(1000.0, "DEPOSIT");
        
        logger.info("\n--- CSV Export ---");
        logger.info(savings.exportData("CSV"));
        
        logger.info("\n--- JSON Export ---");
        logger.info(savings.exportData("JSON"));
        
        logger.info("\n--- XML Export ---");
        logger.info(savings.exportData("XML"));
        
        logger.info("\n--- Unsupported Format Test ---");
        logger.info(savings.exportData("PDF"));
        
        logger.info("\n✓ Data export in multiple formats successful");
    }
    
    private static void test7_OverdraftProtection() {
        logger.info("\n╔════════════════════════════════════════════════════════════╗");
        logger.info("║ TEST 7: Overdraft Protection in Checking Account         ║");
        logger.info("╚════════════════════════════════════════════════════════════╝");
        
        CheckingAccount checking = new CheckingAccount("CHK-300", "Jack Ryan",
                1000.0, 500.0);
        
        logger.info("Initial balance: $" + checking.getBalance());
        logger.info("Overdraft limit: $" + checking.getOverdraftLimit());
        logger.info("Available balance: $" + checking.getAvailableBalance());
        
        logger.info("\n--- Testing Overdraft ---");
        checking.processTransaction(1200.0, "WITHDRAWAL");
        logger.log(Level.INFO, "After withdrawing $1200: Balance = ${0}", checking.getBalance());
        logger.log(Level.INFO, "Is overdrawn? {0}", checking.isOverdrawn());
        
        logger.info("\n--- Attempting to Exceed Overdraft Limit ---");
        boolean result = checking.processTransaction(500.0, "WITHDRAWAL");
        logger.log(Level.INFO, "Attempting to withdraw $500 (exceeds limit): {0}", 
                  result ? "SUCCESS" : "FAILED (as expected)");
        
        logger.info("\n" + checking.getTransactionHistory());
        logger.info("✓ Overdraft protection tested successfully");
    }
    
    private static void test8_InterestCalculation() {
        logger.info("\n╔════════════════════════════════════════════════════════════╗");
        logger.info("║ TEST 8: Interest Calculation                             ║");
        logger.info("╚════════════════════════════════════════════════════════════╝");
        
        SavingsAccount savings = new SavingsAccount("SAV-500", "Kate Winslet",
                10000.0, 5.0);
        
        logger.info("Initial balance: $" + savings.getBalance());
        logger.info("Interest rate: " + savings.getInterestRate() + "%");
        
        double initialBalance = savings.getBalance();
        savings.applyInterest();
        double newBalance = savings.getBalance();
        double interestEarned = newBalance - initialBalance;
        
        logger.log(Level.INFO, "After applying interest: Balance = ${0}", newBalance);
        logger.log(Level.INFO, "Interest earned: ${0}", String.format("%.2f", interestEarned));
        
        logger.info("✓ Interest calculation successful");
    }
    
    private static void test9_InterfaceConcepts() {
        logger.info("\n╔════════════════════════════════════════════════════════════╗");
        logger.info("║ TEST 9: Interface Concepts Demonstration                 ║");
        logger.info("╚════════════════════════════════════════════════════════════╝");
        
        logger.info("\n📚 KEY INTERFACE CONCEPTS DEMONSTRATED:");
        logger.info("─".repeat(60));
        
        logger.info("\n1. INTERFACE DEFINITION:");
        logger.info("   • Transactable interface defines transaction operations");
        logger.info("   • Reportable interface defines reporting operations");
        logger.info("   • Interfaces contain only method signatures (abstract by default)");
        
        logger.info("\n2. MULTIPLE INTERFACE IMPLEMENTATION:");
        logger.info("   • SavingsAccount implements BOTH Transactable AND Reportable");
        logger.info("   • CheckingAccount implements ONLY Transactable");
        logger.info("   • A class can implement multiple interfaces");
        
        logger.info("\n3. POLYMORPHISM:");
        SavingsAccount savings = new SavingsAccount("SAV-999", "Test User",
                1000.0, 2.0);
        Transactable trans = savings;  // Polymorphic reference
        Reportable report = savings;   // Another polymorphic reference
        
        logger.info("   • Same object referenced as SavingsAccount, Transactable, and Reportable");
        logger.info("   • trans.processTransaction() works (Transactable method)");
        logger.info("   • report.generateReport() works (Reportable method)");
        
        trans.processTransaction(100.0, "DEPOSIT");
        logger.info("   ✓ Transaction processed through Transactable reference");

        String reportStr = report.generateReport();
        logger.info("   ✓ Report generated through Reportable reference");
        
        logger.info("\n4. CONTRACT ENFORCEMENT:");
        logger.info("   • Classes implementing interfaces MUST implement all methods");
        logger.info("   • Provides a contract that classes must follow");
        logger.info("   • Enables loose coupling and flexibility");
        
        logger.info("\n5. INTERFACE VS ABSTRACT CLASS:");
        logger.info("   • Interfaces: Cannot have instance variables (only constants)");
        logger.info("   • Interfaces: All methods are abstract (pre-Java 8)");
        logger.info("   • Interfaces: Support multiple inheritance");
        logger.info("   • Abstract Classes: Can have both abstract and concrete methods");
        
        logger.info("\n✓ Interface concepts demonstrated successfully");
    }
}
