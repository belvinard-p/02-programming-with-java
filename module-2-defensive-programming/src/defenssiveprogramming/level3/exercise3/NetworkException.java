package defenssiveprogramming.level3.exercise3;

public class NetworkException extends BankingException {
    private boolean retryable;
    
    public NetworkException(String message, String networkError, boolean retryable) {
        super(message + (retryable ? " (Retryable)" : " (Not retryable)"), "NETWORK_ERROR");
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
