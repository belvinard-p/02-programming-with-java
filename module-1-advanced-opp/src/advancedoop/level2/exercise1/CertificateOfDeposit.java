package advancedoop.level2.exercise1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Certificate of Deposit (CD) account with fixed term and early withdrawal penalties.
 * Offers higher interest rates for longer terms but restricts withdrawals before maturity.
 */
public class CertificateOfDeposit extends InterestBearingAccount {
    private static final Logger logger = Logger.getLogger(CertificateOfDeposit.class.getName());

    private int termMonths;
    private String maturityDate;
    private double earlyWithdrawalPenalty;

    public CertificateOfDeposit(String accountId, double balance, String dateOpened, 
                                double annualInterestRate, int termMonths, 
                                String maturityDate, double earlyWithdrawalPenalty) {
        super(accountId, balance, dateOpened, annualInterestRate);
        this.termMonths = termMonths;
        this.maturityDate = maturityDate;
        this.earlyWithdrawalPenalty = earlyWithdrawalPenalty;
    }

    @Override
    public String getAccountType() {
        return "CD";
    }

    @Override
    public double calculateMinimumBalance() {
        return 1000.0;
    }

    @Override
    public double calculateInterest() {
        double termBonus = 1.0 + (termMonths / 12.0 * 0.5); // 50% bonus per year
        return getBalance() * annualInterestRate * termBonus / 100 / 12;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            logger.log(Level.WARNING, "Withdrawal amount must be positive");
            return false;
        }

        double totalAmount = amount;
        
        // Check if withdrawal is before maturity
        if (isBeforeMaturity()) {
            totalAmount += earlyWithdrawalPenalty;
            logger.log(Level.WARNING, "Early withdrawal penalty of ${0} applied", earlyWithdrawalPenalty);
        }

        if (getBalance() < totalAmount) {
            logger.log(Level.WARNING, "Insufficient funds");
            return false;
        }

        setBalance(getBalance() - totalAmount);
        logger.log(Level.INFO, "Withdrawn: ${0}. New balance: ${1}", 
            new Object[]{totalAmount, getBalance()});
        return true;
    }

    private boolean isBeforeMaturity() {
        LocalDate today = LocalDate.now();
        LocalDate maturity = LocalDate.parse(maturityDate, DateTimeFormatter.ISO_LOCAL_DATE);
        return today.isBefore(maturity);
    }
}
