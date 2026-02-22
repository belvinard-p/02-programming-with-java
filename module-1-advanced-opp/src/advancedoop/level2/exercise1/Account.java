package advancedoop.level2.exercise1;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Account {
    private static Logger
    logger = Logger.getLogger(Account.class.getName());

    private String accountId;
    private double balance;
    private String dateOpened;
    private boolean isActive;

    protected Account(String accountId, double balance, String dateOpened) {
        this.accountId = accountId;
        this.balance = balance;
        this.dateOpened = dateOpened;
        this.isActive = true;
    }

    public abstract String getAccountType();
    public abstract double calculateMinimumBalance();
    public abstract boolean withdraw(double amount);

    public void deposit(double amount){
        if (!isActive && amount <= 0){
            logger.warning("Account is inactive or amount is less than or equal to zero");
            return;
        }

        balance += amount;
        logger.log(Level.INFO, "{0} deposited. New balance: {1}", new Object[]{amount, balance});
    }

    public double getBalance(){
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }
    
    public String getAccountId() {
        return accountId;
    }
    
    public String getDateOpened() {
        return dateOpened;
    }
    
    public boolean isActive() {
        return isActive;
    }

    public String closeAccount(){
        isActive = false;

        logger.log(Level.INFO, "Account {0} closed on {1}", new Object[]{accountId, dateOpened});

        return "Account closed";

    }

    public void displaySummary() {
        logger.log(Level.INFO, "Account ID: {0}, Balance: {1}, Date Opened: {2}, Active: {3}",
                new Object[]{accountId, balance, dateOpened, isActive});
    }
}
