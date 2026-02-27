package defenssiveprogramming.level3.exercise3;

public class TransactionLimitExceededException extends BankingException {
    private double transactionAmount;
    private double limitAmount;
    
    public TransactionLimitExceededException(double transactionAmount, double limitAmount) {
        super("Transaction limit exceeded. Amount: $" + transactionAmount + ", Limit: $" + limitAmount, 
              "LIMIT_EXCEEDED");
        this.transactionAmount = transactionAmount;
        this.limitAmount = limitAmount;
    }
    
    public double getExcessAmount() {
        return transactionAmount - limitAmount;
    }
    
    public String getViolationDetails() {
        return String.format("Transaction of $%.2f exceeds limit of $%.2f by $%.2f", 
                           transactionAmount, limitAmount, getExcessAmount());
    }
}
