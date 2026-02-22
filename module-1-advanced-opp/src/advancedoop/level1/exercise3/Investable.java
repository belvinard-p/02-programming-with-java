package advancedoop.level1.exercise3;

public interface Investable {
    boolean invest(String fundName, double amount);
    double getInvestmentReturns();
}