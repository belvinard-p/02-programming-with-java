package advancedoop.level1.exercise1;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckingAccount extends BankAccount {
    private static final Logger logger = Logger.getLogger(CheckingAccount.class.getName());
    private double overdraftLimit;

    public CheckingAccount(String accountNumber, double balance, String accountHolder, double overdraftLimit) {
        super(accountNumber, balance, accountHolder);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public double calculateInterest() {
        return 0.0;
    }

    @Override
    public double applyMonthlyFees() {
        withdraw(10.0);
        logger.log(Level.INFO, "Monthly maintenance fee of $10 applied");
        return 10.0;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            logger.log(Level.WARNING, "Withdrawal amount must be positive");
            return false;
        }
        if (amount > getBalance() + overdraftLimit) {
            logger.log(Level.WARNING, "Exceeds overdraft limit");
            return false;
        }
        return super.withdraw(amount);
    }
}
