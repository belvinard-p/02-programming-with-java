package defenssiveprogramming.level3.exercise5;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionHandlingTest {
    private static final Logger logger = Logger.getLogger(ExceptionHandlingTest.class.getName());
    private static final String SUCCESS = "\u2705 Success";
    private static final String FAILED = "\u274c Failed";
    private static final String CLOSURE_DATE = "2026-01-15";
    private static final String TARGET_ACCOUNT = "ACC999";
    private static final String LOG_CAUGHT = "{0} Caught: {1}";
    private static final String LOG_WRONG_EXCEPTION = "{0} Wrong exception: {1}";

    public static void main(String[] args) {
        logger.info("=== Exception Handling Test ===");
        testInsufficientFunds();
        testAccountClosed();
        testInvalidAccount();
        testTransactionLimit();
        testSuccessfulOperations();
        testProcessTransaction();
        testNetworkException();
        testSafeWithdraw();
        testSafeTransfer();
        testProcessBatch();
    }
    
    private static void testInsufficientFunds() {
        logger.info("\n--- Test 1: InsufficientFundsException ---");
        RobustBankAccount account = new RobustBankAccount("ACC001", 500.0);
        try {
            account.withdraw(1000.0);
            logger.log(Level.WARNING, "{0} Should have thrown InsufficientFundsException", FAILED);
        } catch (InsufficientFundsException e) {
            logger.log(Level.INFO, LOG_CAUGHT, new Object[]{SUCCESS, e.getMessage()});
            logger.log(Level.INFO, "Error Code: {0}", e.getErrorCode());
        } catch (Exception e) {
            logger.log(Level.WARNING, LOG_WRONG_EXCEPTION, new Object[]{FAILED, e.getClass().getName()});
        }
    }
    
    private static void testAccountClosed() {
        logger.info("\n--- Test 2: AccountClosedException ---");
        RobustBankAccount account = new RobustBankAccount("ACC002", 1000.0);
        account.closeAccount(CLOSURE_DATE);
        try {
            account.withdraw(100.0);
            logger.log(Level.WARNING, "{0} Should have thrown AccountClosedException", FAILED);
        } catch (AccountClosedException e) {
            logger.log(Level.INFO, LOG_CAUGHT, new Object[]{SUCCESS, e.getMessage()});
        } catch (Exception e) {
            logger.log(Level.WARNING, LOG_WRONG_EXCEPTION, new Object[]{FAILED, e.getClass().getName()});
        }
    }
    
    private static void testInvalidAccount() {
        logger.info("\n--- Test 3: InvalidAccountException ---");
        RobustBankAccount account = new RobustBankAccount("ACC003", 1000.0);
        try {
            account.transfer("", 100.0);
            logger.log(Level.WARNING, "{0} Should have thrown InvalidAccountException", FAILED);
        } catch (InvalidAccountException e) {
            logger.log(Level.INFO, LOG_CAUGHT, new Object[]{SUCCESS, e.getMessage()});
        } catch (Exception e) {
            logger.log(Level.WARNING, LOG_WRONG_EXCEPTION, new Object[]{FAILED, e.getClass().getName()});
        }
    }
    
    private static void testTransactionLimit() {
        logger.info("\n--- Test 4: TransactionLimitExceededException ---");
        RobustBankAccount account = new RobustBankAccount("ACC004", 50000.0);
        try {
            account.transfer(TARGET_ACCOUNT, 15000.0);
            logger.log(Level.WARNING, "{0} Should have thrown TransactionLimitExceededException", FAILED);
        } catch (TransactionLimitExceededException e) {
            logger.log(Level.INFO, LOG_CAUGHT, new Object[]{SUCCESS, e.getMessage()});
            logger.log(Level.INFO, "Violation: {0}", e.getViolationDetails());
        } catch (Exception e) {
            logger.log(Level.WARNING, LOG_WRONG_EXCEPTION, new Object[]{FAILED, e.getClass().getName()});
        }
    }
    
    private static void testSuccessfulOperations() {
        logger.info("\n--- Test 5: Successful Operations ---");
        RobustBankAccount account = new RobustBankAccount("ACC005", 1000.0);
        try {
            account.deposit(500.0);
            account.withdraw(200.0);
            account.transfer(TARGET_ACCOUNT, 300.0);
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
            logger.log(Level.WARNING, LOG_WRONG_EXCEPTION, new Object[]{FAILED, e.getClass().getName()});
        }
    }
    
    private static void testNetworkException() {
        logger.info("\n--- Test 7: NetworkException ---");
        try {
            throw new NetworkException("Connection timeout", "NETWORK_ERROR", true);
        } catch (NetworkException e) {
            logger.log(Level.INFO, LOG_CAUGHT, new Object[]{SUCCESS, e.getMessage()});
            logger.log(Level.INFO, "Retry advice: {0}", e.getRetryAdvice());
        }
    }
    
    private static void testSafeWithdraw() {
        logger.info("\n--- Test 8: BankingService.safeWithdraw ---");
        BankingService service = new BankingService();
        
        RobustBankAccount account1 = new RobustBankAccount("ACC007", 1000.0);
        boolean result1 = service.safeWithdraw(account1, 500.0);
        logger.log(Level.INFO, "{0} Successful withdrawal returned: {1}", 
                  new Object[]{result1 ? SUCCESS : FAILED, result1});
        
        RobustBankAccount account2 = new RobustBankAccount("ACC008", 500.0);
        boolean result2 = service.safeWithdraw(account2, 1000.0);
        logger.log(Level.INFO, "{0} Insufficient funds returned: {1}", 
                  new Object[]{!result2 ? SUCCESS : FAILED, result2});
        
        RobustBankAccount account3 = new RobustBankAccount("ACC009", 1000.0);
        account3.closeAccount(CLOSURE_DATE);
        boolean result3 = service.safeWithdraw(account3, 100.0);
        logger.log(Level.INFO, "{0} Closed account returned: {1}", 
                  new Object[]{!result3 ? SUCCESS : FAILED, result3});
    }
    
    private static void testSafeTransfer() {
        logger.info("\n--- Test 9: BankingService.safeTransfer ---");
        BankingService service = new BankingService();
        
        RobustBankAccount account1 = new RobustBankAccount("ACC010", 5000.0);
        boolean result1 = service.safeTransfer(account1, TARGET_ACCOUNT, 1000.0);
        logger.log(Level.INFO, "{0} Successful transfer: {1}", 
                  new Object[]{result1 ? SUCCESS : FAILED, result1});
        
        RobustBankAccount account2 = new RobustBankAccount("ACC011", 500.0);
        boolean result2 = service.safeTransfer(account2, TARGET_ACCOUNT, 1000.0);
        logger.log(Level.INFO, "{0} Insufficient funds transfer: {1}", 
                  new Object[]{!result2 ? SUCCESS : FAILED, result2});
        
        RobustBankAccount account3 = new RobustBankAccount("ACC012", 50000.0);
        boolean result3 = service.safeTransfer(account3, TARGET_ACCOUNT, 15000.0);
        logger.log(Level.INFO, "{0} Limit exceeded transfer: {1}", 
                  new Object[]{!result3 ? SUCCESS : FAILED, result3});
    }
    
    private static void testProcessBatch() {
        logger.info("\n--- Test 10: BankingService.processBatchWithErrorHandling ---");
        BankingService service = new BankingService();
        
        RobustBankAccount[] accounts = {
            new RobustBankAccount("ACC013", 1000.0),
            new RobustBankAccount("ACC014", 500.0),
            new RobustBankAccount("ACC015", 2000.0),
            new RobustBankAccount("ACC016", 100.0),
            new RobustBankAccount("ACC017", 1000.0),
            new RobustBankAccount("ACC018", 50000.0),
            new RobustBankAccount("ACC019", 1000.0)
        };
        accounts[5].closeAccount(CLOSURE_DATE);
        
        double[] amounts = {200.0, 1000.0, 500.0, 50.0};
        
        String report = service.processBatchWithErrorHandling(accounts, amounts);
        logger.info(report);
        logger.log(Level.INFO, "{0} Batch processing completed", SUCCESS);
    }
}
