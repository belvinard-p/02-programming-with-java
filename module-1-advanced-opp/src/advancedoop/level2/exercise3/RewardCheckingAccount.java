package advancedoop.level2.exercise3;

public class RewardCheckingAccount implements CheckingCapable, RewardCapable {
    private double balance;
    private double rewardBalance;

    public RewardCheckingAccount(double initialBalance) {
        this.balance = initialBalance;
        this.rewardBalance = 0;
    }

    @Override
    public boolean allowsOverdraft() {
        return true;
    }

    @Override
    public double getOverdraftLimit() {
        return 500;
    }

    @Override
    public int getFreeTransactions() {
        return 0;
    }

    @Override
    public double earnRewards() {
        double cashback = balance * 0.02;
        rewardBalance += cashback;
        return cashback;
    }

    @Override
    public String getRewardType() {
        return "cashback";
    }

    @Override
    public double getRewardBalance() {
        return rewardBalance;
    }

    public double getBalance() {
        return balance;
    }
}
