package level4.model;

import level4.exception.BankingException;
import level4.interfaces.Depositable;
import level4.interfaces.Investable;
import level4.interfaces.Reportable;
import level4.interfaces.Withdrawable;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MoneyMarketAccount extends InterestBearingAccount implements Depositable, Withdrawable, Investable, Reportable {
    private static final Logger LOGGER = Logger.getLogger(MoneyMarketAccount.class.getName());
    private static final double MONTHLY_FEE = 3.00;
    private static final double MIN_BALANCE = 2500.0;
    private double investmentReturns = 0.0;

    public MoneyMarketAccount(String accountId, String accountHolder, double balance, String dateOpened, double interestRate) {
        super(accountId, accountHolder, balance, dateOpened, interestRate);
    }

    @Override
    public String getAccountType() { return "MONEY_MARKET"; }

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
    public boolean invest(String fundName, double amount) throws BankingException {
        withdraw(amount);
        investmentReturns += amount * 0.08;
        LOGGER.log(Level.INFO, "Invested {0} into {1}", new Object[]{amount, fundName});
        return true;
    }

    @Override
    public double getInvestmentReturns() { return investmentReturns; }

    @Override
    public String generateStatement() {
        return "=== Statement: " + accountId + " ===\nHolder: " + accountHolder
                + "\nBalance: " + balance + "\nInvestment Returns: " + investmentReturns;
    }

    @Override
    public String generateMonthlyReport() {
        double interest = calculateInterest();
        return "=== Monthly Report: " + accountId + " ===\nBalance: " + balance
                + "\nInterest Earned: " + interest
                + "\nInvestment Returns: " + investmentReturns
                + "\nFee Deducted: " + MONTHLY_FEE;
    }
}
