package advancedoop.level2.exercise4;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SavingsProduct extends BankingProduct {
    private static final Logger logger = Logger.getLogger(SavingsProduct.class.getName());
    private static final int MAX_WITHDRAWALS_PER_MONTH = 6;
    private static final double MINIMUM_BALANCE = 500.0;
    
    private double balance;
    private int withdrawalCount;

    public SavingsProduct(String productId, String productName, String customerName, double initialBalance) {
        super(productId, productName, customerName);
        this.balance = initialBalance;
        this.withdrawalCount = 0;
    }

    @Override
    double processTransaction(String transactionType, double amount) {
        if ("DEPOSIT".equalsIgnoreCase(transactionType)) {
            if (amount <= 0) {
                logger.warning("Deposit amount must be positive");
                return balance;
            }
            balance += amount;
            logger.log(Level.INFO, "Deposited ${0}. New balance: ${1}", new Object[]{amount, balance});
            return balance;
        }
        
        if ("WITHDRAWAL".equalsIgnoreCase(transactionType)) {
            if (amount <= 0) {
                logger.warning("Withdrawal amount must be positive");
                return balance;
            }
            
            if (withdrawalCount >= MAX_WITHDRAWALS_PER_MONTH) {
                logger.log(Level.WARNING, "Withdrawal limit reached. Maximum {0} withdrawals per month", MAX_WITHDRAWALS_PER_MONTH);
                return balance;
            }
            
            if (balance - amount < MINIMUM_BALANCE) {
                logger.log(Level.WARNING, "Cannot withdraw. Minimum balance of ${0} required", MINIMUM_BALANCE);
                return balance;
            }
            
            balance -= amount;
            withdrawalCount++;
            logger.log(Level.INFO, "Withdrew ${0}. New balance: ${1}. Withdrawals: {2}", new Object[]{amount, balance, withdrawalCount});
            return balance;
        }
        
        logger.log(Level.WARNING, "Unknown transaction type: {0}", transactionType);
        return balance;
    }

    @Override
    String generateStatement() {
        StringBuilder statement = new StringBuilder();
        statement.append("\n=== SAVINGS ACCOUNT STATEMENT ===");
        statement.append("\nProduct ID: ").append(super.toString());
        statement.append("\nCurrent Balance: $").append(String.format("%.2f", balance));
        statement.append("\nMinimum Balance: $").append(String.format("%.2f", MINIMUM_BALANCE));
        statement.append("\nWithdrawals This Month: ").append(withdrawalCount).append("/").append(MAX_WITHDRAWALS_PER_MONTH);
        statement.append("\n=================================");
        return statement.toString();
    }
    
    public void resetMonthlyWithdrawals() {
        withdrawalCount = 0;
        logger.info("Monthly withdrawal count reset");
    }
}
