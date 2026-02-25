package advancedoop.level2.exercise4;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoanProduct extends BankingProduct {
    private static final Logger logger = Logger.getLogger(LoanProduct.class.getName());
    
    private double loanAmount;
    private double remainingBalance;
    private double interestRate;
    private int termMonths;

    public LoanProduct(String productId, String productName, String customerName, double loanAmount, double interestRate, int termMonths) {
        super(productId, productName, customerName);
        this.loanAmount = loanAmount;
        this.remainingBalance = loanAmount;
        this.interestRate = interestRate;
        this.termMonths = termMonths;
    }

    @Override
    double processTransaction(String transactionType, double amount) {
        if ("PAYMENT".equalsIgnoreCase(transactionType)) {
            if (amount <= 0) {
                logger.warning("Payment amount must be positive");
                return remainingBalance;
            }
            
            if (amount > remainingBalance) {
                logger.log(Level.WARNING, "Payment exceeds remaining balance. Balance: ${0}", remainingBalance);
                return remainingBalance;
            }
            
            remainingBalance -= amount;
            logger.log(Level.INFO, "Payment of ${0} received. Remaining balance: ${1}", new Object[]{amount, remainingBalance});
            return remainingBalance;
        }
        
        if ("ADVANCE".equalsIgnoreCase(transactionType)) {
            remainingBalance += amount;
            logger.log(Level.INFO, "Loan advance of ${0}. New balance: ${1}", new Object[]{amount, remainingBalance});
            return remainingBalance;
        }
        
        logger.log(Level.WARNING, "Unknown transaction type: {0}", transactionType);
        return remainingBalance;
    }

    @Override
    String generateStatement() {
        double monthlyPayment = (loanAmount * (interestRate / 100 / 12)) / (1 - Math.pow(1 + (interestRate / 100 / 12), -termMonths));
        
        StringBuilder statement = new StringBuilder();
        statement.append("\n=== LOAN STATEMENT ===");
        statement.append("\nProduct ID: ").append(super.toString());
        statement.append("\nOriginal Loan Amount: $").append(String.format("%.2f", loanAmount));
        statement.append("\nRemaining Balance: $").append(String.format("%.2f", remainingBalance));
        statement.append("\nInterest Rate: ").append(String.format("%.2f", interestRate)).append("%");
        statement.append("\nTerm: ").append(termMonths).append(" months");
        statement.append("\nMonthly Payment: $").append(String.format("%.2f", monthlyPayment));
        statement.append("\n======================");
        return statement.toString();
    }
}
