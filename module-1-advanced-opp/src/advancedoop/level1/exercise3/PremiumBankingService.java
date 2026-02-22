package advancedoop.level1.exercise3;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PremiumBankingService implements Transferable, BillPayable, Notifiable {
    private static final Logger logger = Logger.getLogger(PremiumBankingService.class.getName());
    private ArrayList<String> transferHistory;
    private ArrayList<String> paymentHistory;
    private String notificationPreference;

    public PremiumBankingService() {
        this.transferHistory = new ArrayList<>();
        this.paymentHistory = new ArrayList<>();
        this.notificationPreference = "SMS";
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
    public boolean payBill(String billerName, double amount) {
        if (amount <= 0) {
            logger.log(Level.WARNING, "Bill amount must be positive");
            return false;
        }
        String entry = String.format("Paid $%.2f to %s", amount, billerName);
        paymentHistory.add(entry);
        logger.log(Level.INFO, entry);
        return true;
    }

    @Override
    public void schedulePayment(String billerName, double amount, String date) {
        String entry = String.format("Scheduled payment: $%.2f to %s on %s", amount, billerName, date);
        paymentHistory.add(entry);
        logger.log(Level.INFO, entry);
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
