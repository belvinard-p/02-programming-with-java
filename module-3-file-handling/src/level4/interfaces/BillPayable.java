package level4.interfaces;

import level4.exception.BankingException;

public interface BillPayable {
    boolean payBill(String payee, double amount) throws BankingException;

    void schedulePayment(String payee, double amount, String date);
}
