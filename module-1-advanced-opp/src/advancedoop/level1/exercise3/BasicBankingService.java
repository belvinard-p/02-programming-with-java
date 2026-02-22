package advancedoop.level1.exercise3;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BasicBankingService implements Transferable {
    private static final Logger logger = Logger.getLogger(BasicBankingService.class.getName());
    private ArrayList<String> transferHistory;

    public BasicBankingService() {
        this.transferHistory = new ArrayList<>();
    }

    @Override
    public boolean transferFunds(String toAccount, double amount) {
        if (amount <= 0) {
            logger.log(Level.WARNING, "Transfer amount must be positive");
            return false;
        }
        String entry = String.format("Transferred $%.2f to %s", amount, toAccount);
        transferHistory.add(entry);
        logger.log(Level.INFO, entry);
        return true;
    }

    @Override
    public String getTransferHistory() {
        return String.join("\n", transferHistory);
    }
}
