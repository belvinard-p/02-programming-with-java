package advancedoop.level2.exercise2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BusinessAccount implements Depositable, Withdrawable, CheckWritable, WireTransferable {
    private static final Logger logger = Logger.getLogger(BusinessAccount.class.getName());
    private static final int INITIAL_CHECK_COUNT = 500;
    private static final double WIRE_TRANSFER_LIMIT = 100000.0;
    
    private String accountId;
    private double balance;
    private int remainingChecks;
    private int depositCount;
    private int withdrawalCount;
    private int checkCount;
    private int wireTransferCount;
    
    public BusinessAccount(String accountId, double initialBalance) {
        this.accountId = accountId;
        this.balance = initialBalance;
        this.remainingChecks = INITIAL_CHECK_COUNT;
        this.depositCount = 0;
        this.withdrawalCount = 0;
        this.checkCount = 0;
        this.wireTransferCount = 0;
    }
    
    @Override
    public boolean deposit(double amount) {
        if (amount <= 0) {
            logger.warning("Deposit amount must be positive");
            return false;
        }
        
        balance += amount;
        depositCount++;
        logger.log(Level.INFO, "Deposited ${0}. New balance: ${1}", new Object[]{amount, balance});
        return true;
    }
    
    @Override
    public String getDepositMethod() {
        return "cash, check, transfer, wire, mobile deposit, ACH";
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
        withdrawalCount++;
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
        checkCount++;
        logger.log(Level.INFO, "Check #{0} written to {1} for ${2}. New balance: ${3}", 
                  new Object[]{checkNumber, payee, amount, balance});
        return true;
    }
    
    @Override
    public int getRemainingChecks() {
        return remainingChecks;
    }
    
    @Override
    public boolean sendWire(String recipientBank, String recipientAccount, double amount) {
        if (amount <= 0) {
            logger.warning("Wire transfer amount must be positive");
            return false;
        }
        
        if (amount > WIRE_TRANSFER_LIMIT) {
            logger.log(Level.WARNING, "Wire transfer amount exceeds limit of ${0}", WIRE_TRANSFER_LIMIT);
            return false;
        }
        
        if (balance < amount) {
            logger.warning("Insufficient balance for wire transfer");
            return false;
        }
        
        balance -= amount;
        wireTransferCount++;
        logger.log(Level.INFO, "Wire transfer of ${0} sent to {1} (Account: {2}). New balance: ${3}", 
                  new Object[]{amount, recipientBank, recipientAccount, balance});
        return true;
    }
    
    @Override
    public double getWireTransferLimit() {
        return WIRE_TRANSFER_LIMIT;
    }
    
    public String getAccountId() {
        return accountId;
    }
    
    public void displayTransactionSummary() {
        logger.info("=== Transaction Summary ===");
        logger.log(Level.INFO, "Deposits: {0}", depositCount);
        logger.log(Level.INFO, "Withdrawals: {0}", withdrawalCount);
        logger.log(Level.INFO, "Checks Written: {0}", checkCount);
        logger.log(Level.INFO, "Wire Transfers: {0}", wireTransferCount);
        logger.log(Level.INFO, "Current Balance: ${0}", balance);
    }
}
