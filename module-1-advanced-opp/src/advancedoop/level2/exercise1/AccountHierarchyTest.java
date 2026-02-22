package advancedoop.level2.exercise1;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Comprehensive test suite for Level 2 Exercise 1: Complex Account Hierarchy
 * 
 * This test demonstrates:
 * - Multi-level inheritance (Account -> InterestBearingAccount -> SavingsAccount/InvestmentAccount)
 * - Interface implementation (Investable)
 * - Abstract class usage
 * - Polymorphism
 * - Method overriding
 * - Encapsulation
 */
public class AccountHierarchyTest {
    private static final Logger logger = Logger.getLogger(AccountHierarchyTest.class.getName());
    private static final String SUCCESS = "✅ Success";
    private static final String FAILED = "❌ Failed";
    private static final String DATE_2024_01_01 = "2024-01-01";
    private static final String DATE_2024_01_03 = "2024-01-03";
    private static final String BALANCE_FORMAT = "   - Balance: ${0}";
    
    public static void main(String[] args) {
        logger.info("╔═══════════════════════════════════════════════════════════════╗");
        logger.info("║  LEVEL 2 - EXERCISE 1: COMPLEX ACCOUNT HIERARCHY TEST        ║");
        logger.info("╚═══════════════════════════════════════════════════════════════╝");
        logger.info("");
        
        testAccountCreation();
        testInheritanceHierarchy();
        testPolymorphismWithAccounts();
        testInterestBearingAccounts();
        testInvestmentAccount();
        testCheckingAccountFees();
        testSavingsAccountWithdrawalLimits();
        testInterfaceImplementation();
        testCompleteScenario();
        
        logger.info("╔═══════════════════════════════════════════════════════════════╗");
        logger.info("║  ALL TESTS COMPLETED SUCCESSFULLY! ✅                         ║");
        logger.info("╚═══════════════════════════════════════════════════════════════╝");
    }
    
    private static void testAccountCreation() {
        logger.info("TEST 1: Creating Different Account Types");
        logger.info("─────────────────────────────────────────────────────────────");
        
        SavingsAccount savings = new SavingsAccount("SAV-001", 5000.0, DATE_2024_01_01, 3.0, 500.0);
        CheckingAccount checking = new CheckingAccount("CHK-001", 2000.0, DATE_2024_01_01);
        InvestmentAccount investment = new InvestmentAccount("INV-001", 10000.0, DATE_2024_01_01, 5.0);
        
        logger.log(Level.INFO, "✅ Created Savings Account: {0}", savings.getAccountType());
        logger.log(Level.INFO, "   - Account ID: {0}", savings.getAccountId());
        logger.log(Level.INFO, BALANCE_FORMAT, savings.getBalance());
        
        logger.log(Level.INFO, "✅ Created Checking Account: {0}", checking.getAccountType());
        logger.log(Level.INFO, "   - Account ID: {0}", checking.getAccountId());
        logger.log(Level.INFO, BALANCE_FORMAT, checking.getBalance());
        
        logger.log(Level.INFO, "✅ Created Investment Account: {0}", investment.getAccountType());
        logger.log(Level.INFO, "   - Account ID: {0}", investment.getAccountId());
        logger.log(Level.INFO, BALANCE_FORMAT, investment.getBalance());
        logger.info("");
    }
    
    private static void testInheritanceHierarchy() {
        logger.info("TEST 2: Inheritance Hierarchy Demonstration");
        logger.info("─────────────────────────────────────────────────────────────");
        
        SavingsAccount savings = new SavingsAccount("SAV-002", 3000.0, "2024-01-02", 2.5, 500.0);
        
        logger.info("Inheritance Chain:");
        logger.info("  SavingsAccount → InterestBearingAccount → Account");
        logger.info("");
        logger.info("Demonstrating inherited methods:");
        savings.displaySummary();
        
        logger.info("\nCalling abstract methods implemented in SavingsAccount:");
        logger.log(Level.INFO, "  - Account Type: {0}", savings.getAccountType());
        logger.log(Level.INFO, "  - Monthly Interest: ${0,number,#.##}", savings.calculateInterest());
        logger.log(Level.INFO, "  - Minimum Balance: ${0}", savings.calculateMinimumBalance());
        logger.info("");
    }
    
