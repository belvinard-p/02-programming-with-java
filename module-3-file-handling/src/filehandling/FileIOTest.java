package filehandling;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileIOTest {
    private static final Logger logger = Logger.getLogger(FileIOTest.class.getName());
    private static final String TEST_ACCOUNT_ID = "001";
    private static final String TEST_OWNER_NAME = "Ahmad Hassan";
    private static final double TEST_BALANCE = 5000.0;
    private static final String TEST_FILE = "test_account.txt";
    private static final String CSV_FILE = "csv_account.csv";
    private static final String TRANSACTION_FILE = "transactions.csv";
    private static final String TEST_PASSED = "\u2705 Test passed: {0}";
    private static final String TEST_FAILED = "\u274c Test failed: {0}";

    public static void main(String[] args) {
        logger.info("=== File I/O Test Suite ===");
        testCreateSingleAccount();
        testCreateMultipleAccounts();
        testSaveAccountToFile();
        testSaveMultipleAccountToFile();
        testAppendTransactionToFile();
        logger.info("=== All tests completed ===");
    }

    private static void testCreateSingleAccount() {
        logger.info("--- Test: Create Single Bank Account ---");
        
        BankAccount account = createTestAccount(TEST_ACCOUNT_ID, TEST_OWNER_NAME, TEST_BALANCE);
        account.displayAccountInfo();
        
        if (account.getAccountId().equals(TEST_ACCOUNT_ID) && 
            account.getOwnerName().equals(TEST_OWNER_NAME) && 
            account.getBalance() == TEST_BALANCE) {
            logger.log(Level.INFO, TEST_PASSED, "Account created successfully");
        } else {
            logger.log(Level.WARNING, "\u274c Test failed: Account data mismatch");
        }
    }
    
    private static void testCreateMultipleAccounts() {
        logger.info("--- Test: Create Multiple Bank Accounts ---");
        
        BankAccount[] accounts = createTestAccounts();
        
        for (BankAccount account : accounts) {
            account.displayAccountInfo();
        }
        
        logger.log(Level.INFO, TEST_PASSED, accounts.length + " accounts created");
    }
    
    private static void testSaveAccountToFile() {
        logger.info("--- Test: Save Account to File ---");
        
        BankAccount account = createTestAccount(TEST_ACCOUNT_ID, TEST_OWNER_NAME, TEST_BALANCE);
        AccountDataWriter writer = new AccountDataWriter();
        
        try {
            writer.saveAccount(account, TEST_FILE);
            logger.log(Level.INFO, TEST_PASSED, "Account saved successfully");
        } catch (IOException e) {
            logger.log(Level.SEVERE, TEST_FAILED, e.getMessage());
        }
    }

    private static void testSaveMultipleAccountToFile() {
        logger.info("--- Test: Save Multiple Accounts to File ---");
        
        BankAccount[] accounts = createTestAccounts();
        AccountDataWriter writer = new AccountDataWriter();
        
        try {
            writer.saveMultipleAccounts(accounts, CSV_FILE);
            logger.log(Level.INFO, TEST_PASSED, "Multiple Accounts saved successfully to " + CSV_FILE);
        } catch (IOException e) {
            logger.log(Level.SEVERE, TEST_FAILED, e.getMessage());
        }
    }

    private static void testAppendTransactionToFile() {
        logger.info("--- Test: Append Transaction to File ---");
        AccountDataWriter writer = new AccountDataWriter();
        Transaction[] transactions = createTestTransactions();
        
        try {
            for (Transaction transaction : transactions) {
                writer.appendTransaction(TRANSACTION_FILE, transaction);
            }
            logger.log(Level.INFO, TEST_PASSED, transactions.length + " transactions appended successfully");
        } catch (IOException e) {
            logger.log(Level.SEVERE, TEST_FAILED, e.getMessage());
        }
    }
    
    private static BankAccount createTestAccount(String id, String name, double balance) {
        return new BankAccount(id, name, balance);
    }
    
    private static BankAccount[] createTestAccounts() {
        return new BankAccount[] {
            createTestAccount(TEST_ACCOUNT_ID, TEST_OWNER_NAME, TEST_BALANCE),
            createTestAccount("002", "Sarah Johnson", 10000.0),
            createTestAccount("003", "Mohamed Ali", 7500.0),
            createTestAccount("004", "Fatima Ahmed", 12000.0),
            createTestAccount("005", "Aisha Omar", 9500.0),
            createTestAccount("006", "Karim Chraibi", 11000.0),
            createTestAccount("007", "Youssef Bakr", 8500.0)
        };
    }
    
    private static Transaction createTestTransaction(String accountId, String type, double amount) {
        return new Transaction(accountId, type, amount);
    }

    private static Transaction[] createTestTransactions() {
        return new Transaction[] {
            createTestTransaction("001", "DEPOSIT", 500.0),
            createTestTransaction("002", "WITHDRAWAL", 200.0),
            createTestTransaction("003", "DEPOSIT", 1000.0),
            createTestTransaction("003", "WITHDRAWAL", 300.0)

        };
    }
}
