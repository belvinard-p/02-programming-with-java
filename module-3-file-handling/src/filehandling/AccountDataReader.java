package filehandling;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountDataReader {
    private static final Logger logger = Logger.getLogger(AccountDataReader.class.getName());

    public BankAccount loadAccount(String filename) throws IOException {
        logger.log(Level.INFO, "Loading account from file: {0}", filename);

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
            String line = reader.readLine();

            if (line != null) {
                String[] parts = line.split(" \\| ");
                String accountId = parts[0];
                String ownerName = parts[1];
                double balance = Double.parseDouble(parts[2].split(" ")[0]);
                BankAccount account = new BankAccount(accountId, ownerName, balance);
                logger.log(Level.INFO, "Account data: {0}", line);
                return account;

            } else {
                logger.log(Level.WARNING, "No data found in file: {0}", filename);
                throw new IOException("No data found in file: " + filename);
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "File not found: {0}", filename);
            throw new IOException("File not found: " + filename, e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading account from file: {0}", filename);
            throw new IOException("Failed to read account from " + filename, e);
        }


    }
}
