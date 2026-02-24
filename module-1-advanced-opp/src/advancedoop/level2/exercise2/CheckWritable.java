package advancedoop.level2.exercise2;

public interface CheckWritable {
    boolean writeCheck(String payee, double amount, String checkNumber);
    int getRemainingChecks();
}