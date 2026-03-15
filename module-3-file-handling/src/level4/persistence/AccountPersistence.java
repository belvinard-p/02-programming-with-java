package level4.persistence;

import level4.model.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountPersistence {
    private static final Logger LOGGER = Logger.getLogger(AccountPersistence.class.getName());
    private static final String DELIMITER = "\\|";
    private static final int FIELD_ID = 0;
    private static final int FIELD_HOLDER = 1;
    private static final int FIELD_BALANCE = 2;
    private static final int FIELD_TYPE = 3;
    private static final int FIELD_ACTIVE = 4;
    private static final int FIELD_DATE = 5;
    private static final int EXPECTED_FIELDS = 6;

    public void saveAllAccounts(List<Account> accounts, String filename) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
            for (Account account : accounts) {
                writer.write(account.toFileFormat());
                writer.newLine();
            }
            LOGGER.log(Level.INFO, "Saved {0} accounts to {1}", new Object[]{accounts.size(), filename});
        }
    }

    public List<Account> loadAllAccounts(String filename) throws IOException {
        List<Account> accounts = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    accounts.add(parseAccount(line));
                }
            }
        }
        LOGGER.log(Level.INFO, "Loaded {0} accounts from {1}", new Object[]{accounts.size(), filename});
        return accounts;
    }

    private Account parseAccount(String line) throws IOException {
        String[] parts = line.split(DELIMITER);
        if (parts.length != EXPECTED_FIELDS) {
            throw new IOException("Invalid account format: expected " + EXPECTED_FIELDS + " fields, got " + parts.length);
        }

        String id = parts[FIELD_ID].trim();
        String holder = parts[FIELD_HOLDER].trim();
        double balance = Double.parseDouble(parts[FIELD_BALANCE].trim());
        String type = parts[FIELD_TYPE].trim();
        String date = parts[FIELD_DATE].trim();

        Account account = buildAccountByType(type, id, holder, balance, date);

        if (!"true".equals(parts[FIELD_ACTIVE].trim())) {
            account.close();
        }
        return account;
    }

    private Account buildAccountByType(String type, String id, String holder, double balance, String date) {
        switch (type) {
            case "SAVINGS":
                return new SavingsAccount(id, holder, balance, date, 0.03);
            case "MONEY_MARKET":
                return new MoneyMarketAccount(id, holder, balance, date, 0.04);
            case "CERTIFICATE_OF_DEPOSIT":
                return new CertificateOfDeposit(id, holder, balance, date, 0.05);
            case "BUSINESS":
                return new BusinessAccount(id, holder, balance, date);
            default:
                return new CheckingAccount(id, holder, balance, date);
        }
    }
}
