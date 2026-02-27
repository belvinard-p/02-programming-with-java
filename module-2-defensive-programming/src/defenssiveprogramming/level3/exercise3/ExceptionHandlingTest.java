package defenssiveprogramming.level3.exercise3;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionHandlingTest {
    private static final Logger logger = Logger.getLogger(ExceptionHandlingTest.class.getName());
    private static final String SUCCESS = "\u2705 Success";
    private static final String FAILED = "\u274c Failed";

    public static void main(String[] args) {
        logger.info("=== Exception Handling Test ===");
        testInsufficientFunds();
        testAccountClosed();
        testInvalidAccount();
        testTransactionLimit();
        testSuccessfulOperations();
        testProcessTransaction();
        testNetworkException();
    }
    
    private static void testInsufficientFunds() {
        logger.info("\n--- Test 1: InsufficientFundsException ---");
        RobustBankAccount account = new RobustBankAccount("ACC001", 500.0);
        try {
            account.withdraw(1000.0);
            logger.log(Level.WARNING, "{0} Should have thrown InsufficientFundsException", FAILED);
        } catch (InsufficientFundsException e) {
            logger.log(Level.INFO, "{0} Caught: {1}", new Object[]{SUCCESS, e.getMessage()});
            logger.log(Level.INFO, "Error Code: {0}", e.getErrorCode());
        } catch (Exception e) {
            logger.log(Level.WARNING, "{0} Wrong exception: {1}", new Object[]{FAILED, e.getClass().getName()});
        }
    }
    
    private static void testAccountClosed() {
        logger.info("\n--- Test 2: AccountClosedException ---");
        RobustBankAccount account = new RobustBankAccount("ACC002", 1000.0);
        account.closeAccount("2024-01-15");
        try {
            account.withdraw(100.0);
            logger.log(Level.WARNING, "{0} Should have thrown AccountClosedException", FAILED);
        } catch (AccountClosedException e) {
            logger.log(Level.INFO, "{0} Caught: {1}", new Object[]{SUCCESS, e.getMessage()});
        } catch (Exception e) {
            logger.log(Level.WARNING, "{0} Wrong exception: {1}", new Object[]{FAILED, e.getClass().getName()});
        }
    }
    
    private static void testInvalidAccount() {
        logger.info("\n--- Test 3: InvalidAccountException ---");
        RobustBankAccount account = new RobustBankAccount("ACC003", 1000.0);
        try {
            account.transfer("", 100.0);
            logger.log(Level.WARNING, "{0} Should have thrown InvalidAccountException", FAILED);
        } catch (InvalidAccountException e) {
            logger.log(Level.INFO, "{0} Caught: {1}", new Object[]{SUCCESS, e.getMessage()});
        } catch (Exception e) {
            logger.log(Level.WARNING, "{0} Wrong exception: {1}", new Object[]{FAILED, e.getClass().getName()});
        }
    }
    
    private static void testTransactionLimit() {
        logger.info("\n--- Test 4: TransactionLimitExceededException ---");
        RobustBankAccount account = new RobustBankAccount("ACC004", 50000.0);
        try {
            account.transfer("ACC999", 15000.0);
            logger.log(Level.WARNING, "{0} Should have thrown TransactionLimitExceededException", FAILED);
        } catch (TransactionLimitExceededException e) {
            logger.log(Level.INFO, "{0} Caught: {1}", new Object[]{SUCCESS, e.getMessage()});
            logger.log(Level.INFO, "Violation: {0}", e.getViolationDetails());
        } catch (Exception e) {
            logger.log(Level.WARNING, "{0} Wrong exception: {1}", new Object[]{FAILED, e.getClass().getName()});
        }
    }
    
    private static void testSuccessfulOperations() {
        logger.info("\n--- Test 5: Successful Operations ---");
        RobustBankAccount account = new RobustBankAccount("ACC005", 1000.0);
        try {
            account.deposit(500.0);
            account.withdraw(200.0);
            account.transfer("ACC999", 300.0);
            logger.log(Level.INFO, "{0} All operations successful. Final balance: {1}", 
                      new Object[]{SUCCESS, account.getBalance()});
        } catch (Exception e) {
            logger.log(Level.WARNING, "{0} Unexpected exception: {1}", new Object[]{FAILED, e.getMessage()});
        }
    }
    
    private static void testProcessTransaction() {
        logger.info("\n--- Test 6: ProcessTransaction Method ---");
        RobustBankAccount account = new RobustBankAccount("ACC006", 1000.0);
        try {
            account.processTransaction("DEPOSIT", 500.0);
            account.processTransaction("WITHDRAWAL", 200.0);
            logger.log(Level.INFO, "{0} ProcessTransaction works. Balance: {1}", 
                      new Object[]{SUCCESS, account.getBalance()});
        } catch (Exception e) {
            logger.log(Level.WARNING, "{0} Exception: {1}", new Object[]{FAILED, e.getMessage()});
        }
        
        try {
            account.processTransaction("WITHDRAWAL", 5000.0);
            logger.log(Level.WARNING, "{0} Should have thrown exception", FAILED);
        } catch (InsufficientFundsException e) {
            logger.log(Level.INFO, "{0} Correctly caught insufficient funds", SUCCESS);
        } catch (Exception e) {
            logger.log(Level.WARNING, "{0} Wrong exception: {1}", new Object[]{FAILED, e.getClass().getName()});
        }
    }
    
    private static void testNetworkException() {
        logger.info("\n--- Test 7: NetworkException ---");
        try {
            throw new NetworkException("Connection timeout", "NETWORK_ERROR", true);
        } catch (NetworkException e) {
            logger.log(Level.INFO, "{0} Caught: {1}", new Object[]{SUCCESS, e.getMessage()});
            logger.log(Level.INFO, "Retry advice: {0}", e.getRetryAdvice());
        }
    }
}
