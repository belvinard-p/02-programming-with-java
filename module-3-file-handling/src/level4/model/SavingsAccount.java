package level4.model;

import level4.exception.BankingException;
import level4.interfaces.Depositable;
import level4.interfaces.Reportable;
import level4.interfaces.Withdrawable;

public class SavingsAccount extends InterestBearingAccount implements Depositable, Withdrawable, Reportable {
    private static final double MONTHLY_FEE = 2.00;
    private static final double MIN_BALANCE = 500.0;

    public SavingsAccount(String accountId, String accountHolder, double balance, String dateOpened, double interestRate) {
        super(accountId, accountHolder, balance, dateOpened, interestRate);
    }

    @Override
    public String getAccountType() { return "SAVINGS"; }

    @Override
    public double calculateMonthlyFees() { return MONTHLY_FEE; }

    @Override
    public boolean meetsMinimumBalance() { return balance >= MIN_BALANCE; }

    @Override
    public double calculateInterest() { return balance * interestRate; }

    @Override
    public void performDeposit(double amount) throws BankingException {
        deposit(amount);
    }

    @Override
    public void performWithdrawal(double amount) throws BankingException {
        withdraw(amount);
    }

    @Override
    public double getAvailableBalance() { return balance; }

    @Override
    public String generateStatement() {
        return "=== Statement: " + accountId + " ===\nHolder: " + accountHolder
                + "\nBalance: " + balance + "\nInterest Rate: " + interestRate
                + "\nMonthly Fee: " + MONTHLY_FEE;
    }

    @Override
    public String generateMonthlyReport() {
        double interest = calculateInterest();
        return "=== Monthly Report: " + accountId + " ===\nBalance: " + balance
                + "\nInterest Earned: " + interest
                + "\nFee Deducted: " + MONTHLY_FEE
                + "\nNet Change: " + (interest - MONTHLY_FEE);
    }
}
