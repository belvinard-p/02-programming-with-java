package advancedoop.level2.exercise4;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CreditCardProduct extends BankingProduct {
    private static final Logger logger = Logger.getLogger(CreditCardProduct.class.getName());
    
    private double creditLimit;
    private double currentBalance;
    private int transactionCount;

    public CreditCardProduct(String productId, String productName, String customerName, double creditLimit) {
        super(productId, productName, customerName);
        this.creditLimit = creditLimit;
        this.currentBalance = 0.0;
        this.transactionCount = 0;
    }

    @Override
    double processTransaction(String transactionType, double amount) {
        if ("PURCHASE".equalsIgnoreCase(transactionType)) {
            if (amount <= 0) {
                logger.warning("Purchase amount must be positive");
                return currentBalance;
            }
            
            if (currentBalance + amount > creditLimit) {
                logger.log(Level.WARNING, "Purchase declined. Would exceed credit limit of ${0}", creditLimit);
                return currentBalance;
            }
            
            currentBalance += amount;
            transactionCount++;
            logger.log(Level.INFO, "Purchase of ${0} approved. Current balance: ${1}", new Object[]{amount, currentBalance});
            return currentBalance;
        }
        
        if ("PAYMENT".equalsIgnoreCase(transactionType)) {
            if (amount <= 0) {
                logger.warning("Payment amount must be positive");
                return currentBalance;
            }
            
            if (amount > currentBalance) {
                logger.log(Level.WARNING, "Payment exceeds balance. Balance: ${0}", currentBalance);
                return currentBalance;
            }
            
            currentBalance -= amount;
            logger.log(Level.INFO, "Payment of ${0} received. Current balance: ${1}", new Object[]{amount, currentBalance});
            return currentBalance;
        }
        
        logger.log(Level.WARNING, "Unknown transaction type: {0}", transactionType);
        return currentBalance;
    }

    @Override
    String generateStatement() {
        double availableCredit = creditLimit - currentBalance;
        
        StringBuilder statement = new StringBuilder();
        statement.append("\n=== CREDIT CARD STATEMENT ===");
        statement.append("\nProduct ID: ").append(super.toString());
        statement.append("\nCredit Limit: $").append(String.format("%.2f", creditLimit));
        statement.append("\nCurrent Balance: $").append(String.format("%.2f", currentBalance));
        statement.append("\nAvailable Credit: $").append(String.format("%.2f", availableCredit));
        statement.append("\nTransactions This Period: ").append(transactionCount);
        statement.append("\n=============================");
        return statement.toString();
    }
}
