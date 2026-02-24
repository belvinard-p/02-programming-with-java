package advancedoop.level2.exercise2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class StandardSavingsAccount implements Depositable, Withdrawable {
    private static final Logger logger = Logger.getLogger(StandardSavingsAccount.class.getName());
    private static final int MAX_WITHDRAWALS_PER_MONTH = 6;
    
    private String accountId;
    private double balance;
    private int withdrawalCount;
    
    public StandardSavingsAccount(String accountId, double initialBalance) {
        this.accountId = accountId;
        this.balance = initialBalance;
        this.withdrawalCount = 0;
    }
    
    @Override
    public boolean deposit(double amount) {
        if (amount <= 0) {
            logger.warning("Deposit amount must be positive");
            return false;
        }
        
        balance += amount;
        logger.log(Level.INFO, "Deposited ${0}. New balance: ${1}", new Object[]{amount, balance});
        return true;
    }
    
    @Override
    public String getDepositMethod() {
        return "cash, check, transfer";
    }
    
    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            logger.warning("Withdrawal amount must be positive");
            return false;
        }
        
        if (withdrawalCount >= MAX_WITHDRAWALS_PER_MONTH) {
            logger.log(Level.WARNING,
                    "Withdrawal limit reached. Maximum {0} withdrawals per month (Federal Regulation D)",
                    MAX_WITHDRAWALS_PER_MONTH);
            return false;
        }
        
        if (balance < amount) {
            logger.warning("Insufficient balance");
            return false;
        }
        
        balance -= amount;
        withdrawalCount++;
        logger.log(Level.INFO, "Withdrew ${0}. New balance: ${1}. Withdrawals this month: {2}",
                new Object[]{amount, balance, withdrawalCount});
        return true;
    }
    
    @Override
    public double getAvailableBalance() {
        return balance;
    }
    
    public String getAccountId() {
        return accountId;
    }
    
    public int getWithdrawalCount() {
        return withdrawalCount;
    }
    
    public void resetMonthlyWithdrawals() {
        withdrawalCount = 0;
        logger.info("Monthly withdrawal count reset");
    }
}
