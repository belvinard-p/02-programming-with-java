package filehandling;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountDataWriter {
    private static final Logger logger = Logger.getLogger(AccountDataWriter.class.getName());

    public void saveAccount(BankAccount account, String filename) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
            writer.write(account.toString());
            writer.newLine();
            logger.log(Level.INFO, "Account saved to {0}", filename);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving account to file: {0}", filename);
            throw new IOException("Failed to save account " + account.getAccountId() + " to " + filename, e);
        }
    }

    public void saveMultipleAccounts(BankAccount[] accounts, String filename) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
            for (BankAccount account : accounts) {
                writer.write(account.toString());
                writer.newLine();
            }
            logger.log(Level.INFO, "Accounts saved to {0}", filename);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving accounts to file: {0}", filename);
            throw new IOException("Failed to save accounts to " + filename, e);
        }
    }
    
    public void appendTransaction(String filename, Transaction transaction) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(transaction.toCSV());
            writer.newLine();
            logger.log(Level.INFO, "Transaction appended to {0}", filename);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error appending transaction to file: {0}", filename);
            throw new IOException("Failed to append transaction to " + filename, e);
        }
    }
}
