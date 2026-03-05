package filehandling;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Transaction {
    private static final Logger logger = Logger.getLogger(Transaction.class.getName());
    private static final String SEPARATOR = ";";
    private static final String CURRENCY = "xaf";

    private final String accountId;
    private final String type;
    private final double amount;
    private final LocalDateTime dateTime;

    public Transaction(String accountId, String type, double amount) {
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
    }

    public Transaction(String accountId, String type,
                       double amount, LocalDateTime dateTime) {
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.dateTime = dateTime;
    }

    public String getAccountId() {
        return accountId;
    }
    
    public String getType() {
        return type;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String toCSV() {
        DateTimeFormatter fmt = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss");
        return accountId + SEPARATOR + type + SEPARATOR + amount + SEPARATOR 
               + dateTime.format(fmt);
    }

    @Override
    public String toString() {
        return "[" + dateTime + "] " + type + " de " 
               + amount + " " + CURRENCY + " sur " + accountId;
    }

    public void displayAccountInfo() {
        logger.log(Level.INFO, this::toString);
    }
}
