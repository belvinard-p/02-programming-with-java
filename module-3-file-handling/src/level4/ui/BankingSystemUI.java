package level4.ui;

import level4.exception.InvalidAccountException;
import level4.interfaces.Reportable;
import level4.model.*;
import level4.persistence.AccountPersistence;
import level4.persistence.ReportGenerator;
import level4.persistence.TransactionLogger;
import level4.service.AccountManager;
import level4.service.CustomerManager;
import level4.service.TransactionProcessor;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankingSystemUI {
    private static final Logger LOGGER = Logger.getLogger(BankingSystemUI.class.getName());
    private static final String ACCOUNTS_FILE = "02-programing-with-java/level4_accounts.txt";
    private static final String TRANSACTIONS_FILE = "02-programing-with-java/level4_transactions.txt";
    private static final String REPORT_FILE = "02-programing-with-java/level4_report.txt";

    private final Scanner scanner;
    private final AccountManager accountManager;
    private final CustomerManager customerManager;
    private final TransactionProcessor transactionProcessor;
    private final AccountPersistence accountPersistence;
    private final TransactionLogger transactionLogger;
    private final ReportGenerator reportGenerator;
    private int transactionCounter = 0;

    public BankingSystemUI() {
        this.scanner = new Scanner(System.in);
        this.accountManager = new AccountManager();
        this.customerManager = new CustomerManager();
        this.transactionProcessor = new TransactionProcessor();
        this.accountPersistence = new AccountPersistence();
        this.transactionLogger = new TransactionLogger();
        this.reportGenerator = new ReportGenerator();
    }

    public void start() {
        LOGGER.info("=== Banking Digital Platform Started ===");
        loadData();
        boolean running = true;

        while (running) {
            displayMainMenu();
            int choice = readInt("Choose option: ");

            switch (choice) {
                case 1:
                    handleCreateAccount();
                    break;
                case 2:
                    handleDeposit();
                    break;
                case 3:
                    handleWithdrawal();
                    break;
                case 4:
                    handleTransfer();
                    break;
                case 5:
                    handleBillPayment();
                    break;
                case 6:
                    handleViewAccounts();
                    break;
                case 7:
                    handleViewTransactions();
                    break;
                case 8:
                    handleGenerateReport();
                    break;
                case 9:
                    handleCloseAccount();
                    break;
                case 10:
                    handleAccountStatement();
                    break;
                case 0:
                    saveData();
                    running = false;
                    break;
                default:
                    LOGGER.warning("Invalid option");
            }
        }

        scanner.close();
        LOGGER.info("=== Banking Digital Platform Stopped ===");
    }

    private void displayMainMenu() {
        LOGGER.info("\n========== BANKING MENU ==========");
        LOGGER.info("1. Create Account");
        LOGGER.info("2. Deposit");
        LOGGER.info("3. Withdrawal");
        LOGGER.info("4. Transfer");
        LOGGER.info("5. Bill Payment");
        LOGGER.info("6. View All Accounts");
        LOGGER.info("7. View Transaction History");
        LOGGER.info("8. Generate Report");
        LOGGER.info("9. Close Account");
        LOGGER.info("10. Account Statement");
        LOGGER.info("0. Exit & Save");
        LOGGER.info("==================================");
    }

    private void handleCreateAccount() {
        LOGGER.info("\n--- Create Account ---");
        LOGGER.info("Types: SAVINGS, CHECKING, MONEY_MARKET, CD, BUSINESS");
        String type = readString("Account type: ");
        String holder = readString("Account holder name: ");
        double deposit = readDouble("Initial deposit: ");

        Account account = accountManager.createAccount(type, holder, deposit);
        LOGGER.log(Level.INFO, "Account created: {0}", account);
    }

    private void handleDeposit() {
        LOGGER.info("\n--- Deposit ---");
        String accountId = readString("Account ID: ");
        double amount = readDouble("Amount: ");

        try {
            Account account = accountManager.getAccount(accountId);
            Transaction transaction = new DepositTransaction(nextTransactionId(), amount, account, "TELLER");
            if (transactionProcessor.processTransaction(transaction)) {
                logTransaction(transaction);
                LOGGER.log(Level.INFO, "Deposit successful. New balance: {0}", account.getBalance());
            }
        } catch (InvalidAccountException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

    private void handleWithdrawal() {
        LOGGER.info("\n--- Withdrawal ---");
        String accountId = readString("Account ID: ");
        double amount = readDouble("Amount: ");

        try {
            Account account = accountManager.getAccount(accountId);
            Transaction transaction = new WithdrawalTransaction(nextTransactionId(), amount, account, "TELLER");
            if (transactionProcessor.processTransaction(transaction)) {
                logTransaction(transaction);
                LOGGER.log(Level.INFO, "Withdrawal successful. New balance: {0}", account.getBalance());
            } else {
                LOGGER.warning("Withdrawal failed. Check balance.");
            }
        } catch (InvalidAccountException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

    private void handleTransfer() {
        LOGGER.info("\n--- Transfer ---");
        String fromId = readString("From Account ID: ");
        String toId = readString("To Account ID: ");
        double amount = readDouble("Amount: ");

        try {
            Account from = accountManager.getAccount(fromId);
            Account to = accountManager.getAccount(toId);
            Transaction transaction = new TransferTransaction(nextTransactionId(), amount, from, to);
            if (transactionProcessor.processTransaction(transaction)) {
                logTransaction(transaction);
                LOGGER.info("Transfer successful.");
                LOGGER.log(Level.INFO, "From {0} balance: {1}", new Object[]{fromId, from.getBalance()});
                LOGGER.log(Level.INFO, "To {0} balance: {1}", new Object[]{toId, to.getBalance()});
            } else {
                LOGGER.warning("Transfer failed.");
            }
        } catch (InvalidAccountException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

    private void handleBillPayment() {
        LOGGER.info("\n--- Bill Payment ---");
        String accountId = readString("Account ID: ");
        String payee = readString("Payee: ");
        double amount = readDouble("Amount: ");

        try {
            Account account = accountManager.getAccount(accountId);
            Transaction transaction = new BillPaymentTransaction(nextTransactionId(), amount, account, payee, "BILL-" + payee);
            if (transactionProcessor.processTransaction(transaction)) {
                logTransaction(transaction);
                LOGGER.log(Level.INFO, "Bill payment successful. Balance: {0}", account.getBalance());
            } else {
                LOGGER.warning("Bill payment failed.");
            }
        } catch (InvalidAccountException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

    private void handleViewAccounts() {
        LOGGER.info("\n--- All Accounts ---");
        for (Account account : accountManager.getAllAccounts()) {
            LOGGER.log(Level.INFO, "{0}", account);
        }
        LOGGER.log(Level.INFO, "Total accounts: {0}", accountManager.getAccountCount());
        LOGGER.log(Level.INFO, "Total balance: {0}", accountManager.getTotalSystemBalance());
    }

    private void handleViewTransactions() {
        LOGGER.info("\n--- Transaction History ---");
        for (Transaction t : transactionProcessor.getTransactionHistory()) {
            LOGGER.log(Level.INFO, "{0}", t);
        }
        LOGGER.log(Level.INFO, "Total transactions: {0}", transactionProcessor.getTransactionCount());
    }

    private void handleGenerateReport() {
        try {
            reportGenerator.generateDailyReport(
                    accountManager.getAllAccounts(),
                    transactionProcessor.getTransactionHistory(),
                    REPORT_FILE
            );
            LOGGER.log(Level.INFO, "Report generated: {0}", REPORT_FILE);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to generate report: {0}", e.getMessage());
        }
    }

    private void handleAccountStatement() {
        LOGGER.info("\n--- Account Statement ---");
        String accountId = readString("Account ID: ");
        try {
            Account account = accountManager.getAccount(accountId);
            if (account instanceof Reportable) {
                Reportable reportable = (Reportable) account;
                LOGGER.info(reportable.generateStatement());
                LOGGER.info(reportable.generateMonthlyReport());
            } else {
                LOGGER.warning("This account type does not support statements.");
            }
        } catch (InvalidAccountException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

    private void handleCloseAccount() {
        LOGGER.info("\n--- Close Account ---");
        String accountId = readString("Account ID: ");
        try {
            accountManager.closeAccount(accountId);
            LOGGER.log(Level.INFO, "Account {0} closed.", accountId);
        } catch (InvalidAccountException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

    private void saveData() {
        try {
            accountPersistence.saveAllAccounts(accountManager.getAllAccounts(), ACCOUNTS_FILE);
            transactionLogger.logAllTransactions(transactionProcessor.getTransactionHistory(), TRANSACTIONS_FILE);
            LOGGER.info("Data saved successfully");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to save data: {0}", e.getMessage());
        }
    }

    private void loadData() {
        try {
            if (java.nio.file.Files.exists(java.nio.file.Paths.get(ACCOUNTS_FILE))) {
                for (Account account : accountPersistence.loadAllAccounts(ACCOUNTS_FILE)) {
                    accountManager.addAccount(account);
                }
                LOGGER.info("Existing data loaded");
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "No existing data found, starting fresh");
        }
    }

    private void logTransaction(Transaction transaction) {
        try {
            transactionLogger.logTransaction(transaction, TRANSACTIONS_FILE);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to log transaction: {0}", e.getMessage());
        }
    }

    private String nextTransactionId() {
        transactionCounter++;
        return String.format("TXN%05d", transactionCounter);
    }

    private String readString(String prompt) {
        LOGGER.info(prompt);
        return scanner.nextLine().trim();
    }

    private double readDouble(String prompt) {
        LOGGER.info(prompt);
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    private int readInt(String prompt) {
        LOGGER.info(prompt);
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}
