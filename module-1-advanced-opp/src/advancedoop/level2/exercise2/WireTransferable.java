package advancedoop.level2.exercise2;

public interface WireTransferable {
    boolean sendWire(String recipientBank, String recipientAccount, double amount);
    double getWireTransferLimit();
}