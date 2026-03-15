package level4.interfaces;

import level4.exception.BankingException;

public interface Transferable {
    boolean transferFunds(String toAccount, double amount) throws BankingException;
    String[] getTransferHistory();
}
