package level4.model;

import level4.exception.BankingException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WithdrawalTransaction extends Transaction {
    private static final Logger LOGGER = Logger.getLogger(WithdrawalTransaction.class.getName());
    private final Account sourceAccount;
    private final String withdrawalMethod;

    public WithdrawalTransaction(String transactionId, double amount, Account sourceAccount, String withdrawalMethod) {
        super(transactionId, amount);
        this.sourceAccount = sourceAccount;
        this.withdrawalMethod = withdrawalMethod;
    }

    @Override
    public boolean process() throws BankingException {
        try {
            sourceAccount.withdraw(amount);
            markCompleted();
            LOGGER.log(Level.INFO, "Withdrawal {0} from {1} via {2}",
                    new Object[]{amount, sourceAccount.getAccountId(), withdrawalMethod});
            return true;
        } catch (BankingException e) {
            markFailed();
            throw e;
        }
    }

    @Override
    public String getTransactionType() {
        return "WITHDRAWAL";
    }

    public String getWithdrawalMethod() {
        return withdrawalMethod;
    }
}
