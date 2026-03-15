package level4.exception;

public class InvalidAccountException extends BankingException {
    private final String accountNumber;

    public InvalidAccountException(String accountNumber) {
        super("Invalid account: " + accountNumber, "ERR_003");
        this.accountNumber = accountNumber;
    }

}
