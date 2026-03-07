package filehandling;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountDataReader {
    private static final Logger LOGGER = Logger.getLogger(AccountDataReader.class.getName());
    private static final String DELIMITER = " \\| ";
    private static final int ACCOUNT_ID_INDEX = 0;
    private static final int OWNER_NAME_INDEX = 1;
    private static final int BALANCE_INDEX = 2;
    private static final int EXPECTED_PARTS = 3;

    public BankAccount loadAccount(String filename) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
            String line = reader.readLine();
            if (line == null || line.trim().isEmpty()) {
                throw new IOException("Empty file: " + filename);
            }
            return parseAccount(line);
        }
    }

    public BankAccount[] loadMultipleAccounts(String filename) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
            List<BankAccount> accounts = new ArrayList<>();
            String line;
            
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    accounts.add(parseAccount(line));
                }
            }
            
            LOGGER.log(Level.INFO, "Loaded {0} accounts from {1}", new Object[]{accounts.size(), filename});
            return accounts.toArray(new BankAccount[0]);
        }
    }

    private BankAccount parseAccount(String line) throws IOException {
        String[] parts = line.split(DELIMITER);
        
        if (parts.length != EXPECTED_PARTS) {
            throw new IOException("Invalid format: expected " + EXPECTED_PARTS + " parts, got " + parts.length);
        }
        
        String accountId = parts[ACCOUNT_ID_INDEX].trim();
        String ownerName = parts[OWNER_NAME_INDEX].trim();
        String balanceStr = parts[BALANCE_INDEX].trim().split(" ")[0];
        
        if (accountId.isEmpty() || ownerName.isEmpty()) {
            throw new IOException("Invalid account data: empty fields");
        }
        
        try {
            double balance = Double.parseDouble(balanceStr);
            return new BankAccount(accountId, ownerName, balance);
        } catch (NumberFormatException e) {
            throw new IOException("Invalid balance format: " + balanceStr, e);
        }
    }
}
