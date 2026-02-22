package advancedoop.level2.exercise1;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class InterestBearingAccount extends Account{
    private static final Logger logger = Logger.getLogger(InterestBearingAccount.class.getName());
    
    protected double annualInterestRate;
    
    protected InterestBearingAccount(String accountId, double balance, String dateOpened, double annualInterestRate) {
        super(accountId, balance, dateOpened);
        this.annualInterestRate = annualInterestRate;
    }
    
    public abstract double calculateInterest();
    
    public void applyInterest(){
        double interest = calculateInterest();
        deposit(interest);
        logger.log(Level.INFO, "Interest applied: {0}. New balance: {1}", new Object[]{interest, getBalance()});
    }
}
