package level4.model;

import level4.exception.BankingException;
import level4.interfaces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BusinessAccount extends Account implements Depositable, Withdrawable, Transferable, BillPayable, Investable {
    private static final Logger LOGGER = Logger.getLogger(BusinessAccount.class.getName());
    private static final double MONTHLY_FEE = 10.00;
    private static final double MIN_BALANCE = 5000.0;
    private final List<String> transferHistory = new ArrayList<>();
    private final List<String> scheduledPayments = new ArrayList<>();
    private double investmentReturns = 0.0;

    public BusinessAccount(String accountId, String accountHolder, double balance, String dateOpened) {
        super(accountId, accountHolder, balance, dateOpened);
    }

    @Override
    public String getAccountType() { return "BUSINESS"; }

    @Override
    public double calculateMonthlyFees() { return MONTHLY_FEE; }

    @Override
    public boolean meetsMinimumBalance() { return balance >= MIN_BALANCE; }

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
    public boolean transferFunds(String toAccount, double amount) throws BankingException {
        withdraw(amount);
        transferHistory.add("TRANSFER: " + amount + " to " + toAccount);
        LOGGER.log(Level.INFO, "Business transfer {0} to {1}", new Object[]{amount, toAccount});
        return true;
    }

    @Override
    public String[] getTransferHistory() {
        return transferHistory.toArray(new String[0]);
    }

    @Override
    public boolean payBill(String payee, double amount) throws BankingException {
        withdraw(amount);
        LOGGER.log(Level.INFO, "Business bill paid: {0} to {1}", new Object[]{amount, payee});
        return true;
    }

    @Override
    public void schedulePayment(String payee, double amount, String date) {
        scheduledPayments.add(date + "|" + payee + "|" + amount);
        LOGGER.log(Level.INFO, "Business payment scheduled: {0} to {1} on {2}", new Object[]{amount, payee, date});
    }

    @Override
    public boolean invest(String fundName, double amount) throws BankingException {
        withdraw(amount);
        investmentReturns += amount * 0.06;
        LOGGER.log(Level.INFO, "Business invested {0} into {1}", new Object[]{amount, fundName});
        return true;
    }

    @Override
    public double getInvestmentReturns() { return investmentReturns; }
}