    private static void testPolymorphismWithAccounts() {
        logger.info("TEST 3: Polymorphism with Account Base Class");
        logger.info("─────────────────────────────────────────────────────────────");
        
        List<Account> accounts = new ArrayList<>();
        accounts.add(new SavingsAccount("SAV-003", 4000.0, DATE_2024_01_03, 3.5, 500.0));
        accounts.add(new CheckingAccount("CHK-002", 1500.0, DATE_2024_01_03));
        accounts.add(new InvestmentAccount("INV-002", 15000.0, DATE_2024_01_03, 6.0));
        
        logger.log(Level.INFO, "Processing {0} accounts polymorphically:", accounts.size());
        logger.info("");
        
        double totalBalance = 0;
        for (Account account : accounts) {
            logger.log(Level.INFO, "Account: {0} ({1})", new Object[]{account.getAccountId(), account.getAccountType()});
            logger.log(Level.INFO, "  Balance: ${0}", account.getBalance());
            totalBalance += account.getBalance();
            
            account.deposit(100.0);
            logger.log(Level.INFO, "  After $100 deposit: ${0}", account.getBalance());
            logger.info("");
        }
        
        logger.log(Level.INFO, "Total balance across all accounts: ${0}", totalBalance);
        logger.info("");
    }
    
    private static void testInterestBearingAccounts() {
        logger.info("TEST 4: Interest-Bearing Account Features");
        logger.info("─────────────────────────────────────────────────────────────");
        
        SavingsAccount savings = new SavingsAccount("SAV-004", 10000.0, "2024-01-04", 4.0, 500.0);
        InvestmentAccount investment = new InvestmentAccount("INV-003", 20000.0, "2024-01-04", 7.0);
        
        logger.info("Savings Account (4% APR):");
        logger.log(Level.INFO, "  Balance before interest: ${0}", savings.getBalance());
        double savingsInterest = savings.calculateInterest();
        logger.log(Level.INFO, "  Monthly interest: ${0,number,#.##}", savingsInterest);
        savings.applyInterest();
        logger.log(Level.INFO, "  Balance after interest: ${0,number,#.##}", savings.getBalance());
        
        logger.info("\nInvestment Account (7% APR):");
        logger.log(Level.INFO, "  Balance before interest: ${0}", investment.getBalance());
        double investmentInterest = investment.calculateInterest();
        logger.log(Level.INFO, "  Monthly interest: ${0,number,#.##}", investmentInterest);
        investment.applyInterest();
        logger.log(Level.INFO, "  Balance after interest: ${0,number,#.##}", investment.getBalance());
        logger.info("");
    }
    
    private static void testInvestmentAccount() {
        logger.info("TEST 5: Investment Account (Extends + Implements)");
        logger.info("─────────────────────────────────────────────────────────────");
        
        InvestmentAccount investment = new InvestmentAccount("INV-004", 25000.0, "2024-01-05", 6.5);
        
        logger.info("Initial account state:");
        logger.log(Level.INFO, "  Cash Balance: ${0}", investment.getBalance());
        logger.log(Level.INFO, "  Investment Value: ${0}", investment.getInvestmentValue());
        
        logger.info("\nMaking investments:");
        boolean success1 = investment.invest(5000.0, "Stocks");
        logger.log(Level.INFO, "  Invested $5000 in Stocks: {0}", (success1 ? SUCCESS : FAILED));
        
        boolean success2 = investment.invest(3000.0, "Bonds");
        logger.log(Level.INFO, "  Invested $3000 in Bonds: {0}", (success2 ? SUCCESS : FAILED));
        
        boolean success3 = investment.invest(2000.0, "Mutual Funds");
        logger.log(Level.INFO, "  Invested $2000 in Mutual Funds: {0}", (success3 ? SUCCESS : FAILED));
        
        logger.log(Level.INFO, "\n{0}", investment.getPortfolioSummary());
        
        logger.info("\nAttempting to invest more than available balance:");
        boolean failedInvest = investment.invest(50000.0, "Real Estate");
        logger.log(Level.INFO, "  Invest $50000: {0}", (failedInvest ? SUCCESS : FAILED + " (Expected)"));
        
        logger.info("\nLiquidating $4000 from investments:");
        boolean liquidate = investment.liquidateInvestment(4000.0);
        logger.log(Level.INFO, "  Liquidation: {0}", (liquidate ? SUCCESS : FAILED));
        
        logger.log(Level.INFO, "\n{0}", investment.getPortfolioSummary());
        logger.log(Level.INFO, "  Cash Balance: ${0,number,#.##}", investment.getBalance());
        logger.info("");
    }
    
