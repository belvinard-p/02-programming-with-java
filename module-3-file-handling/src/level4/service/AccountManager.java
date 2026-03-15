package level4.service;

import level4.exception.InvalidAccountException;
import level4.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountManager {
    private static final Logger LOGGER = Logger.getLogger(AccountManager.class.getName());
    private static final String DATE_TODAY = "2024-01-01";
    private final List<Account> accounts = new ArrayList<>();
    private int accountCounter = 0;

    public Account createAccount(String type, String holderName, double initialDeposit) {
        accountCounter++;
        String accountId = String.format("ACC%03d", accountCounter);
        Account account = buildAccount(type, accountId, holderName, initialDeposit);
        accounts.add(account);
        LOGGER.log(Level.INFO, "Account created: {0}", account);
        return account;
    }

    private Account buildAccount(String type, String accountId, String holderName, double initialDeposit) {
        switch (type.toUpperCase()) {
            case "SAVINGS":
                return new SavingsAccount(accountId, holderName, initialDeposit, DATE_TODAY, 0.03);
            case "CHECKING":
                return new CheckingAccount(accountId, holderName, initialDeposit, DATE_TODAY);
            case "MONEY_MARKET":
                return new MoneyMarketAccount(accountId, holderName, initialDeposit, DATE_TODAY, 0.04);
            case "CD":
                return new CertificateOfDeposit(accountId, holderName, initialDeposit, DATE_TODAY, 0.05);
            case "BUSINESS":
                return new BusinessAccount(accountId, holderName, initialDeposit, DATE_TODAY);
            default:
                return new CheckingAccount(accountId, holderName, initialDeposit, DATE_TODAY);
        }
    }

    public Account getAccount(String accountId) throws InvalidAccountException {
        for (Account account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        throw new InvalidAccountException(accountId);
    }

    public boolean closeAccount(String accountId) throws InvalidAccountException {
        Account account = getAccount(accountId);
        account.close();
        LOGGER.log(Level.INFO, "Account closed: {0}", accountId);
        return true;
    }

    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public double getTotalSystemBalance() {
        double total = 0;
        for (Account account : accounts) {
            total += account.getBalance();
        }
        return total;
    }

    public int getAccountCount() { return accounts.size(); }
}
