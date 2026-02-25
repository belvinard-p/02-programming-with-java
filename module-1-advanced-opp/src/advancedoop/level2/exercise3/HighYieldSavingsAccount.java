package advancedoop.level2.exercise3;

public class HighYieldSavingsAccount implements SavingsCapable, RewardCapable {
    private double balance;
    private double rewardBalance;

    public HighYieldSavingsAccount(double initialBalance) {
        this.balance = initialBalance;
        this.rewardBalance = 0;
    }

    @Override
    public double earnInterest() {
        double rate = balance >= 10000 ? 0.04 : 0.025;
        return balance * rate;
    }

    @Override
    public boolean hasMinimumBalanceRequirement() {
        return true;
    }

    @Override
    public double getMinimumBalance() {
        return 5000;
    }

    @Override
    public double earnRewards() {
        double bonus = balance * 0.005;
        rewardBalance += bonus;
        return bonus;
    }

    @Override
    public String getRewardType() {
        return "points";
    }

    @Override
    public double getRewardBalance() {
        return rewardBalance;
    }

    public double getBalance() {
        return balance;
    }
}
