package advancedoop.level2.exercise2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionInterfaceTest {
    private static final Logger logger = Logger.getLogger(TransactionInterfaceTest.class.getName());

    public static void main(String[] args) {
        logger.info("=== TRANSACTION INTERFACE SYSTEM TEST ===");
        logger.info("");
        
        testStandardSavingsAccount();
        testWithdrawalLimit();
        testDepositMethods();
        testPremiumCheckingAccount();
        testCheckWriting();
        testUnlimitedTransactions();
        testBusinessAccount();
        testWireTransfers();
        testTransactionTracking();
        
        logger.info("");
        logger.info("=== ALL TESTS COMPLETED ===");
    }
    
    private static void testStandardSavingsAccount() {
        logger.info("TEST 1: Standard Savings Account - Basic Operations");
        logger.info("---------------------------------------------------");
        
        StandardSavingsAccount savings = new StandardSavingsAccount("SAV-001", 1000.0);
        logger.log(Level.INFO, "Initial balance: ${0}", savings.getAvailableBalance());
        
        savings.deposit(500.0);
        logger.log(Level.INFO, "After deposit: ${0}", savings.getAvailableBalance());
        
        savings.withdraw(200.0);
        logger.log(Level.INFO, "After withdrawal: ${0}", savings.getAvailableBalance());
        logger.log(Level.INFO, "Withdrawal count: {0}", savings.getWithdrawalCount());
        logger.info("");
    }
    
    private static void testWithdrawalLimit() {
        logger.info("TEST 2: Federal Regulation D - 6 Withdrawal Limit");
        logger.info("---------------------------------------------------");
        
        StandardSavingsAccount savings = new StandardSavingsAccount("SAV-002", 5000.0);
        
        for (int i = 1; i <= 7; i++) {
            boolean success = savings.withdraw(100.0);
            if (success) {
                logger.log(Level.INFO, "Withdrawal #{0}: Success", i);
            } else {
                logger.log(Level.WARNING, "Withdrawal #{0}: Failed (limit reached)", i);
            }
        }
        
        logger.log(Level.INFO, "Total withdrawals: {0}", savings.getWithdrawalCount());
        logger.log(Level.INFO, "Final balance: ${0}", savings.getAvailableBalance());
        logger.info("");
    }
    
    private static void testDepositMethods() {
        logger.info("TEST 3: Deposit Methods");
        logger.info("------------------------");
        
        StandardSavingsAccount savings = new StandardSavingsAccount("SAV-003", 1000.0);
        logger.log(Level.INFO, "Account supports deposit methods: {0}", savings.getDepositMethod());
        
        savings.deposit(250.0);
        logger.log(Level.INFO, "Deposited via {0}", savings.getDepositMethod());
        logger.log(Level.INFO, "New balance: ${0}", savings.getAvailableBalance());
        logger.info("");
    }
    
    private static void testPremiumCheckingAccount() {
        logger.info("TEST 4: Premium Checking Account - Basic Operations");
        logger.info("----------------------------------------------------");
        
        PremiumCheckingAccount checking = new PremiumCheckingAccount("CHK-001", 2000.0);
        logger.log(Level.INFO, "Initial balance: ${0}", checking.getAvailableBalance());
        logger.log(Level.INFO, "Deposit methods: {0}", checking.getDepositMethod());
        
        checking.deposit(1000.0);
        checking.withdraw(500.0);
        logger.log(Level.INFO, "After transactions: ${0}", checking.getAvailableBalance());
        logger.info("");
    }
    
    private static void testCheckWriting() {
        logger.info("TEST 5: Check Writing Capability");
        logger.info("---------------------------------");
        
        PremiumCheckingAccount checking = new PremiumCheckingAccount("CHK-002", 5000.0);
        logger.log(Level.INFO, "Initial checks remaining: {0}", checking.getRemainingChecks());
        
        checking.writeCheck("Electric Company", 150.0, "1001");
        checking.writeCheck("Water Utility", 75.0, "1002");
        checking.writeCheck("Internet Provider", 99.99, "1003");
        
        logger.log(Level.INFO, "Checks remaining: {0}", checking.getRemainingChecks());
        logger.log(Level.INFO, "Final balance: ${0}", checking.getAvailableBalance());
        logger.info("");
    }
    
    private static void testUnlimitedTransactions() {
        logger.info("TEST 6: Unlimited Transactions (No Withdrawal Limit)");
        logger.info("-----------------------------------------------------");
        
        PremiumCheckingAccount checking = new PremiumCheckingAccount("CHK-003", 10000.0);
        
        logger.info("Performing 10 withdrawals (no limit for premium checking):");
        for (int i = 1; i <= 10; i++) {
            boolean success = checking.withdraw(100.0);
            if (success) {
                logger.log(Level.INFO, "Withdrawal #{0}: Success", i);
            }
        }
        
        logger.log(Level.INFO, "Final balance: ${0}", checking.getAvailableBalance());
        logger.info("All transactions completed successfully - no limits!");
        logger.info("");
    }
    
    private static void testBusinessAccount() {
        logger.info("TEST 7: Business Account - All Transaction Types");
        logger.info("--------------------------------------------------");
        
        BusinessAccount business = new BusinessAccount("BUS-001", 50000.0);
        logger.log(Level.INFO, "Initial balance: ${0}", business.getAvailableBalance());
        logger.log(Level.INFO, "Wire transfer limit: ${0}", business.getWireTransferLimit());
        
        business.deposit(10000.0);
        business.withdraw(5000.0);
        business.writeCheck("Office Supplies Inc", 2500.0, "2001");
        business.sendWire("Chase Bank", "987654321", 15000.0);
        
        logger.log(Level.INFO, "Final balance: ${0}", business.getAvailableBalance());
        logger.info("");
    }
    
    private static void testWireTransfers() {
        logger.info("TEST 8: Wire Transfer Capabilities");
        logger.info("-----------------------------------");
        
        BusinessAccount business = new BusinessAccount("BUS-002", 200000.0);
        
        logger.info("Testing wire transfers:");
        business.sendWire("Bank of America", "111222333", 25000.0);
        business.sendWire("Wells Fargo", "444555666", 50000.0);
        
        logger.info("\nTesting wire transfer limit:");
        boolean exceeded = business.sendWire("Citibank", "777888999", 150000.0);
        if (!exceeded) {
            logger.info("Transfer correctly rejected - exceeds limit");
        }
        
        logger.log(Level.INFO, "Final balance: ${0}", business.getAvailableBalance());
        logger.info("");
    }
    
    private static void testTransactionTracking() {
        logger.info("TEST 9: Transaction Type Tracking");
        logger.info("----------------------------------");
        
        BusinessAccount business = new BusinessAccount("BUS-003", 100000.0);
        
        business.deposit(5000.0);
        business.deposit(3000.0);
        business.withdraw(2000.0);
        business.writeCheck("Vendor A", 1500.0, "3001");
        business.writeCheck("Vendor B", 2500.0, "3002");
        business.sendWire("International Bank", "123456789", 10000.0);
        
        logger.info("");
        business.displayTransactionSummary();
        logger.info("");
    }
}
