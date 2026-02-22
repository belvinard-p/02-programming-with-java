package advancedoop.level1.exercise3;

/**
 * Interface defining transactional operations for banking services
 */
public interface Transactable {
    /**
     * Process a transaction
     * @param amount The transaction amount
     * @param transactionType The type of transaction (DEPOSIT, WITHDRAWAL, TRANSFER)
     * @return true if transaction successful, false otherwise
     */
    boolean processTransaction(double amount, String transactionType);
    
    /**
     * Get transaction history
     * @return String representation of transaction history
     */
    String getTransactionHistory();
}
