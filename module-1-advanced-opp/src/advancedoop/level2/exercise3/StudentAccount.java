package advancedoop.level2.exercise3;

public class StudentAccount implements SavingsCapable, CheckingCapable {
    private double balance;
    private double interestRate = 0.01;

    public StudentAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public double earnInterest() {
        return balance * interestRate;
    }

    @Override
    public boolean hasMinimumBalanceRequirement() {
        return false;
    }

    @Override
    public double getMinimumBalance() {
        return 0;
    }

    @Override
    public boolean allowsOverdraft() {
        return true;
    }

    @Override
    public double getOverdraftLimit() {
        return 100;
    }

    @Override
    public int getFreeTransactions() {
        return 0;
    }

    public double getBalance() {
        return balance;
    }
}
