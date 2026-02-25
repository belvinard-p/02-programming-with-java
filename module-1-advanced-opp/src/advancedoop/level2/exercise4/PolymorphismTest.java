package advancedoop.level2.exercise4;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PolymorphismTest {
    private static final Logger logger = Logger.getLogger(PolymorphismTest.class.getName());

    public static void main(String[] args) {
        logger.info("=== POLYMORPHISM IN BANKING OPERATIONS TEST ===");
        logger.info("");
        
        testSavingsProduct();
        testPolymorphicBehavior();
        testStatementGeneration();
        testLoanProduct();
        testCreditCardProduct();
        testInvestmentProduct();
        testTransactionProcessor();
        
        logger.info("");
        logger.info("=== ALL TESTS COMPLETED ===");
    }
    
    private static void testSavingsProduct() {
        logger.info("TEST 1: Savings Product Operations");
        logger.info("-----------------------------------");
        
        SavingsProduct savings = new SavingsProduct("SAV-001", "Premium Savings", "John Doe", 5000.0);
        savings.getProductInfo();
        
        logger.info("\nProcessing transactions:");
        savings.processTransaction("DEPOSIT", 1000.0);
        savings.processTransaction("WITHDRAWAL", 500.0);
        savings.processTransaction("WITHDRAWAL", 200.0);
        
        logger.log(Level.INFO, "\nGenerated Statement:{0}", savings.generateStatement());
        logger.info("");
    }
    
    private static void testPolymorphicBehavior() {
        logger.info("TEST 2: Polymorphic Behavior with Parent Reference");
        logger.info("----------------------------------------------------");
        
        BankingProduct product = new SavingsProduct("SAV-002", "Standard Savings", "Jane Smith", 3000.0);
        
        logger.info("Using BankingProduct reference for SavingsProduct:");
        product.getProductInfo();
        
        double newBalance = product.processTransaction("DEPOSIT", 500.0);
        logger.log(Level.INFO, "Balance after deposit: ${0}", newBalance);
        
        newBalance = product.processTransaction("WITHDRAWAL", 200.0);
        logger.log(Level.INFO, "Balance after withdrawal: ${0}", newBalance);
        
        logger.info("\nPolymorphic method call - generateStatement():");
        logger.log(Level.INFO, "{0}", product.generateStatement());
        logger.info("");
    }
    
    private static void testStatementGeneration() {
        logger.info("TEST 3: Statement Generation for Multiple Products");
        logger.info("---------------------------------------------------");
        
        BankingProduct[] products = new BankingProduct[3];
        products[0] = new SavingsProduct("SAV-003", "High Yield Savings", "Alice Brown", 10000.0);
        products[1] = new SavingsProduct("SAV-004", "Student Savings", "Bob Wilson", 2000.0);
        products[2] = new SavingsProduct("SAV-005", "Emergency Fund", "Carol Davis", 15000.0);
        
        logger.info("Processing transactions for all products:");
        for (BankingProduct product : products) {
            product.processTransaction("DEPOSIT", 500.0);
        }
        
        logger.info("\nGenerating statements for all products:");
        for (BankingProduct product : products) {
            logger.log(Level.INFO, "{0}", product.generateStatement());
        }
        logger.info("");
    }
    
    private static void testLoanProduct() {
        logger.info("TEST 4: Loan Product Operations");
        logger.info("---------------------------------");
        
        LoanProduct loan = new LoanProduct("LOAN-001", "Personal Loan", "Mike Johnson", 10000.0, 5.5, 36);
        loan.getProductInfo();
        
        logger.info("\nProcessing loan transactions:");
        loan.processTransaction("PAYMENT", 500.0);
        loan.processTransaction("PAYMENT", 300.0);
        
        logger.log(Level.INFO, "\nGenerated Statement:{0}", loan.generateStatement());
        logger.info("");
    }
    
    private static void testCreditCardProduct() {
        logger.info("TEST 5: Credit Card Product Operations");
        logger.info("---------------------------------------");
        
        CreditCardProduct creditCard = new CreditCardProduct("CC-001", "Platinum Card", "Sarah Lee", 5000.0);
        creditCard.getProductInfo();
        
        logger.info("\nProcessing credit card transactions:");
        creditCard.processTransaction("PURCHASE", 150.0);
        creditCard.processTransaction("PURCHASE", 75.50);
        creditCard.processTransaction("PAYMENT", 100.0);
        
        logger.log(Level.INFO, "\nGenerated Statement:{0}", creditCard.generateStatement());
        logger.info("");
    }
    
    private static void testInvestmentProduct() {
        logger.info("TEST 6: Investment Product Operations");
        logger.info("--------------------------------------");
        
        InvestmentProduct investment = new InvestmentProduct("INV-001", "Growth Portfolio", "Tom Harris", 20000.0);
        investment.getProductInfo();
        
        logger.info("\nProcessing investment transactions:");
        investment.processTransaction("BUY", 5000.0);
        investment.processTransaction("BUY", 3000.0);
        investment.processTransaction("SELL", 2000.0);
        
        logger.log(Level.INFO, "\nGenerated Statement:{0}", investment.generateStatement());
        logger.info("");
    }
    
    private static void testTransactionProcessor() {
        logger.info("TEST 7: Transaction Processor - Polymorphic Processing");
        logger.info("-------------------------------------------------------");
        
        BankingProduct[] products = new BankingProduct[4];
        products[0] = new SavingsProduct("SAV-100", "Basic Savings", "Customer A", 5000.0);
        products[1] = new LoanProduct("LOAN-100", "Auto Loan", "Customer B", 15000.0, 4.5, 60);
        products[2] = new CreditCardProduct("CC-100", "Gold Card", "Customer C", 3000.0);
        products[3] = new InvestmentProduct("INV-100", "Balanced Fund", "Customer D", 10000.0);
        
        TransactionProcessor processor = new TransactionProcessor();
        
        logger.info("\nProcessing deposits for all products:");
        processor.processMultipleProducts(products, "DEPOSIT", 1000.0);
        
        logger.info("\nGenerating all statements:");
        processor.generateAllStatements(products);
        logger.info("");
    }
}
