package advancedoop.level1.exercise3;

public interface Transferable {
    boolean transferFunds(String toAccount, double amount);
    String getTransferHistory();
}