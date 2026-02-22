package advancedoop.level2.exercise1;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class InvestmentAccount extends InterestBearingAccount implements Investable {
    private static final Logger logger = Logger.getLogger(InvestmentAccount.class.getName());

    private Map<String, Double> portfolio;
    private double totalInvested;
    
    public InvestmentAccount(String accountId, double balance, String dateOpened, double annualInterestRate) {
        super(accountId, balance, dateOpened, annualInterestRate);
        this.portfolio = new HashMap<>();
        this.totalInvested = 0.0;
    }
    
    @Override
    public String getAccountType() {
        return "INVESTMENT";
    }
    
    @Override
    public double calculateMinimumBalance() {
        return 1000.0; // Investment accounts require higher minimum balance
    }
    
    @Override
    public double calculateInterest() {
        return ((getBalance() + totalInvested) * annualInterestRate / 100) / 12;
    }
    
    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            logger.warning("Withdrawal amount must be positive");
            return false;
        }
        
        if (getBalance() < amount) {
            logger.warning("Insufficient cash balance for withdrawal");
            return false;
        }
        
        setBalance(getBalance() - amount);
        logger.log(Level.INFO, "Withdrew ${0}. New balance: ${1}", new Object[]{amount, getBalance()});
        return true;
    }
    
    // Investable interface implementation
    
    @Override
    public boolean invest(double amount, String instrumentType) {
        if (amount <= 0) {
            logger.warning("Investment amount must be positive");
            return false;
        }
        
        if (getBalance() < amount) {
            logger.log(Level.WARNING, "Insufficient balance. Available: ${0}, Requested: ${1}", 
                      new Object[]{getBalance(), amount});
            return false;
        }
        
        // Deduct from cash balance
        setBalance(getBalance() - amount);
        portfolio.put(instrumentType, portfolio.getOrDefault(instrumentType, 0.0) + amount);
        totalInvested += amount;
        
        logger.log(Level.INFO, "Invested ${0} in {1}. Total portfolio value: ${2}", 
                  new Object[]{amount, instrumentType, totalInvested});
        return true;
    }
    
    @Override
    public boolean liquidateInvestment(double amount) {
        if (amount <= 0) {
            logger.warning("Liquidation amount must be positive");
            return false;
        }
        
        if (totalInvested < amount) {
            logger.log(Level.WARNING, "Insufficient investments. Total invested: ${0}, Requested: ${1}", 
                      new Object[]{totalInvested, amount});
            return false;
        }

        double remainingToLiquidate = amount;
        for (Map.Entry<String, Double> entry : portfolio.entrySet()) {
            if (remainingToLiquidate <= 0) break;
            
            String instrument = entry.getKey();
            double currentValue = entry.getValue();
            double toLiquidate = Math.min(currentValue, remainingToLiquidate);
            
            portfolio.put(instrument, currentValue - toLiquidate);
            remainingToLiquidate -= toLiquidate;
        }

        totalInvested -= amount;
        setBalance(getBalance() + amount);
        
        logger.log(Level.INFO, "Liquidated ${0}. Cash balance: ${1}, Remaining investments: ${2}", 
                  new Object[]{amount, getBalance(), totalInvested});
        return true;
    }
    
    @Override
    public double getInvestmentValue() {
        return totalInvested;
    }
    
    @Override
    public String getPortfolioSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Investment Portfolio Summary:\n");
        summary.append("  Cash Balance: $").append(String.format("%.2f", getBalance())).append("\n");
        summary.append("  Investments:\n");
        
        if (portfolio.isEmpty()) {
            summary.append("    No investments\n");
        } else {
            for (Map.Entry<String, Double> entry : portfolio.entrySet()) {
                if (entry.getValue() > 0) {
                    summary.append("    - ").append(entry.getKey())
                           .append(": $").append(String.format("%.2f", entry.getValue())).append("\n");
                }
            }
        }
        
        summary.append("  Total Invested: $").append(String.format("%.2f", totalInvested));
        return summary.toString();
    }
}
