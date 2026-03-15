package level4.model;

import level4.exception.BankingException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class InvestmentTransaction extends Transaction {
    private static final Logger LOGGER = Logger.getLogger(InvestmentTransaction.class.getName());
    private final Account sourceAccount;
    private final String fundName;

    public InvestmentTransaction(String transactionId, double amount, Account sourceAccount, String fundName) {
        super(transactionId, amount);
        this.sourceAccount = sourceAccount;
        this.fundName = fundName;
    }

    @Override
    public boolean process() throws BankingException {
        try {
            sourceAccount.withdraw(amount);
            markCompleted();
            LOGGER.log(Level.INFO, "Investment {0} into {1} from {2}", new Object[]{amount, fundName, sourceAccount.getAccountId()});
            return true;
        } catch (BankingException e) {
            markFailed();
            throw e;
        }
    }

    @Override
    public String getTransactionType() {
        return "INVESTMENT";
    }

    public String getFundName() {
        return fundName;
    }
}
