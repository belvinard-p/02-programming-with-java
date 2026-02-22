package advancedoop.level2.exercise1;

public class MoneyMarketAccount extends InterestBearingAccount {
    private int tierLevel;

    public MoneyMarketAccount(String accountId, double balance, String dateOpened, double annualInterestRate) {
        super(accountId, balance, dateOpened, annualInterestRate);
        this.tierLevel = determineTierLevel(balance);
    }

    @Override
    public String getAccountType() {
        return "MONEY_MARKET";
    }

    @Override
    public double calculateMinimumBalance() {
        return 2500.0;
    }

    /**
     * Calculates monthly interest with tiered bonus rates based on account balance.
     * Higher balances earn higher interest rates through tier multipliers.
     *
     * Scenario: Balance = $60,000, Annual Rate = 4%
     *
     * tierLevel = 2  // $60K is in tier 2
     * tierMultiplier = 1.0 + (2 * 0.25) = 1.5
     *
     * Interest = 60000 * 4 * 1.5 / 100 / 12
     *          = 60000 * 4 * 1.5 / 1200
     *          = 60000 * 6 / 1200
     *          = $300 per month
     * @return monthly interest amount
     */
    @Override
    public double calculateInterest() {
        tierLevel = determineTierLevel(getBalance());
        double tierMultiplier = 1.0 + (tierLevel * 0.25);
        return getBalance() * annualInterestRate * tierMultiplier / 100 / 12;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            return false;
        }
        if (getBalance() < amount) {
            return false;
        }
        setBalance(getBalance() - amount);
        return true;
    }

    private int determineTierLevel(double balance) {
        if (balance >= 100000) return 3;
        if (balance >= 50000) return 2;
        if (balance >= 10000) return 1;
        return 0;
    }
}
