package level4.interfaces;

import level4.exception.BankingException;

public interface Investable {
    boolean invest(String fundName, double amount) throws BankingException;
    double getInvestmentReturns();
}
