package level4.service;

import level4.exception.BankingException;
import level4.model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionProcessor {
    private static final Logger LOGGER = Logger.getLogger(TransactionProcessor.class.getName());
    private final List<Transaction> transactionHistory = new ArrayList<>();

    public boolean processTransaction(Transaction transaction) {
        try {
            boolean result = transaction.process();
            transactionHistory.add(transaction);
            return result;
        } catch (BankingException e) {
            transactionHistory.add(transaction);
            LOGGER.log(Level.WARNING, "Transaction failed: {0} - {1}",
                    new Object[]{transaction.getTransactionId(), e.getMessage()});
            return false;
        }
    }

    public int processBatch(Transaction[] transactions) {
        int successCount = 0;
        for (Transaction transaction : transactions) {
            if (processTransaction(transaction)) {
                successCount++;
            }
        }
        LOGGER.log(Level.INFO, "Batch processed: {0}/{1} successful", new Object[]{successCount, transactions.length});
        return successCount;
    }

    public Transaction[] getTransactionsByType(String type) {
        List<Transaction> filtered = new ArrayList<>();
        for (Transaction t : transactionHistory) {
            if (t.getTransactionType().equals(type)) {
                filtered.add(t);
            }
        }
        return filtered.toArray(new Transaction[0]);
    }

    public double calculateTotalAmount() {
        double total = 0;
        for (Transaction t : transactionHistory) {
            total += t.getAmount();
        }
        return total;
    }

    public List<Transaction> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }

    public int getTransactionCount() { return transactionHistory.size(); }
}
