package advancedoop.level1.exercise1;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
* Best Practice: Abstract class constructors should be
* protected to clearly indicate they're only for inheritance!
*/
public abstract class BankAccount {
    private static final Logger logger = Logger.getLogger(BankAccount.class.getName());
    private String accountNumber;
    private double balance;
    private String accountHolder;

    protected BankAccount(String accountNumber, double balance, String accountHolder) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountHolder = accountHolder;
    }

    public abstract double calculateInterest();
    public abstract double applyMonthlyFees();

    public void deposit (double amount) {
        if (amount <= 0) {
            logger.log(Level.WARNING, "Deposit amount must be positive");
            return;
        }
        balance += amount;
        logger.log(Level.INFO, "{0} deposited. New balance: {1}", new Object[]{amount, balance});
    }

    public boolean withdraw (double amount) {
        if (amount <= 0) {
            logger.log(Level.WARNING, "Withdrawal amount must be positive");
            return false;
        }
        if (amount > balance) {
            logger.log(Level.WARNING, "Insufficient funds");
            return false;
        }
        balance -= amount;
        logger.log(Level.INFO, "{0} withdrawn. New balance: {1}", new Object[]{amount, balance});
        return true;
    }

    public double getBalance() {
        return balance;
    }

    public void displayAccountInfo() {
        logger.log(Level.INFO, "Account Number: {0}, Account Holder: {1}, Balance: {2}",
                new Object[]{accountNumber, accountHolder, balance});

    }
}
