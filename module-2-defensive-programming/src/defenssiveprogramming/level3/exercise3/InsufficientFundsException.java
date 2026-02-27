package defenssiveprogramming.level3.exercise3;

import defenssiveprogramming.BankingException;

public class InsufficientFundsException extends BankingException {
    private double requiredAmount;
    private double availableAmount;

    public InsufficientFundsException(String message, String errorCode, double requiredAmount, double availableAmount) {
        super(message, errorCode);
        this.requiredAmount = requiredAmount;
        this.availableAmount = availableAmount;
    }
}
