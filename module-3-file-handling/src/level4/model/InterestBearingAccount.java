package level4.model;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class InterestBearingAccount extends Account {
    private static final Logger LOGGER = Logger.getLogger(InterestBearingAccount.class.getName());
    protected double interestRate;

    protected InterestBearingAccount(String accountId, String accountHolder, double balance, String dateOpened, double interestRate) {
        super(accountId, accountHolder, balance, dateOpened);
        this.interestRate = interestRate;
    }

    public abstract double calculateInterest();

    public void applyInterest() {
        double interest = calculateInterest();
        balance += interest;
        LOGGER.log(Level.INFO, "Interest {0} applied to {1}", new Object[]{interest, accountId});
    }

    public double getInterestRate() {
        return interestRate;
    }
}
