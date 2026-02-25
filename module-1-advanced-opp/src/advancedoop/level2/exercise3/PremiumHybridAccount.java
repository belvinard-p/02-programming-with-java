package advancedoop.level2.exercise3;

public class PremiumHybridAccount implements SavingsCapable, CheckingCapable, RewardCapable {
    private double balance;
    private double rewardBalance;

    public PremiumHybridAccount(double initialBalance) {
        this.balance = initialBalance;
        this.rewardBalance = 0;
    }

    @Override
    public double earnInterest() {
        return balance * 0.03;
    }

    @Override
    public boolean hasMinimumBalanceRequirement() {
        return true;
    }

    @Override
    public double getMinimumBalance() {
        return 10000;
    }

    @Override
    public boolean allowsOverdraft() {
        return true;
    }

    @Override
    public double getOverdraftLimit() {
        return 1000;
    }

    @Override
    public int getFreeTransactions() {
        return 0;
    }

    @Override
    public double earnRewards() {
        double rewards = balance * 0.03;
        rewardBalance += rewards;
        return rewards;
    }

    @Override
    public String getRewardType() {
        return "miles";
    }

    @Override
    public double getRewardBalance() {
        return rewardBalance;
    }

    public double getBalance() {
        return balance;
    }
}
