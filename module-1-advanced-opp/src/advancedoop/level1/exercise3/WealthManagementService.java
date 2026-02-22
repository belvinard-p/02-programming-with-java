package advancedoop.level1.exercise3;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WealthManagementService implements Transferable, BillPayable, Investable, Notifiable {
    private static final Logger logger = Logger.getLogger(WealthManagementService.class.getName());
    private ArrayList<String> transferHistory;
    private ArrayList<String> paymentHistory;
    private ArrayList<String> investmentHistory;
    private double totalInvested;
    private String notificationPreference;

    public WealthManagementService() {
        this.transferHistory = new ArrayList<>();
        this.paymentHistory = new ArrayList<>();
        this.investmentHistory = new ArrayList<>();
        this.totalInvested = 0.0;
        this.notificationPreference = "PUSH";
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
    public boolean invest(String fundName, double amount) {
        if (amount <= 0) {
            logger.log(Level.WARNING, "Investment amount must be positive");
            return false;
        }
        String entry = String.format("Invested $%.2f in %s", amount, fundName);
        investmentHistory.add(entry);
        totalInvested += amount;
        logger.log(Level.INFO, entry);
        return true;
    }

    @Override
    public double getInvestmentReturns() {
        double returns = totalInvested * 0.07; // 7% return
        logger.log(Level.INFO, "Total investment returns: ${0}", String.format("%.2f", returns));
        return returns;
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
