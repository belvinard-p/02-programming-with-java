package advancedoop.level1.exercise2;

import java.util.logging.Level;
import java.util.logging.Logger;


public class CustomerManagementTest {
    private static final Logger logger = Logger.getLogger(CustomerManagementTest.class.getName());
    
    public static void main(String[] args) {
        printHeader("Banking System - Customer Management Test");
        printSubHeader("Exercise 1.2: Customer Account Abstraction");
        
        // Test 1: Create and Display Individual Customers
        printTestSection("TEST 1: Creating Individual Customers");
        IndividualCustomer toto = new IndividualCustomer(
            "IND001", 
            "toto Doe", 
            "toto@example.com", 
            "2026-01-01", 
            "123-45-6789"
        );
        
        IndividualCustomer sarah = new IndividualCustomer(
            "IND002", 
            "Sarah totoson", 
            "sarah.j@email.com", 
            "2026-02-15", 
            "987-65-4321"
        );
        
        logger.log(Level.INFO, "Created Individual Customer: {0}", toto);
        toto.displayCustomerInfo();
        logger.log(Level.INFO, "Created Individual Customer: {0}", sarah);
        sarah.displayCustomerInfo();
        
        // Test 2: Create and Display Business Customers
        printTestSection("TEST 2: Creating Business Customers");
        BusinessCustomer techCorp = new BusinessCustomer(
            "BUS001",
            "TechCorp Solutions",
            "contact@techcorp.com",
            "2026-03-01",
            "TAX-2026-TC001",
            "TechCorp Solutions LLC"
        );
        
        BusinessCustomer retailCo = new BusinessCustomer(
            "BUS002",
            "Retail Innovations",
            "info@retailinnovations.com",
            "2026-04-10",
            "TAX-2026-RI002",
            "Retail Innovations Inc"
        );
        
        logger.log(Level.INFO, "Created Business Customer: {0}", techCorp);
        techCorp.displayCustomerInfo();
        logger.log(Level.INFO, "Created Business Customer: {0}", retailCo);
        retailCo.displayCustomerInfo();
        
        // Test 3: Authentication Tests
        printTestSection("TEST 3: Authentication Mechanisms");
        logger.info("--- Individual Customer Authentication (SSN-based) ---");
        boolean totoAuth = toto.authenticate();
        logger.log(Level.INFO, "toto authentication result: {0}", totoAuth);
        
        boolean sarahAuth = sarah.authenticate();
        logger.log(Level.INFO, "Sarah authentication result: {0}", sarahAuth);
        
        logger.info("--- Business Customer Authentication (Tax ID-based) ---");
        boolean techAuth = techCorp.authenticate();
        logger.log(Level.INFO, "TechCorp authentication result: {0}", techAuth);
        
        boolean retailAuth = retailCo.authenticate();
        logger.log(Level.INFO, "RetailCo authentication result: {0}", retailAuth);
        
        // Test 4: Service Level Demonstration
        printTestSection("TEST 4: Service Level Differentiation");
        logger.log(Level.INFO, "toto (Individual) - Service Level: {0}", toto.getServiceLevel());
        logger.log(Level.INFO, "Sarah (Individual) - Service Level: {0}", sarah.getServiceLevel());
        logger.log(Level.INFO, "TechCorp (Business) - Service Level: {0}", techCorp.getServiceLevel());
        logger.log(Level.INFO, "RetailCo (Business) - Service Level: {0}", retailCo.getServiceLevel());
        
        // Test 5: Polymorphism - Array of Customers
        printTestSection("TEST 5: Polymorphism - Processing Mixed Customers");
        Customer[] customers = {toto, sarah, techCorp, retailCo};
        
        logger.info("Processing all customers polymorphically:");
        for (int i = 0; i < customers.length; i++) {
            Customer customer = customers[i];
            logger.log(Level.INFO, "\n[Customer {0}]", (i + 1));
            logger.log(Level.INFO, "  Type: {0}", customer.getClass().getSimpleName());
            logger.log(Level.INFO, "  Service Level: {0}", customer.getServiceLevel());
            logger.log(Level.INFO, "  Authentication Status: {0}", customer.authenticate());
            customer.displayCustomerInfo();
        }
        
        // Test 6: Update Operations
        printTestSection("TEST 6: Customer Update Operations");
        logger.info("Updating toto's email address:");
        String oldEmail = "toto@example.com";
        String newEmail = toto.updateEmail("toto.doe.updated@email.com");
        logger.log(Level.INFO, "Email changed from {0} to {1}", new Object[]{oldEmail, newEmail});
        toto.displayCustomerInfo();
        
        logger.info("\nUpdating TechCorp's email address:");
        String oldBusinessEmail = "contact@techcorp.com";
        String newBusinessEmail = techCorp.updateEmail("newcontact@techcorp.com");
        logger.log(Level.INFO, "Email changed from {0} to {1}", new Object[]{oldBusinessEmail, newBusinessEmail});
        techCorp.displayCustomerInfo();
        
        // Test 7: Authentication with Invalid Data
        printTestSection("TEST 7: Testing Authentication Edge Cases");
        logger.info("Creating customer with null SSN:");
        IndividualCustomer invalidCustomer = new IndividualCustomer(
            "IND003", 
            "Invalid User", 
            "invalid@example.com", 
            "2026-05-01", 
            null
        );
        boolean invalidAuth = invalidCustomer.authenticate();
        logger.log(Level.INFO, "Invalid customer authentication result: {0}", invalidAuth);
        
        logger.info("\nCreating customer with empty SSN:");
        IndividualCustomer emptyCustomer = new IndividualCustomer(
            "IND004", 
            "Empty User", 
            "empty@example.com", 
            "2026-05-02", 
            ""
        );
        boolean emptyAuth = emptyCustomer.authenticate();
        logger.log(Level.INFO, "Empty customer authentication result: {0}", emptyAuth);
        
        // Test 8: Service Level Statistics
        printTestSection("TEST 8: Service Level Statistics");
        displayServiceLevelStats(customers);
        
        // Test 9: Demonstrating Abstract Class Concept
        printTestSection("TEST 9: Abstract Class Demonstration");
        logger.info("Key Points:");
        logger.info("1. Customer is an abstract class - cannot be instantiated directly");
        logger.info("2. authenticate() and getServiceLevel() are abstract methods");
        logger.info("3. Each subclass provides its own implementation");
        logger.info("4. displayCustomerInfo() and updateEmail() are concrete methods inherited by all");
        logger.info("5. Polymorphism allows treating all customers uniformly");
        
        printFooter("All Tests Completed Successfully!");
    }
    
