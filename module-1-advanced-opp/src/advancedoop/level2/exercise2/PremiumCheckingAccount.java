package advancedoop.level2.exercise2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PremiumCheckingAccount implements Depositable, Withdrawable, CheckWritable {
    private static final Logger logger = Logger.getLogger(PremiumCheckingAccount.class.getName());
    private static final int INITIAL_CHECK_COUNT = 100;
    
    private String accountId;
    private double balance;
    private int remainingChecks;
    
    public PremiumCheckingAccount(String accountId, double initialBalance) {
        this.accountId = accountId;
        this.balance = initialBalance;
        this.remainingChecks = INITIAL_CHECK_COUNT;
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
        return "cash, check, transfer, mobile deposit";
    }
    
    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            logger.warning("Withdrawal amount must be positive");
            return false;
        }
        
        if (balance < amount) {
            logger.warning("Insufficient balance");
            return false;
        }
        
        balance -= amount;
        logger.log(Level.INFO, "Withdrew ${0}. New balance: ${1}", new Object[]{amount, balance});
        return true;
    }
    
    @Override
    public double getAvailableBalance() {
        return balance;
    }
    
    @Override
    public boolean writeCheck(String payee, double amount, String checkNumber) {
        if (amount <= 0) {
            logger.warning("Check amount must be positive");
            return false;
        }
        
        if (remainingChecks <= 0) {
            logger.warning("No checks remaining. Please order new checks");
            return false;
        }
        
        if (balance < amount) {
            logger.warning("Insufficient balance to write check");
            return false;
        }
        
        balance -= amount;
        remainingChecks--;
        logger.log(Level.INFO, "Check #{0} written to {1} for ${2}. New balance: ${3}", 
                  new Object[]{checkNumber, payee, amount, balance});
        return true;
    }
    
    @Override
    public int getRemainingChecks() {
        return remainingChecks;
    }

}
