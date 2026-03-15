package level4.model;

import level4.exception.BankingException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DepositTransaction extends Transaction {
    private static final Logger LOGGER = Logger.getLogger(DepositTransaction.class.getName());
    private final Account targetAccount;
    private final String depositMethod;

    public DepositTransaction(String transactionId, double amount, Account targetAccount, String depositMethod) {
        super(transactionId, amount);
        this.targetAccount = targetAccount;
        this.depositMethod = depositMethod;
    }

    @Override
    public boolean process() throws BankingException {
        try {
            targetAccount.deposit(amount);
            markCompleted();
            LOGGER.log(Level.INFO, "Deposit {0} to {1} via {2}", new Object[]{amount, targetAccount.getAccountId(), depositMethod});
            return true;
        } catch (BankingException e) {
            markFailed();
            throw e;
        }
    }

    @Override
    public String getTransactionType() {
        return "DEPOSIT";
    }

    public String getDepositMethod() {
        return depositMethod;
    }
}
