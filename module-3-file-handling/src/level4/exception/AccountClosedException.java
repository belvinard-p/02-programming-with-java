package level4.exception;

public class AccountClosedException extends BankingException {

    public AccountClosedException(String accountId) {
        super("Account is closed: " + accountId, "ERR_005");
    }
}
