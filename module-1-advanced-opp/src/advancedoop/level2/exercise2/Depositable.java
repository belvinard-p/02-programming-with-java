package advancedoop.level2.exercise2;

public interface Depositable {
    boolean deposit(double amount);
    String getDepositMethod(); // cash, check, transfer, etc.
}