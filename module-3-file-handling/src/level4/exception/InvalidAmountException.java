package level4.exception;

public class InvalidAmountException extends BankingException {
    public InvalidAmountException(String message, String errorCode) {
        super(message, errorCode);
    }

    public static InvalidAmountException withDefaultCode(String message) {
        return new InvalidAmountException(message, "ERR_001");
    }

    public static InvalidAmountException fromMessageAndCode(String message, String errorCode) {
        return new InvalidAmountException(message, errorCode);
    }
}
