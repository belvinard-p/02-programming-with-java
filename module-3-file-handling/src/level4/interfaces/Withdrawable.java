package level4.interfaces;

import level4.exception.BankingException;

public interface Withdrawable {
    void performWithdrawal(double amount) throws BankingException;
    double getAvailableBalance();
}
