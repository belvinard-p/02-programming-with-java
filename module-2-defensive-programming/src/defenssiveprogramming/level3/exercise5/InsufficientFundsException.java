package defenssiveprogramming.level3.exercise5;

public class InsufficientFundsException extends BankingException {
    private final double requiredAmount;
    private final double availableAmount;

    public InsufficientFundsException(String message, String errorCode, double requiredAmount, double availableAmount) {
        super(message, errorCode);
        this.requiredAmount = requiredAmount;
        this.availableAmount = availableAmount;
    }
    
    public double getRequiredAmount() {
        return requiredAmount;
    }
    
    public double getAvailableAmount() {
        return availableAmount;
    }
}
