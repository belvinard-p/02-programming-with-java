package level4.model;

import level4.exception.BankingException;
import level4.interfaces.BillPayable;
import level4.interfaces.Depositable;
import level4.interfaces.Transferable;
import level4.interfaces.Withdrawable;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckingAccount extends Account implements Depositable, Withdrawable, Transferable, BillPayable {
    private static final Logger LOGGER = Logger.getLogger(CheckingAccount.class.getName());
    private static final double MONTHLY_FEE = 5.00;
    private static final double MIN_BALANCE = 100.0;
    private final List<String> transferHistory = new ArrayList<>();
    private final List<String> scheduledPayments = new ArrayList<>();

    public CheckingAccount(String accountId, String accountHolder, double balance, String dateOpened) {
        super(accountId, accountHolder, balance, dateOpened);
    }

    @Override
    public String getAccountType() { return "CHECKING"; }

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
        LOGGER.log(Level.INFO, "Transfer {0} to {1}", new Object[]{amount, toAccount});
        return true;
    }

    @Override
    public String[] getTransferHistory() {
        return transferHistory.toArray(new String[0]);
    }

    @Override
    public boolean payBill(String payee, double amount) throws BankingException {
        withdraw(amount);
        LOGGER.log(Level.INFO, "Bill paid: {0} to {1}", new Object[]{amount, payee});
        return true;
    }

    @Override
    public void schedulePayment(String payee, double amount, String date) {
        scheduledPayments.add(date + "|" + payee + "|" + amount);
        LOGGER.log(Level.INFO, "Payment scheduled: {0} to {1} on {2}", new Object[]{amount, payee, date});
    }
}
