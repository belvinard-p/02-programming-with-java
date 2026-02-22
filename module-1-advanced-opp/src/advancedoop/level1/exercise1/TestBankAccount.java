package advancedoop.level1.exercise1;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TestBankAccount {
    private static final Logger logger = Logger.getLogger(TestBankAccount.class.getName());
    
    public static void main(String[] args) {
        logger.log(Level.INFO, "=== Testing Savings Account ===");
        BankAccount savingsAccount = new SavingsAccount("SA001", 1500.0, "Belvinard Pouadjeu", 2.5);
        savingsAccount.displayAccountInfo();
        
        logger.log(Level.INFO, "\nCalculated Interest: $ {0}", savingsAccount.calculateInterest());
        savingsAccount.deposit(500.0);
        savingsAccount.withdraw(200.0);
        savingsAccount.applyMonthlyFees();
        
        logger.log(Level.INFO, "\n=== Testing Checking Account ===");
        BankAccount checkingAccount = new CheckingAccount("CA001", 500.0, "Ali Souley", 1000.0);
        checkingAccount.displayAccountInfo();
        
        logger.log(Level.INFO, "\nCalculated Interest: $ {0}", checkingAccount.calculateInterest());
        checkingAccount.deposit(300.0);
        checkingAccount.withdraw(1200.0); // Test overdraft
        checkingAccount.applyMonthlyFees();
        
        logger.log(Level.INFO, "\n=== Testing Edge Cases ===");
        savingsAccount.withdraw(-50.0); // Negative amount
        checkingAccount.withdraw(5000.0); // Exceeds overdraft
    }
}
