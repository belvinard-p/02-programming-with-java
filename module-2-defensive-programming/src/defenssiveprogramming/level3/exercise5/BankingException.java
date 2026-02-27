package defenssiveprogramming.level3.exercise5;

public class BankingException extends Exception {
    private final String errorCode;
    
    public BankingException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
}
