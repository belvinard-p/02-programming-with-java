package advancedoop.level2.exercise1;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SavingsAccount extends InterestBearingAccount{
    private static Logger logger = Logger.getLogger(SavingsAccount.class.getName());

    private double minimumBalance = 500.0;
    private int withdrawalCount = 0;
    private static final int MAX_FREE_WITHDRAWALS = 6;
    private static final double EXCESS_WITHDRAWAL_FEE = 5.0;

    protected SavingsAccount(String accountId, double balance, String dateOpened, double annualInterestRate, double minimumBalance) {
        super(accountId, balance, dateOpened, annualInterestRate);
        this.minimumBalance = minimumBalance;
    }

    @Override
    public double calculateInterest() {
        return (getBalance() * annualInterestRate / 100) / 12;
    }

    @Override
    public String getAccountType() {
        return "SAVINGS";
    }

    @Override
    public double calculateMinimumBalance() {
        return minimumBalance;
    }
    
    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            logger.warning("Withdrawal amount must be positive");
            return false;
        }
        
        double totalAmount = amount;
        withdrawalCount++;
        
        // Apply fee if exceeding free withdrawal limit
        if (withdrawalCount > MAX_FREE_WITHDRAWALS) {
            totalAmount += EXCESS_WITHDRAWAL_FEE;
            logger.log(Level.INFO, "Excess withdrawal fee of ${0} applied (withdrawal #{1})", 
                      new Object[]{EXCESS_WITHDRAWAL_FEE, withdrawalCount});
        }
        
        if (getBalance() - totalAmount < minimumBalance) {
            logger.log(Level.WARNING, "Cannot withdraw. Minimum balance of ${0} required", minimumBalance);
            return false;
        }
        
        setBalance(getBalance() - totalAmount);
        logger.log(Level.INFO, "Withdrew ${0}. New balance: ${1}", new Object[]{totalAmount, getBalance()});
        return true;
    }
    
    public int getWithdrawalCount() {
        return withdrawalCount;
    }
    
    public void resetMonthlyWithdrawals() {
        withdrawalCount = 0;
        logger.info("Monthly withdrawal counter reset");
    }
}
