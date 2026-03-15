package level4.model;

import level4.exception.BankingException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BillPaymentTransaction extends Transaction {
    private static final Logger LOGGER = Logger.getLogger(BillPaymentTransaction.class.getName());
    private final Account sourceAccount;
    private final String payee;
    private final String billAccount;

    public BillPaymentTransaction(String transactionId, double amount, Account sourceAccount, String payee, String billAccount) {
        super(transactionId, amount);
        this.sourceAccount = sourceAccount;
        this.payee = payee;
        this.billAccount = billAccount;
    }

    @Override
    public boolean process() throws BankingException {
        try {
            sourceAccount.withdraw(amount);
            markCompleted();
            LOGGER.log(Level.INFO, "Bill payment {0} to {1} from {2}", new Object[]{amount, payee, sourceAccount.getAccountId()});
            return true;
        } catch (BankingException e) {
            markFailed();
            throw e;
        }
    }

    @Override
    public String getTransactionType() { return "BILL_PAYMENT"; }

    public String getPayee() { return payee; }
    public String getBillAccount() { return billAccount; }
}
