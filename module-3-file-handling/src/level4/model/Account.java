package level4.model;

import level4.exception.AccountClosedException;
import level4.exception.InsufficientFundsException;
import level4.exception.InvalidAmountException;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Account {
    private static final Logger LOGGER = Logger.getLogger(Account.class.getName());

    protected String accountId;
    protected String accountHolder;
    protected double balance;
    protected boolean isActive;
    protected String dateOpened;

    protected Account(String accountId, String accountHolder, double balance, String dateOpened) {
        this.accountId = accountId;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.isActive = true;
        this.dateOpened = dateOpened;
    }

    public abstract String getAccountType();

    public abstract double calculateMonthlyFees();

    public abstract boolean meetsMinimumBalance();

    public void deposit(double amount) throws InvalidAmountException, AccountClosedException {
        validateActive();
        if (amount <= 0) {
            throw InvalidAmountException.withDefaultCode("Deposit amount must be positive");
        }
        balance += amount;
        LOGGER.log(Level.INFO, "Deposited {0} to {1}", new Object[]{amount, accountId});
    }

    public void withdraw(double amount) throws InvalidAmountException, InsufficientFundsException, AccountClosedException {
        validateActive();
        if (amount <= 0) {
            throw InvalidAmountException.withDefaultCode("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new InsufficientFundsException(amount, balance);
        }
        balance -= amount;
        LOGGER.log(Level.INFO, "Withdrew {0} from {1}", new Object[]{amount, accountId});
    }

    public void applyMonthlyFees() {
        double fee = calculateMonthlyFees();
        balance -= fee;
        LOGGER.log(Level.INFO, "Fee {0} applied to {1}", new Object[]{fee, accountId});
    }

    public void close() {
        isActive = false;
        LOGGER.log(Level.INFO, "Account {0} closed", accountId);
    }

    protected void validateActive() throws AccountClosedException {
        if (!isActive) {
            throw new AccountClosedException(accountId);
        }
    }

    public String toFileFormat() {
        return accountId + "|" + accountHolder + "|" + balance + "|" + getAccountType() + "|" + isActive + "|" + dateOpened;
    }

    @Override
    public String toString() {
        return "[" + getAccountType() + "] " + accountId + " - " + accountHolder + " | Balance: " + balance;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getDateOpened() {
        return dateOpened;
    }
}
