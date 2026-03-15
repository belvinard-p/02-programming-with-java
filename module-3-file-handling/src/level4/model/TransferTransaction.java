package level4.model;

import level4.exception.BankingException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TransferTransaction extends Transaction {
    private static final Logger LOGGER = Logger.getLogger(TransferTransaction.class.getName());
    private final Account fromAccount;
    private final Account toAccount;

    public TransferTransaction(String transactionId, double amount, Account fromAccount, Account toAccount) {
        super(transactionId, amount);
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }

    @Override
    public boolean process() throws BankingException {
        try {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
            markCompleted();
            LOGGER.log(Level.INFO, "Transfer {0} from {1} to {2}", new Object[]{amount, fromAccount.getAccountId(),
                    toAccount.getAccountId()});
            return true;
        } catch (BankingException e) {
            markFailed();
            throw e;
        }
    }

    @Override
    public String getTransactionType() {
        return "TRANSFER";
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }
}
