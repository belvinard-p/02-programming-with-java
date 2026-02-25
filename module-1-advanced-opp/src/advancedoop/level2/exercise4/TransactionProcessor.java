package advancedoop.level2.exercise4;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionProcessor {
    private static final Logger logger = Logger.getLogger(TransactionProcessor.class.getName());
    
    public void processMultipleProducts(BankingProduct[] products, String transactionType, double amount) {
        logger.log(Level.INFO, "Processing {0} transaction of ${1} for {2} products", 
                  new Object[]{transactionType, amount, products.length});
        
        for (BankingProduct product : products) {
            product.processTransaction(transactionType, amount);
        }
        
        logger.info("All transactions processed");
    }
    
    public void generateAllStatements(BankingProduct[] products) {
        logger.log(Level.INFO, "Generating statements for {0} products:", products.length);
        
        for (BankingProduct product : products) {
            logger.log(Level.INFO, "{0}", product.generateStatement());
        }
    }
}
