package advancedoop.level2.exercise3;

public interface CheckingCapable {
    boolean allowsOverdraft();
    double getOverdraftLimit();
    int getFreeTransactions();
}