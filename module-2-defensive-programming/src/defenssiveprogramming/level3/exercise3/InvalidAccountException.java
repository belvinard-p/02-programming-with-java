package defenssiveprogramming.level3.exercise3;

public class InvalidAccountException extends BankingException {
    private String accountNumber;
    
    public InvalidAccountException(String accountNumber) {
        super(generateMessage(accountNumber), "INVALID_ACCOUNT");
        this.accountNumber = accountNumber;
    }
    
    private static String generateMessage(String accountNumber) {
        return "Invalid account number: " + accountNumber + ". Account does not exist or is not accessible.";
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
}
