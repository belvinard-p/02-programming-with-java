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

    public static void main(String[] args) {
        logger.info("=== File I/O Test Suite ===");
        testCreateSingleAccount();
        testCreateMultipleAccounts();
        testSaveAccountToFile();
        logger.info("=== All tests completed ===");
    }

    private static void testCreateSingleAccount() {
        logger.info("--- Test: Create Single Bank Account ---");
        
        BankAccount account = createTestAccount(TEST_ACCOUNT_ID, TEST_OWNER_NAME, TEST_BALANCE);
        account.displayAccountInfo();
        
        if (account.getAccountId().equals(TEST_ACCOUNT_ID) && 
            account.getOwnerName().equals(TEST_OWNER_NAME) && 
            account.getBalance() == TEST_BALANCE) {
            logger.log(Level.INFO, "\u2705 Test passed: Account created successfully");
        } else {
            logger.log(Level.WARNING, "\u274c Test failed: Account data mismatch");
        }
    }
    
    private static void testCreateMultipleAccounts() {
        logger.info("--- Test: Create Multiple Bank Accounts ---");
        
        BankAccount[] accounts = {
            createTestAccount(TEST_ACCOUNT_ID, TEST_OWNER_NAME, TEST_BALANCE),
            createTestAccount("002", "Sarah Johnson", 10000.0),
            createTestAccount("003", "Mohamed Ali", 7500.0),
            createTestAccount("004", "Fatima Ahmed", 12000.0),
            createTestAccount("005", "Youssef Bakr", 8500.0)
        };
        
        for (BankAccount account : accounts) {
            account.displayAccountInfo();
        }
        
        logger.log(Level.INFO, "\u2705 Test passed: {0} accounts created", accounts.length);
    }
    
    private static void testSaveAccountToFile() {
        logger.info("--- Test: Save Account to File ---");
        
        BankAccount account = createTestAccount(TEST_ACCOUNT_ID, TEST_OWNER_NAME, TEST_BALANCE);
        AccountDataWriter writer = new AccountDataWriter();
        
        try {
            writer.saveAccount(account, TEST_FILE);
            logger.log(Level.INFO, "\u2705 Test passed: Account saved successfully");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "\u274c Test failed: {0}", e.getMessage());
        }
    }
    
    private static BankAccount createTestAccount(String id, String name, double balance) {
        return new BankAccount(id, name, balance);
    }
}