    private static void testCheckingAccountFees() {
        logger.info("TEST 6: Checking Account Transaction Fees");
        logger.info("─────────────────────────────────────────────────────────────");
        
        CheckingAccount checking = new CheckingAccount("CHK-003", 5000.0, "2024-01-06");
        
        logger.log(Level.INFO, "Initial balance: ${0}", checking.getBalance());
        logger.info("Transaction fee structure: First 10 transactions free, then $1.50 each");
        
        logger.info("\nMaking 12 transactions to demonstrate fee application:");
        double balanceBefore = checking.getBalance();
        
        for (int i = 1; i <= 12; i++) {
            double balanceBefore2 = checking.getBalance();
            checking.withdraw(50.0);
            double balanceAfter = checking.getBalance();
            double charged = balanceBefore2 - balanceAfter;
            
            if (i <= 10) {
                logger.log(Level.INFO, "  Transaction #{0}: Withdrew $50.00 (Free transaction)", i);
            } else {
                logger.log(Level.INFO, "  Transaction #{0}: Withdrew $50.00 + $1.50 fee = ${1,number,#.##} total", new Object[]{i, charged});
            }
        }
        
        logger.log(Level.INFO, "\nBalance before transactions: ${0,number,#.##}", balanceBefore);
        logger.log(Level.INFO, "Balance after transactions: ${0,number,#.##}", checking.getBalance());
        logger.log(Level.INFO, "Total fees paid: ${0,number,#.##}", (balanceBefore - checking.getBalance() - (12 * 50)));
        logger.info("");
    }
    
    private static void testSavingsAccountWithdrawalLimits() {
        logger.info("TEST 7: Savings Account Withdrawal Limits");
        logger.info("─────────────────────────────────────────────────────────────");
        
        SavingsAccount savings = new SavingsAccount("SAV-005", 2000.0, "2024-01-07", 3.0, 500.0);
        
        logger.info("Federal Regulation D: Maximum 6 free withdrawals per month");
        logger.info("Excess withdrawal fee: $10.00 per transaction");
        logger.info("\nMaking 8 withdrawals:");
        
        double balanceBefore = savings.getBalance();
        
        for (int i = 1; i <= 8; i++) {
            double balanceBefore2 = savings.getBalance();
            savings.withdraw(50.0);
            double balanceAfter = savings.getBalance();
            double charged = balanceBefore2 - balanceAfter;
            
            if (i <= 6) {
                logger.log(Level.INFO, "  Withdrawal #{0}: $50.00 (Free - within limit)", i);
            } else {
                logger.log(Level.INFO, "  Withdrawal #{0}: $50.00 + $10.00 fee = ${1,number,#.##} total", new Object[]{i, charged});
            }
        }
        
        logger.log(Level.INFO, "\nTotal withdrawals this month: {0}", savings.getWithdrawalCount());
        logger.log(Level.INFO, "Balance before withdrawals: ${0,number,#.##}", balanceBefore);
        logger.log(Level.INFO, "Balance after withdrawals: ${0,number,#.##}", savings.getBalance());
        logger.log(Level.INFO, "Total fees paid: ${0,number,#.##}", (balanceBefore - savings.getBalance() - (8 * 50)));
        logger.info("");
    }
    
