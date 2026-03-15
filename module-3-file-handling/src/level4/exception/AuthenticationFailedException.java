package level4.exception;

public class AuthenticationFailedException extends BankingException {

    public AuthenticationFailedException(String customerId) {
        super("Authentication failed for customer: " + customerId, "ERR_006");
    }
}
