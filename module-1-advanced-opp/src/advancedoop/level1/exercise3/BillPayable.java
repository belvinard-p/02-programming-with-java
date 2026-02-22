package advancedoop.level1.exercise3;

public interface BillPayable {
    boolean payBill(String billerName, double amount);
    void schedulePayment(String billerName, double amount, String date);
}