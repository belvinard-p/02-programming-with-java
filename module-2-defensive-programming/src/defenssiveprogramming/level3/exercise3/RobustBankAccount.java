package defenssiveprogramming.level3.exercise3;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RobustBankAccount {
    private static final Logger logger = Logger.getLogger(RobustBankAccount.class.getName());
    private static final double TRANSACTION_LIMIT = 10000.0;
    
    private final String accountNumber;
    private double balance;
    private boolean isClosed;
    private String closureDate;

    public RobustBankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.isClosed = false;
        this.closureDate = null;
    }

    public void withdraw(double amount) throws InsufficientFundsException, AccountClosedException {
        if (isClosed) {
            throw new AccountClosedException(accountNumber, closureDate);
        }
        
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        
        if (balance < amount) {
            throw new InsufficientFundsException(
                "Insufficient funds for withdrawal", 
                "INSUFFICIENT_FUNDS", 
                amount, 
                balance
            );
        }
        
        balance -= amount;
        logger.log(Level.INFO, "Withdrew ${0}. New balance: ${1}", new Object[]{amount, balance});
    }

    public void transfer(String toAccount, double amount) throws BankingException, InsufficientFundsException {
        if (isClosed) {
            throw new AccountClosedException(accountNumber, closureDate);
        }
        
        if (toAccount == null || toAccount.trim().isEmpty()) {
            throw new InvalidAccountException(toAccount);
        }
        
        if (amount > TRANSACTION_LIMIT) {
            throw new TransactionLimitExceededException(amount, TRANSACTION_LIMIT);
        }
        
        if (balance < amount) {
            throw new InsufficientFundsException(
                "Insufficient funds for transfer", 
                "INSUFFICIENT_FUNDS", 
                amount, 
                balance
            );
        }
        
        balance -= amount;
        logger.log(Level.INFO, "Transferred ${0} to {1}. New balance: ${2}", 
                  new Object[]{amount, toAccount, balance});
    }

    public void processTransaction(String type, double amount) throws BankingException, InsufficientFundsException {
        if (isClosed) {
            throw new AccountClosedException(accountNumber, closureDate);
        }
        
        if (amount <= 0) {
            throw new IllegalArgumentException("Transaction amount must be positive");
        }
        
        switch (type.toUpperCase()) {
            case "DEPOSIT":
                deposit(amount);
                break;
            case "WITHDRAWAL":
                withdraw(amount);
                break;
            case "TRANSFER":
                throw new IllegalArgumentException("Transfer requires destination account");
            default:
                throw new IllegalArgumentException("Unknown transaction type: " + type);
        }
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
        logger.log(Level.INFO, "Deposited ${0}. New balance: ${1}", new Object[]{amount, balance});
    }

    public void closeAccount(String date) {
        this.isClosed = true;
        this.closureDate = date;
        logger.log(Level.INFO, "Account {0} closed on {1}", new Object[]{accountNumber, date});
    }

    public double getBalance() {
        return balance;
    }

    public boolean isClosed() {
        return isClosed;
    }
}
