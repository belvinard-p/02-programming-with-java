package filehandling;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileIOTest {
    private static final Logger LOGGER = Logger.getLogger(FileIOTest.class.getName());
    private static final String TEST_ACCOUNT_ID = "001";
    private static final String TEST_OWNER_NAME = "Ahmad Hassan";
    private static final double TEST_BALANCE = 5000.0;
    private static final String TEST_FILE = "02-programing-with-java/test_account.txt";
    private static final String CSV_FILE = "02-programing-with-java/multiple_account.csv";
    private static final String TRANSACTION_FILE = "02-programing-with-java/transactions.csv";
    private static final String BACKUP_FILE = "02-programing-with-java/test_account_backup.txt";
    private static final String RESTORE_FILE = "02-programing-with-java/test_account_restored.txt";
    private static final String TEST_PASSED = "\u2705 Test passed: {0}";
    private static final String TEST_FAILED = "\u274c Test failed: {0}";
    private static final String TRANSACTION_DEPOSIT = "DEPOSIT";
    private static final String TRANSACTION_WITHDRAWAL = "WITHDRAWAL";

    public static void main(String[] args) {
        LOGGER.info("=== File I/O Test Suite ===");
        testCreateSingleAccount();
        testCreateMultipleAccounts();
        testSaveAccountToFile();
        testSaveMultipleAccountToFile();
        testAppendTransactionToFile();
        testLoadAccount();
        testLoadMultipleAccounts();
        testLoadTransactions();
        testGenerateDailyReport();
        testArchiveOldLogs();
        testBackupAndRestore();
        testVerifyBackupIntegrity();
        LOGGER.info("=== All tests completed ===");
    }

    private static void testCreateSingleAccount() {
        LOGGER.info("--- Test: Create Single Bank Account ---");

        BankAccount account = createTestAccount(TEST_ACCOUNT_ID, TEST_OWNER_NAME, TEST_BALANCE);
        account.displayAccountInfo();

        if (account.getAccountId().equals(TEST_ACCOUNT_ID) &&
                account.getOwnerName().equals(TEST_OWNER_NAME) &&
                account.getBalance() == TEST_BALANCE) {
            LOGGER.log(Level.INFO, TEST_PASSED, "Account created successfully");
        } else {
            LOGGER.log(Level.WARNING, TEST_FAILED, "Account data mismatch");
        }
    }

    private static void testCreateMultipleAccounts() {
        LOGGER.info("--- Test: Create Multiple Bank Accounts ---");

        BankAccount[] accounts = createTestAccounts();

        for (BankAccount account : accounts) {
            account.displayAccountInfo();
        }

        LOGGER.log(Level.INFO, TEST_PASSED, accounts.length + " accounts created");
    }

    private static void testSaveAccountToFile() {
        LOGGER.info("--- Test: Save Account to File ---");

        BankAccount account = createTestAccount(TEST_ACCOUNT_ID, TEST_OWNER_NAME, TEST_BALANCE);
        AccountDataWriter writer = new AccountDataWriter();

        try {
            writer.saveAccount(account, TEST_FILE);
            LOGGER.log(Level.INFO, TEST_PASSED, "Account saved successfully");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, TEST_FAILED, e.getMessage());
        }
    }

    private static void testSaveMultipleAccountToFile() {
        LOGGER.info("--- Test: Save Multiple Accounts to File ---");

        BankAccount[] accounts = createTestAccounts();
        AccountDataWriter writer = new AccountDataWriter();

        try {
            writer.saveMultipleAccounts(accounts, CSV_FILE);
            LOGGER.log(Level.INFO, TEST_PASSED, "Multiple Accounts saved successfully to " + CSV_FILE);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, TEST_FAILED, e.getMessage());
        }
    }

    private static void testAppendTransactionToFile() {
        LOGGER.info("--- Test: Append Transaction to File ---");
        AccountDataWriter writer = new AccountDataWriter();
        Transaction[] transactions = createTestTransactions();

        try {
            for (Transaction transaction : transactions) {
                writer.appendTransaction(TRANSACTION_FILE, transaction);
            }
            LOGGER.log(Level.INFO, TEST_PASSED, transactions.length + " transactions appended successfully");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, TEST_FAILED, e.getMessage());
        }
    }

    private static void testLoadAccount() {
        LOGGER.info("--- Test: Load Account from File ---");
        AccountDataReader reader = new AccountDataReader();

        try {
            BankAccount loadedAccount = reader.loadAccount(TEST_FILE);
            loadedAccount.displayAccountInfo();
            if (loadedAccount.getAccountId().equals(TEST_ACCOUNT_ID) &&
                    loadedAccount.getOwnerName().equals(TEST_OWNER_NAME) &&
                    loadedAccount.getBalance() == TEST_BALANCE) {
                LOGGER.log(Level.INFO, TEST_PASSED, "Account loaded successfully");
            } else {
                LOGGER.log(Level.WARNING, TEST_FAILED, "Account data mismatch");
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, TEST_FAILED, e.getMessage());
        }
    }

    private static void testLoadMultipleAccounts() {
        LOGGER.info("--- Test: Load Multiple Accounts from File ---");
        AccountDataReader reader = new AccountDataReader();

        try {
            BankAccount[] loadedAccounts = reader.loadMultipleAccounts(CSV_FILE);
            for (BankAccount account : loadedAccounts) {
                account.displayAccountInfo();
            }
            LOGGER.log(Level.INFO, TEST_PASSED, loadedAccounts.length + " accounts loaded successfully");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, TEST_FAILED, e.getMessage());
        }
    }

    private static void testLoadTransactions() {
        LOGGER.info("--- Test: Load Transactions from File ---");
        AccountDataReader reader = new AccountDataReader();

        try {
            Transaction[] loadedTransactions = reader.loadTransactions(TRANSACTION_FILE);
            for (Transaction transaction : loadedTransactions) {
                transaction.displayAccountInfo();
            }
            LOGGER.log(Level.INFO, TEST_PASSED, loadedTransactions.length + " transactions loaded successfully");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, TEST_FAILED, e.getMessage());
        }
    }

    private static BankAccount createTestAccount(String id, String name, double balance) {
        return new BankAccount(id, name, balance);
    }

    private static BankAccount[] createTestAccounts() {
        return new BankAccount[]{
                createTestAccount(TEST_ACCOUNT_ID, TEST_OWNER_NAME, TEST_BALANCE),
                createTestAccount("002", "Sarah Johnson", 10000.0),
                createTestAccount("003", "Mohamed Ali", 7500.0),
                createTestAccount("004", "Fatima Ahmed", 12000.0),
                createTestAccount("005", "Aisha Omar", 9500.0),
                createTestAccount("006", "Karim Chraibi", 11000.0),
                createTestAccount("007", "Youssef Bakr", 8500.0),
                createTestAccount("008", "Pouadjeu Belvinard", 6500.0),
                createTestAccount("009", "Diallo Ibrahima", 13000.0),
                createTestAccount("010", "Nguyen Thi Lan", 14000.0),
                createTestAccount("011", "Jeanne Dubois", 15000.0),
                createTestAccount("012", "Maria Garcia", 16000.0),
                createTestAccount("013", "James Wilson", 17000.0),
                createTestAccount("014", "Anna Müller", 18000.0),
                createTestAccount("015", "Yuki Tanaka", 19000.0)
        };
    }

    private static Transaction createTestTransaction(String accountId, String type, double amount) {
        return new Transaction(accountId, type, amount);
    }

    private static Transaction[] createTestTransactions() {
        return new Transaction[]{
                createTestTransaction("001", TRANSACTION_DEPOSIT, 500.0),
                createTestTransaction("002", TRANSACTION_WITHDRAWAL, 200.0),
                createTestTransaction("003", TRANSACTION_DEPOSIT, 1000.0),
                createTestTransaction("004", TRANSACTION_WITHDRAWAL, 300.0),
                createTestTransaction("005", TRANSACTION_DEPOSIT, 750.0),
                createTestTransaction("006", TRANSACTION_WITHDRAWAL, 150.0),
                createTestTransaction("007", TRANSACTION_DEPOSIT, 2000.0)
        };
    }

    private static void testGenerateDailyReport() {
        LOGGER.info("--- Test: Generate Daily Report ---");
        TransactionLogger logger = new TransactionLogger();

        try {
            logger.generateDailyReport(TRANSACTION_FILE, "02-programing-with-java/daily_report.txt");
            LOGGER.log(Level.INFO, TEST_PASSED, "Daily report generated successfully");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, TEST_FAILED, e.getMessage());
        }
    }

    private static void testArchiveOldLogs() {
        LOGGER.info("--- Test: Archive Old Logs ---");
        TransactionLogger logger = new TransactionLogger();

        try {
            logger.archiveOldLogs(TRANSACTION_FILE, "02-programing-with-java/transactions_archive.csv");
            LOGGER.log(Level.INFO, TEST_PASSED, "Logs archived successfully");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, TEST_FAILED, e.getMessage());
        }
    }

    private static void testBackupAndRestore() {
        LOGGER.info("--- Test: Backup and Restore ---");
        BackupManager backupManager = new BackupManager();

        try {
            backupManager.createBackup(TEST_FILE, BACKUP_FILE);
            LOGGER.log(Level.INFO, TEST_PASSED, "Backup created successfully");
            
            backupManager.restoreFromBackup(BACKUP_FILE, RESTORE_FILE);
            LOGGER.log(Level.INFO, TEST_PASSED, "Restore completed successfully");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, TEST_FAILED, e.getMessage());
        }
    }

    private static void testVerifyBackupIntegrity() {
        LOGGER.info("--- Test: Verify Backup Integrity ---");
        BackupManager backupManager = new BackupManager();

        try {
            boolean isValid = backupManager.verifyBackupIntegrity(TEST_FILE, BACKUP_FILE);
            if (isValid) {
                LOGGER.log(Level.INFO, TEST_PASSED, "Backup integrity verified");
            } else {
                LOGGER.log(Level.WARNING, TEST_FAILED, "Backup integrity check failed");
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, TEST_FAILED, e.getMessage());
        }
    }
}
