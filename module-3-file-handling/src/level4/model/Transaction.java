package level4.model;

import level4.exception.BankingException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Transaction {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    protected String transactionId;
    protected double amount;
    protected String timestamp;
    protected String status;

    protected Transaction(String transactionId, double amount) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.timestamp = LocalDateTime.now().format(FORMATTER);
        this.status = "PENDING";
    }

    public abstract boolean process() throws BankingException;

    public abstract String getTransactionType();

    public void markCompleted() {
        this.status = "COMPLETED";
    }

    public void markFailed() {
        this.status = "FAILED";
    }

    public String toFileFormat() {
        return transactionId + "|" + getTransactionType() + "|" + amount + "|" + status + "|" + timestamp;
    }

    @Override
    public String toString() {
        return "[" + getTransactionType() + "] " + transactionId + " | " + amount + " | " + status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getStatus() {
        return status;
    }
}