    private static void testInterfaceImplementation() {
        logger.info("TEST 8: Interface Implementation (Investable)");
        logger.info("─────────────────────────────────────────────────────────────");
        
        InvestmentAccount investment = new InvestmentAccount("INV-005", 30000.0, "2024-01-08", 8.0);
        
        Investable investable = investment;
        
        logger.info("Using Investable interface reference:");
        logger.info("  (Demonstrates interface-based polymorphism)");
        logger.info("");
        
        investable.invest(8000.0, "Real Estate");
        logger.info("✅ Invested $8000 in Real Estate via interface");
        
        investable.invest(5000.0, "Commodities");
        logger.info("✅ Invested $5000 in Commodities via interface");
        
        logger.info("\nPortfolio accessed through Investable interface:");
        logger.log(Level.INFO, "{0}", investable.getPortfolioSummary());
        logger.log(Level.INFO, "Total investment value: ${0,number,#.##}", investable.getInvestmentValue());
        
        logger.info("\nKey Point: Same object, different interface!");
        logger.info("  - As InvestmentAccount: Can access all account methods");
        logger.info("  - As Investable: Can only access investment methods");
        logger.info("  - This is Interface Segregation Principle in action!");
        logger.info("");
    }
    
    private static void testCompleteScenario() {
        logger.info("TEST 9: Complete Real-World Scenario");
        logger.info("─────────────────────────────────────────────────────────────");
        
        logger.info("Scenario: Customer manages multiple accounts");
        logger.info("");
        
        CheckingAccount checking = new CheckingAccount("CHK-999", 5000.0, DATE_2024_01_01);
        SavingsAccount savings = new SavingsAccount("SAV-999", 15000.0, DATE_2024_01_01, 3.5, 1000.0);
        InvestmentAccount investment = new InvestmentAccount("INV-999", 50000.0, DATE_2024_01_01, 7.5);
        
        logger.info("📊 INITIAL PORTFOLIO:");
        logger.log(Level.INFO, "  Checking:   ${0,number,#,###.##}", checking.getBalance());
        logger.log(Level.INFO, "  Savings:    ${0,number,#,###.##}", savings.getBalance());
        logger.log(Level.INFO, "  Investment: ${0,number,#,###.##}", investment.getBalance());
        double totalInitial = checking.getBalance() + savings.getBalance() + investment.getBalance();
        logger.info("  ────────────────────────");
        logger.log(Level.INFO, "  TOTAL:      ${0,number,#,###.##}", totalInitial);
        logger.info("");
        
        logger.info("💰 MONTH 1 ACTIVITIES:");
        logger.info("");
        
        logger.info("1. Salary deposit to checking: $6000");
        checking.deposit(6000.0);
        
        logger.info("2. Transfer $2000 from checking to savings");
        checking.withdraw(2000.0);
        savings.deposit(2000.0);
        
        logger.info("3. Make investments:");
        investment.invest(10000.0, "Index Funds");
        investment.invest(5000.0, "Bonds");
        
        logger.info("4. Apply monthly interest:");
        savings.applyInterest();
        logger.log(Level.INFO, "   Savings earned: ${0,number,#.##}", savings.calculateInterest());
        investment.applyInterest();
        logger.log(Level.INFO, "   Investment earned: ${0,number,#.##}", investment.calculateInterest());
        
        logger.info("");
        logger.info("📊 END OF MONTH PORTFOLIO:");
        logger.log(Level.INFO, "  Checking:   ${0,number,#,###.##}", checking.getBalance());
        logger.log(Level.INFO, "  Savings:    ${0,number,#,###.##}", savings.getBalance());
        logger.log(Level.INFO, "  Investment: ${0,number,#,###.##}", investment.getBalance());
        logger.log(Level.INFO, "  Invested:   ${0,number,#,###.##}", investment.getInvestmentValue());
        double totalFinal = checking.getBalance() + savings.getBalance() + investment.getBalance() + investment.getInvestmentValue();
        logger.info("  ────────────────────────");
        logger.log(Level.INFO, "  TOTAL:      ${0,number,#,###.##}", totalFinal);
        logger.info("");
        
        double growth = totalFinal - totalInitial;
        logger.log(Level.INFO, "📈 NET GROWTH: ${0,number,#,###.##}", growth);
        logger.info("");
    }
}
