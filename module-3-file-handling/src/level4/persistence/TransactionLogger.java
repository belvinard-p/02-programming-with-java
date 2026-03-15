package level4.persistence;

import level4.model.Transaction;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionLogger {
    private static final Logger LOGGER = Logger.getLogger(TransactionLogger.class.getName());

    public void logTransaction(Transaction transaction, String logFile) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(logFile), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(transaction.toFileFormat());
            writer.newLine();
        }
    }

    public void logAllTransactions(List<Transaction> transactions, String logFile) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(logFile))) {
            for (Transaction transaction : transactions) {
                writer.write(transaction.toFileFormat());
                writer.newLine();
            }
            LOGGER.log(Level.INFO, "Logged {0} transactions to {1}", new Object[]{transactions.size(), logFile});
        }
    }
}
