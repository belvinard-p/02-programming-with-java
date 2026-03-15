package level4.interfaces;

import level4.exception.BankingException;

public interface Depositable {
    void performDeposit(double amount) throws BankingException;
}
