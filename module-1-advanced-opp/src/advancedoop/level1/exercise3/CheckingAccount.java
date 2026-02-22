package advancedoop.level1.exercise3;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * CheckingAccount implementing Transactable interface with overdraft protection
 */
public class CheckingAccount implements Transactable {
    private String accountNumber;
    private String accountHolder;
    private double balance;
    private double overdraftLimit;
    private List<String> transactionHistory;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public CheckingAccount(String accountNumber, String accountHolder, double initialBalance, double overdraftLimit) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.overdraftLimit = overdraftLimit;
        this.transactionHistory = new ArrayList<>();
        addTransaction("ACCOUNT_CREATED", initialBalance, "Account created with initial balance");
    }
    
    @Override
    public boolean processTransaction(double amount, String transactionType) {
        if (amount <= 0) {
            addTransaction(transactionType, amount, "FAILED - Invalid amount");
            return false;
        }
        
        switch (transactionType.toUpperCase()) {
            case "DEPOSIT":
                balance += amount;
                addTransaction("DEPOSIT", amount, "Deposit successful");
                return true;
                
            case "WITHDRAWAL":
                // Allow withdrawal up to overdraft limit
                if (balance + overdraftLimit >= amount) {
                    balance -= amount;
                    addTransaction("WITHDRAWAL", amount, "Withdrawal successful" + 
                                 (balance < 0 ? " (OVERDRAFT)" : ""));
                    return true;
                } else {
                    addTransaction("WITHDRAWAL", amount, "FAILED - Exceeds overdraft limit");
                    return false;
                }
                
            case "TRANSFER":
                if (balance + overdraftLimit >= amount) {
                    balance -= amount;
                    addTransaction("TRANSFER", amount, "Transfer successful" + 
                                 (balance < 0 ? " (OVERDRAFT)" : ""));
                    return true;
                } else {
                    addTransaction("TRANSFER", amount, "FAILED - Exceeds overdraft limit");
                    return false;
                }
                
            default:
                addTransaction(transactionType, amount, "FAILED - Unknown transaction type");
                return false;
        }
    }
    
    @Override
    public String getTransactionHistory() {
        StringBuilder history = new StringBuilder();
        history.append("=== Transaction History for Checking Account: ").append(accountNumber).append(" ===\n");
        for (String transaction : transactionHistory) {
            history.append(transaction).append("\n");
        }
        return history.toString();
    }
    
    private void addTransaction(String type, double amount, String status) {
        String timestamp = LocalDateTime.now().format(DATE_FORMATTER);
        String transaction = String.format("[%s] %s: $%.2f - %s (Balance: $%.2f)", 
                                          timestamp, type, amount, status, balance);
        transactionHistory.add(transaction);
    }
    
    public boolean isOverdrawn() {
        return balance < 0;
    }
    
    public double getAvailableBalance() {
        return balance + overdraftLimit;
    }
    
    // Getters
    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolder() { return accountHolder; }
    public double getBalance() { return balance; }
    public double getOverdraftLimit() { return overdraftLimit; }
}
