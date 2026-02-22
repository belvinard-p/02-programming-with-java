package advancedoop.level1.exercise3;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BasicBankingService implements Transferable, Notifiable {
    private static final Logger logger = Logger.getLogger(BasicBankingService.class.getName());
    private ArrayList<String> transferHistory;
    private String notificationPreference;

    public BasicBankingService() {
        this.transferHistory = new ArrayList<>();
        this.notificationPreference = "EMAIL";
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

    @Override
    public void sendNotification(String message) {
        logger.log(Level.INFO, "[NOTIFICATION] {0}", message);
    }

    @Override
    public String getNotificationPreference() {
        return notificationPreference;
    }
}
