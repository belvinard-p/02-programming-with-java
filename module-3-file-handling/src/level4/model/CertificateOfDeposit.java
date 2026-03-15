package level4.model;

import level4.exception.BankingException;
import level4.interfaces.Depositable;
import level4.interfaces.Reportable;

public class CertificateOfDeposit extends InterestBearingAccount implements Depositable, Reportable {
    private static final double MIN_BALANCE = 1000.0;

    public CertificateOfDeposit(String accountId, String accountHolder, double balance, String dateOpened, double interestRate) {
        super(accountId, accountHolder, balance, dateOpened, interestRate);
    }

    @Override
    public String getAccountType() { return "CERTIFICATE_OF_DEPOSIT"; }

    @Override
    public double calculateMonthlyFees() { return 0.00; }

    @Override
    public boolean meetsMinimumBalance() { return balance >= MIN_BALANCE; }

    @Override
    public double calculateInterest() { return balance * interestRate; }

    @Override
    public void performDeposit(double amount) throws BankingException {
        deposit(amount);
    }

    @Override
    public String generateStatement() {
        return "=== Statement: " + accountId + " ===\nHolder: " + accountHolder
                + "\nBalance: " + balance + "\nInterest Rate: " + interestRate
                + "\nType: Certificate of Deposit (Locked)";
    }

    @Override
    public String generateMonthlyReport() {
        double interest = calculateInterest();
        return "=== Monthly Report: " + accountId + " ===\nBalance: " + balance
                + "\nInterest Earned: " + interest
                + "\nFee: $0.00 (No fees on CD)";
    }
}
