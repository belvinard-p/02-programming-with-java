package defenssiveprogramming.level3.exercise5;

public class NetworkException extends BankingException {
    private final boolean retryable;
    
    public NetworkException(String message, String errorCode, boolean retryable) {
        super(message + (retryable ? " (Retryable)" : " (Not retryable)"), errorCode);
        this.retryable = retryable;
    }

    public String getRetryAdvice() {
        if (retryable) {
            return "This operation can be retried. Please try again in a few moments.";
        } else {
            return "This operation cannot be retried. Please contact support.";
        }
    }
}
