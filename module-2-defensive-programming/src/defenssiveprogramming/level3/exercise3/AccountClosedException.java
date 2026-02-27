package defenssiveprogramming.level3.exercise3;

public class AccountClosedException extends BankingException {
    private String closureDate;
    
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
