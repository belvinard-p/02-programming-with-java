package advancedoop.level1.exercise2;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Customer {
    private static Logger
            logger = Logger.getLogger(Customer.class.getName());

    private String customerId;
    private String name;
    private String email;
    private String registrationDate;

    protected Customer(String customerId, String name, String email, String registrationDate) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    public abstract boolean authenticate();
    public abstract String getServiceLevel();

    public void displayCustomerInfo() {
        logger.log(Level.INFO, "Customer ID: {0}, Name: {1}, Email: {2}, Registration Date: {3}",
                new Object[]{customerId, name, email, registrationDate});
    }

    public String updateEmail(String newEmail) {
        this.email = newEmail;

        logger.log(Level.INFO, "Email updated successfully for customer {0}", name);

        return email;

    }
}
