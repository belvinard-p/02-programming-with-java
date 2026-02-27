package defenssiveprogramming.level3.exercise3;

public class BankingException extends Exception {
    private String errorCode;
    
    public BankingException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
}
