package advancedoop.level1.exercise3;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * SavingsAccount implementing both Transactable and Reportable interfaces
 */
public class SavingsAccount implements Transactable, Reportable {
    private String accountNumber;
    private String accountHolder;
    private double balance;
    private double interestRate;
    private List<String> transactionHistory;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public SavingsAccount(String accountNumber, String accountHolder, double initialBalance, double interestRate) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.interestRate = interestRate;
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
                if (balance >= amount) {
                    balance -= amount;
                    addTransaction("WITHDRAWAL", amount, "Withdrawal successful");
                    return true;
                } else {
                    addTransaction("WITHDRAWAL", amount, "FAILED - Insufficient funds");
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
        history.append("=== Transaction History for Account: ").append(accountNumber).append(" ===\n");
        for (String transaction : transactionHistory) {
            history.append(transaction).append("\n");
        }
        return history.toString();
    }
    
    @Override
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("\n╔════════════════════════════════════════════════════════════╗\n");
        report.append("║         SAVINGS ACCOUNT SUMMARY REPORT                   ║\n");
        report.append("╚════════════════════════════════════════════════════════════╝\n");
        report.append("Account Number    : ").append(accountNumber).append("\n");
        report.append("Account Holder    : ").append(accountHolder).append("\n");
        report.append("Current Balance   : $").append(String.format("%.2f", balance)).append("\n");
        report.append("Interest Rate     : ").append(String.format("%.2f", interestRate)).append("%\n");
        report.append("Total Transactions: ").append(transactionHistory.size()).append("\n");
        report.append("════════════════════════════════════════════════════════════\n");
        return report.toString();
    }
    
    @Override
    public String exportData(String format) {
        switch (format.toUpperCase()) {
            case "CSV":
                return exportToCSV();
            case "JSON":
                return exportToJSON();
            case "XML":
                return exportToXML();
            default:
                return "Unsupported format: " + format;
        }
    }
    
    private String exportToCSV() {
        StringBuilder csv = new StringBuilder();
        csv.append("AccountNumber,AccountHolder,Balance,InterestRate,TransactionCount\n");
        csv.append(accountNumber).append(",")
           .append(accountHolder).append(",")
           .append(balance).append(",")
           .append(interestRate).append(",")
           .append(transactionHistory.size()).append("\n");
        return csv.toString();
    }
    
    private String exportToJSON() {
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        json.append("  \"accountNumber\": \"").append(accountNumber).append("\",\n");
        json.append("  \"accountHolder\": \"").append(accountHolder).append("\",\n");
        json.append("  \"balance\": ").append(balance).append(",\n");
        json.append("  \"interestRate\": ").append(interestRate).append(",\n");
        json.append("  \"transactionCount\": ").append(transactionHistory.size()).append("\n");
        json.append("}");
        return json.toString();
    }
    
    private String exportToXML() {
        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<SavingsAccount>\n");
        xml.append("  <accountNumber>").append(accountNumber).append("</accountNumber>\n");
        xml.append("  <accountHolder>").append(accountHolder).append("</accountHolder>\n");
        xml.append("  <balance>").append(balance).append("</balance>\n");
        xml.append("  <interestRate>").append(interestRate).append("</interestRate>\n");
        xml.append("  <transactionCount>").append(transactionHistory.size()).append("</transactionCount>\n");
        xml.append("</SavingsAccount>");
        return xml.toString();
    }
    
    private void addTransaction(String type, double amount, String status) {
        String timestamp = LocalDateTime.now().format(DATE_FORMATTER);
        String transaction = String.format("[%s] %s: $%.2f - %s (Balance: $%.2f)", 
                                          timestamp, type, amount, status, balance);
        transactionHistory.add(transaction);
    }
    
    public void applyInterest() {
        double interest = balance * (interestRate / 100);
        balance += interest;
        addTransaction("INTEREST", interest, "Monthly interest applied");
    }
    
    // Getters
    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolder() { return accountHolder; }
    public double getBalance() { return balance; }
    public double getInterestRate() { return interestRate; }
}
