package advancedoop.level1.exercise1;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SavingsAccount extends BankAccount {
    private static final Logger logger = Logger.getLogger(SavingsAccount.class.getName());
    private double interestRate;

    public SavingsAccount(String accountNumber, double balance, String accountHolder, double interestRate) {
        super(accountNumber, balance, accountHolder);
        this.interestRate = interestRate;
    }

    @Override
    public double calculateInterest() {
        return getBalance() * interestRate / 100;
    }

    @Override
    public double applyMonthlyFees() {
        if (getBalance() < 1000) {
            withdraw(5.0);
            logger.log(Level.INFO, "Monthly fee of $5 applied");
            return 5.0;
        }
        return 0.0;
    }
}
