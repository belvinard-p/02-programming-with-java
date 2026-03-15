package level4.persistence;

import level4.interfaces.Reportable;
import level4.model.Account;
import level4.model.Transaction;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportGenerator {
    private static final Logger LOGGER = Logger.getLogger(ReportGenerator.class.getName());

    public void generateDailyReport(List<Account> accounts, List<Transaction> transactions,
                                    String filename) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
            writeHeader(writer, "DAILY BANKING REPORT");
            writeAccountSummary(writer, accounts);
            writeTransactionSummary(writer, transactions);
            LOGGER.log(Level.INFO, "Daily report generated: {0}", filename);
        }
    }

    public void generateCustomerReport(String customerId, Account[] accounts, List<Transaction> transactions,
                                       String filename) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
            writeHeader(writer, "CUSTOMER REPORT - " + customerId);
            writeLine(writer, "Accounts: " + accounts.length);
            writer.newLine();
            for (Account account : accounts) {
                writeLine(writer, account.toString());
            }
            writer.newLine();
            writeLine(writer, "Transactions: " + transactions.size());
            for (Transaction t : transactions) {
                writeLine(writer, t.toString());
            }
        }
    }

    private void writeHeader(BufferedWriter writer, String title) throws IOException {
        writeLine(writer, "=== " + title + " ===");
        writer.newLine();
    }

    private void writeAccountSummary(BufferedWriter writer, List<Account> accounts) throws IOException {
        writeLine(writer, "--- Account Summary ---");
        writeLine(writer, "Total Accounts: " + accounts.size());

        double totalBalance = 0;
        Map<String, Integer> typeCount = new HashMap<>();
        Map<String, Double> typeBalance = new HashMap<>();

        for (Account account : accounts) {
            totalBalance += account.getBalance();
            String type = account.getAccountType();
            typeCount.put(type, typeCount.getOrDefault(type, 0) + 1);
            typeBalance.put(type, typeBalance.getOrDefault(type, 0.0) + account.getBalance());
        }

        writeLine(writer, "Total System Balance: " + totalBalance);
        writer.newLine();

        for (String type : typeCount.keySet()) {
            writeLine(writer, type + ": " + typeCount.get(type) + " accounts ($" + typeBalance.get(type) + ")");
        }
        writer.newLine();

        for (Account account : accounts) {
            if (account instanceof Reportable) {
                writeLine(writer, ((Reportable) account).generateStatement());
                writer.newLine();
            }
        }
    }

    private void writeTransactionSummary(BufferedWriter writer, List<Transaction> transactions) throws IOException {
        writeLine(writer, "--- Transaction Summary ---");
        writeLine(writer, "Total Transactions: " + transactions.size());

        Map<String, Integer> typeCount = new HashMap<>();
        Map<String, Double> typeAmount = new HashMap<>();
        int completed = 0;
        int failed = 0;

        for (Transaction t : transactions) {
            String type = t.getTransactionType();
            typeCount.put(type, typeCount.getOrDefault(type, 0) + 1);
            typeAmount.put(type, typeAmount.getOrDefault(type, 0.0) + t.getAmount());
            if ("COMPLETED".equals(t.getStatus())) {
                completed++;
            } else if ("FAILED".equals(t.getStatus())) {
                failed++;
            }
        }

        writeLine(writer, "Completed: " + completed + " | Failed: " + failed);
        writer.newLine();

        for (String type : typeCount.keySet()) {
            writeLine(writer, type + ": " + typeCount.get(type) + " transactions ($" + typeAmount.get(type) + ")");
        }
    }

    private void writeLine(BufferedWriter writer, String text) throws IOException {
        writer.write(text);
        writer.newLine();
    }
}
