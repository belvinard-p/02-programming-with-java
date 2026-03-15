package level4.exception;

public class TransactionLimitExceededException extends BankingException {
    private final double transactionAmount;
    private final double limitAmount;

    public TransactionLimitExceededException(double transactionAmount, double limitAmount) {
        super("Transaction limit exceeded: attempted " + transactionAmount + ", limit " + limitAmount, "ERR_004");
        this.transactionAmount = transactionAmount;
        this.limitAmount = limitAmount;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public double getLimitAmount() {
        return limitAmount;
    }
}
