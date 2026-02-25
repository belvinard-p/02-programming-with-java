package advancedoop.level2.exercise4;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BankingProduct {
    private static Logger logger = Logger.getLogger(BankingProduct.class.getName());
    private String productId;
    private String productName;
    private String customerName;

    public BankingProduct(String productId, String productName, String customerName) {
        this.productId = productId;
        this.productName = productName;
        this.customerName = customerName;
    }

    abstract double processTransaction(String transactionType, double amount);
    abstract String generateStatement();

    public void getProductInfo(){
        logger.log(Level.INFO, "Product ID : {0}, Product Name : {1}, Customer Name : {2}",
                new Object[]{productId, productName, customerName});

    }
}
