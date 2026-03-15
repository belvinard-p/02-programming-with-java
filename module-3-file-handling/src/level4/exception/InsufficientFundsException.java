package level4.exception;

public class InsufficientFundsException extends BankingException {
    private final double requiredAmount;
    private final double availableAmount;

    public InsufficientFundsException(double requiredAmount, double availableAmount) {
        super("Insufficient funds: required " + requiredAmount + ", available " + availableAmount, "ERR_002");
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
