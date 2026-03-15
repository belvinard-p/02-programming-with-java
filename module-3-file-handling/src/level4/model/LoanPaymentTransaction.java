package level4.model;

import level4.exception.BankingException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoanPaymentTransaction extends Transaction {
    private static final Logger LOGGER = Logger.getLogger(LoanPaymentTransaction.class.getName());
    private final Account sourceAccount;
    private final String loanId;
    private final double principalAmount;
    private final double interestAmount;

    public LoanPaymentTransaction(String transactionId, Account sourceAccount, String loanId, double principalAmount, double interestAmount) {
        super(transactionId, principalAmount + interestAmount);
        this.sourceAccount = sourceAccount;
        this.loanId = loanId;
        this.principalAmount = principalAmount;
        this.interestAmount = interestAmount;
    }

    @Override
    public boolean process() throws BankingException {
        try {
            sourceAccount.withdraw(amount);
            markCompleted();
            LOGGER.log(Level.INFO, "Loan payment for {0}: principal={1}, interest={2}", new Object[]{loanId, principalAmount, interestAmount});
            return true;
        } catch (BankingException e) {
            markFailed();
            throw e;
        }
    }

    @Override
    public String getTransactionType() {
        return "LOAN_PAYMENT";
    }

    public String getLoanId() {
        return loanId;
    }

    public double getPrincipalAmount() {
        return principalAmount;
    }

    public double getInterestAmount() {
        return interestAmount;
    }
}
