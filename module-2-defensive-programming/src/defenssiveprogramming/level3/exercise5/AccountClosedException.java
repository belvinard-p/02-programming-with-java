package defenssiveprogramming.level3.exercise5;

public class AccountClosedException extends BankingException {
    private final String closureDate;
    
    public AccountClosedException(String accountNumber, String closureDate) {
        super(generateMessage(accountNumber, closureDate), "ACCOUNT_CLOSED");
        this.closureDate = closureDate;
    }
    
    private static String generateMessage(String accountNumber, String closureDate) {
        return "Account " + accountNumber + " is closed. Closure date: " + closureDate + 
               ". No transactions are permitted on closed accounts.";
    }
    
    public String getClosureDate() {
        return closureDate;
    }
}
