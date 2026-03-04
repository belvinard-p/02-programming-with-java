package filehandling;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BankAccount {
    private static final Logger logger = Logger.getLogger(BankAccount.class.getName());
    private static final String CURRENCY = "xaf";
    
    private final String accountId;
    private final String ownerName;
    private final double balance;

    public BankAccount(String accountId, String ownerName, double balance) {
        this.accountId = accountId;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }
    
    public String getOwnerName() {
        return ownerName;
    }
    
    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return  accountId + " | " + ownerName + " | " + balance + " " + CURRENCY;
    }

    public void displayAccountInfo() {
        logger.log(Level.INFO, this::toString);
    }
}