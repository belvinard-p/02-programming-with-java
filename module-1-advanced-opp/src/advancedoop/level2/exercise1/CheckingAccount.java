package advancedoop.level2.exercise1;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckingAccount extends Account {
    private static final Logger logger = Logger.getLogger(CheckingAccount.class.getName());
    private int transactionCount;
    private final int freeTransactionsLimit = 10;
    private static final double TRANSACTION_FEE = 2.0;

    public CheckingAccount(String accountId, double balance, String dateOpened) {
        super(accountId, balance, dateOpened);
        this.transactionCount = 0;
    }

    @Override
    public String getAccountType() {
        return "CHECKING";
    }

    @Override
    public double calculateMinimumBalance() {
        return 100.0;
    }

    @Override
    public boolean withdraw(double amount) {
        transactionCount++;
        
        double totalAmount = amount;
        if (transactionCount > freeTransactionsLimit) {
            totalAmount += TRANSACTION_FEE;
            logger.log(Level.INFO, "Transaction fee of ${0} applied", TRANSACTION_FEE);
        }
        
        if (amount <= 0) {
            logger.log(Level.WARNING, "Withdrawal amount must be positive");
            return false;
        }
        
        if (getBalance() < totalAmount) {
            logger.log(Level.WARNING, "Insufficient funds");
            return false;
        }
        
        setBalance(getBalance() - totalAmount);
        logger.log(Level.INFO, "Withdrawn: ${0}. New balance: ${1}", new Object[]{totalAmount, getBalance()});
        return true;
    }

}
