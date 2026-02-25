package advancedoop.level2.exercise4;

import java.util.logging.Level;
import java.util.logging.Logger;

public class InvestmentProduct extends BankingProduct {
    private static final Logger logger = Logger.getLogger(InvestmentProduct.class.getName());
    
    private double cashBalance;
    private double portfolioValue;
    private int sharesOwned;

    public InvestmentProduct(String productId, String productName, String customerName, double initialCash) {
        super(productId, productName, customerName);
        this.cashBalance = initialCash;
        this.portfolioValue = 0.0;
        this.sharesOwned = 0;
    }

    @Override
    double processTransaction(String transactionType, double amount) {
        if ("BUY".equalsIgnoreCase(transactionType)) {
            if (amount <= 0) {
                logger.warning("Buy amount must be positive");
                return cashBalance;
            }
            
            if (cashBalance < amount) {
                logger.warning("Insufficient cash balance for purchase");
                return cashBalance;
            }
            
            cashBalance -= amount;
            portfolioValue += amount;
            sharesOwned++;
            logger.log(Level.INFO, "Bought investment for ${0}. Cash: ${1}, Portfolio: ${2}", new Object[]{amount, cashBalance, portfolioValue});
            return cashBalance;
        }
        
        if ("SELL".equalsIgnoreCase(transactionType)) {
            if (amount <= 0) {
                logger.warning("Sell amount must be positive");
                return cashBalance;
            }
            
            if (portfolioValue < amount) {
                logger.log(Level.WARNING, "Insufficient portfolio value. Portfolio: ${0}", portfolioValue);
                return cashBalance;
            }
            
            cashBalance += amount;
            portfolioValue -= amount;
            sharesOwned--;
            logger.log(Level.INFO, "Sold investment for ${0}. Cash: ${1}, Portfolio: ${2}", new Object[]{amount, cashBalance, portfolioValue});
            return cashBalance;
        }
        
        logger.log(Level.WARNING, "Unknown transaction type: {0}", transactionType);
        return cashBalance;
    }

    @Override
    String generateStatement() {
        double totalValue = cashBalance + portfolioValue;
        
        StringBuilder statement = new StringBuilder();
        statement.append("\n=== INVESTMENT STATEMENT ===");
        statement.append("\nProduct ID: ").append(super.toString());
        statement.append("\nCash Balance: $").append(String.format("%.2f", cashBalance));
        statement.append("\nPortfolio Value: $").append(String.format("%.2f", portfolioValue));
        statement.append("\nShares Owned: ").append(sharesOwned);
        statement.append("\nTotal Account Value: $").append(String.format("%.2f", totalValue));
        statement.append("\n============================");
        return statement.toString();
    }
}
