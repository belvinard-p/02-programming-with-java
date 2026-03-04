package filehandling;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
}
