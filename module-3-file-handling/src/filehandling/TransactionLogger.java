package filehandling;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionLogger {
    private static final Logger LOGGER = Logger.getLogger(TransactionLogger.class.getName());
    private static final String DEPOSIT_TYPE = "DEPOSIT";
    private static final String WITHDRAWAL_TYPE = "WITHDRAWAL";
    private static final String CURRENCY = " xaf";
    
    private final AccountDataWriter writer = new AccountDataWriter();
    private final AccountDataReader reader = new AccountDataReader();

    public void logTransaction(Transaction transaction, String logFile) throws IOException {
        writer.appendTransaction(logFile, transaction);
    }

    public void generateDailyReport(String logFile, String reportFile) throws IOException {
        Transaction[] transactions = reader.loadTransactions(logFile);
        ReportData data = aggregateTransactions(transactions);
        writeReport(reportFile, transactions.length, data);
        LOGGER.log(Level.INFO, "Daily report generated: {0}", reportFile);
    }

    private ReportData aggregateTransactions(Transaction[] transactions) {
        Map<String, Double> depositsByAccount = new HashMap<>();
        Map<String, Double> withdrawalsByAccount = new HashMap<>();
        Map<String, Integer> countByAccount = new HashMap<>();
        double totalDeposits = 0.0;
        double totalWithdrawals = 0.0;
        
        for (Transaction t : transactions) {
            String accountId = t.getAccountId();
            double amount = t.getAmount();
            
            countByAccount.put(accountId, countByAccount.getOrDefault(accountId, 0) + 1);
            
            if (DEPOSIT_TYPE.equals(t.getType())) {
                depositsByAccount.put(accountId, depositsByAccount.getOrDefault(accountId, 0.0) + amount);
                totalDeposits += amount;
            } else if (WITHDRAWAL_TYPE.equals(t.getType())) {
                withdrawalsByAccount.put(accountId, withdrawalsByAccount.getOrDefault(accountId, 0.0) + amount);
                totalWithdrawals += amount;
            }
        }
        
        return new ReportData(depositsByAccount, withdrawalsByAccount, countByAccount, totalDeposits, totalWithdrawals);
    }

    private void writeReport(String reportFile, int transactionCount, ReportData data) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(reportFile))) {
            writeSummary(writer, transactionCount, data);
            writeAccountDetails(writer, data);
        }
    }

    private void writeSummary(BufferedWriter writer, int count, ReportData data) throws IOException {
        writer.write("=== DAILY TRANSACTION REPORT ===");
        writer.newLine();
        writer.newLine();
        writer.write("Total Transactions: " + count);
        writer.newLine();
        writer.write("Total Deposits: " + data.totalDeposits + CURRENCY);
        writer.newLine();
        writer.write("Total Withdrawals: " + data.totalWithdrawals + CURRENCY);
        writer.newLine();
        writer.write("Net Change: " + (data.totalDeposits - data.totalWithdrawals) + CURRENCY);
        writer.newLine();
        writer.newLine();
    }

    private void writeAccountDetails(BufferedWriter writer, ReportData data) throws IOException {
        writer.write("=== BY ACCOUNT ===");
        writer.newLine();
        
        for (String accountId : data.countByAccount.keySet()) {
            writer.write("Account " + accountId + ":");
            writer.newLine();
            writer.write("  Transactions: " + data.countByAccount.get(accountId));
            writer.newLine();
            writer.write("  Deposits: " + data.depositsByAccount.getOrDefault(accountId, 0.0) + CURRENCY);
            writer.newLine();
            writer.write("  Withdrawals: " + data.withdrawalsByAccount.getOrDefault(accountId, 0.0) + CURRENCY);
            writer.newLine();
        }
    }

    public void archiveOldLogs(String logFile, String archiveFile) throws IOException {
        Files.copy(Paths.get(logFile), Paths.get(archiveFile), StandardCopyOption.REPLACE_EXISTING);
        clearLogFile(logFile);
        LOGGER.log(Level.INFO, "Archived {0} to {1} and cleared original", new Object[]{logFile, archiveFile});
    }

    private void clearLogFile(String logFile) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(logFile))) {
            writer.write("");
        }
    }

    private static class ReportData {
        final Map<String, Double> depositsByAccount;
        final Map<String, Double> withdrawalsByAccount;
        final Map<String, Integer> countByAccount;
        final double totalDeposits;
        final double totalWithdrawals;

        ReportData(Map<String, Double> depositsByAccount, Map<String, Double> withdrawalsByAccount,
                   Map<String, Integer> countByAccount, double totalDeposits, double totalWithdrawals) {
            this.depositsByAccount = depositsByAccount;
            this.withdrawalsByAccount = withdrawalsByAccount;
            this.countByAccount = countByAccount;
            this.totalDeposits = totalDeposits;
            this.totalWithdrawals = totalWithdrawals;
        }
    }
}
