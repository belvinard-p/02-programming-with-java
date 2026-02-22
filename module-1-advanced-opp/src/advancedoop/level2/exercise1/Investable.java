package advancedoop.level2.exercise1;

/**
 * Interface for investment operations
 * Demonstrates: Interface segregation, contract for investment behavior
 */
public interface Investable {
    /**
     * Invest funds in a specific instrument
     * @param amount Amount to invest
     * @param instrumentType Type of investment (stocks, bonds, etc.)
     * @return true if investment successful
     */
    boolean invest(double amount, String instrumentType);
    
    /**
     * Liquidate investments
     * @param amount Amount to liquidate
     * @return true if liquidation successful
     */
    boolean liquidateInvestment(double amount);
    
    /**
     * Get current investment portfolio value
     * @return Total value of investments
     */
    double getInvestmentValue();
    
    /**
     * Get investment portfolio summary
     * @return String representation of portfolio
     */
    String getPortfolioSummary();
}