    /**
     * Display service level statistics for an array of customers
     */
    private static void displayServiceLevelStats(Customer[] customers) {
        int standardCount = 0;
        int businessCount = 0;
        int otherCount = 0;
        
        for (Customer customer : customers) {
            String level = customer.getServiceLevel();
            switch (level) {
                case "STANDARD":
                    standardCount++;
                    break;
                case "BUSINESS":
                    businessCount++;
                    break;
                default:
                    otherCount++;
                    break;
            }
        }
        
        logger.log(Level.INFO, "Total Customers: {0}", customers.length);
        logger.log(Level.INFO, "  STANDARD Service: {0}", standardCount);
        logger.log(Level.INFO, "  BUSINESS Service: {0}", businessCount);
        logger.log(Level.INFO, "  Other Service Levels: {0}", otherCount);
    }
    
    /**
     * Print a formatted header
     */
    private static void printHeader(String title) {
        String border = "=".repeat(60);
        logger.info(border);
        logger.log(Level.INFO, "  {0}", title);
        logger.info(border);
    }
    
    /**
     * Print a formatted sub-header
     */
    private static void printSubHeader(String title) {
        logger.log(Level.INFO, "  >>> {0} <<<\n", title);
    }
    
    /**
     * Print a test section separator
     */
    private static void printTestSection(String sectionName) {
        String separator = "-".repeat(60);
        logger.info("\n" + separator);
        logger.info(sectionName);
        logger.info(separator);
    }
    
    /**
     * Print a formatted footer
     */
    private static void printFooter(String message) {
        String border = "=".repeat(60);
        logger.info("\n" + border);
        logger.log(Level.INFO, "  {0}", message);
        logger.info(border);
    }
}
