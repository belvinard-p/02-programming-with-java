package defenssiveprogramming.level3.exercise5;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankingService {
    private static final Logger logger = Logger.getLogger(BankingService.class.getName());
    private static final int MAX_RETRIES = 3;

    public boolean safeWithdraw(RobustBankAccount account, double amount) {
        try {
            account.withdraw(amount);
            logger.log(Level.INFO, "Withdrawal successful: ${0}", amount);
            return true;
        } catch (InsufficientFundsException e) {
            logger.log(Level.WARNING, "Withdrawal failed: {0} (Code: {1})", 
                      new Object[]{e.getMessage(), e.getErrorCode()});
            return false;
        } catch (AccountClosedException e) {
            logger.log(Level.SEVERE, "Withdrawal failed: {0}", e.getMessage());
            return false;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unexpected error during withdrawal: {0}", e.getMessage());
            return false;
        }
    }

    public boolean safeTransfer(RobustBankAccount from, String to, double amount) {
        for (int retries = 0; retries < MAX_RETRIES; retries++) {
            try {
                from.transfer(to, amount);
                logger.log(Level.INFO, "Transfer successful: ${0} to {1}", new Object[]{amount, to});
                return true;
            } catch (NetworkException e) {
                if (!handleNetworkError(retries, e)) {
                    return false;
                }
            } catch (BankingException e) {
                return handleBankingException(e);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Unexpected error: {0}", e.getMessage());
                return false;
            }
        }
        return false;
    }

    private boolean handleNetworkError(int retries, NetworkException e) {
        logger.log(Level.WARNING, "Network error (attempt {0}/{1}): {2}", 
                  new Object[]{retries + 1, MAX_RETRIES, e.getMessage()});
        if (retries + 1 >= MAX_RETRIES) {
            logger.log(Level.SEVERE, "Transfer failed after {0} retries", MAX_RETRIES);
            return false;
        }
        return true;
    }

    private boolean handleBankingException(BankingException e) {
        Level level = e instanceof AccountClosedException ? Level.SEVERE : Level.WARNING;
        String message = e instanceof TransactionLimitExceededException 
            ? ((TransactionLimitExceededException) e).getViolationDetails() 
            : e.getMessage();
        logger.log(level, "Transfer failed: {0}", message);
        return false;
    }

    public String processBatchWithErrorHandling(RobustBankAccount[] accounts, double[] amounts) {
        ArrayList<String> errors = new ArrayList<>();
        int successCount = 0;

        for (int i = 0; i < accounts.length && i < amounts.length; i++) {
            if (processOperation(accounts[i], amounts[i], i + 1, errors)) {
                successCount++;
            }
        }

        return generateBatchReport(successCount, errors);
    }

    private boolean processOperation(RobustBankAccount account, double amount,
                                     int opNumber, ArrayList<String> errors) {
        try {
            account.withdraw(amount);
            logger.log(Level.INFO, "Batch operation {0}: Success", opNumber);
            return true;
        } catch (Exception e) {
            handleBatchError(e, opNumber, errors);
            return false;
        }
    }

    private void handleBatchError(Exception e, int opNumber, ArrayList<String> errors) {
        String error = String.format("Operation %d failed: %s", opNumber, e.getMessage());
        errors.add(error);
        Level level = e instanceof AccountClosedException || !(e instanceof BankingException) ? Level.SEVERE : Level.WARNING;
        logger.log(level, error);
    }

    private String generateBatchReport(int successCount, ArrayList<String> errors) {
        StringBuilder report = new StringBuilder();
        report.append(String.format("Batch processing complete: %d success, %d failures%n", 
                                   successCount, errors.size()));
        if (!errors.isEmpty()) {
            report.append("Errors:%n");
            for (String error : errors) {
                report.append(String.format("  - %s%n", error));
            }
        }
        return report.toString();
    }
}
