package level4.service;

import level4.exception.AuthenticationFailedException;
import level4.exception.InvalidAccountException;
import level4.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerManager {
    private static final Logger LOGGER = Logger.getLogger(CustomerManager.class.getName());
    private final List<Customer> customers = new ArrayList<>();
    private int customerCounter = 0;

    public Customer createCustomer(String type, String name, String email, String phone, String password) {
        customerCounter++;
        String customerId = String.format("CUS%03d", customerCounter);
        Customer customer = buildCustomer(type, customerId, name, email, phone, password);
        customers.add(customer);
        LOGGER.log(Level.INFO, "Customer created: {0}", customer);
        return customer;
    }

    private Customer buildCustomer(String type, String customerId, String name, String email,
                                   String phone, String password) {
        switch (type.toUpperCase()) {
            case "BUSINESS":
                return new BusinessCustomer(customerId, name, email, phone, password, name + " Corp");
            case "PREMIUM":
                return new PremiumCustomer(customerId, name, email, phone, password);
            default:
                return new IndividualCustomer(customerId, name, email, phone, password);
        }
    }

    public Customer getCustomer(String customerId) throws InvalidAccountException {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        throw new InvalidAccountException(customerId);
    }

    public boolean authenticateCustomer(String customerId, String credential) throws AuthenticationFailedException {
        try {
            Customer customer = getCustomer(customerId);
            if (customer.authenticate(credential)) {
                LOGGER.log(Level.INFO, "Customer authenticated: {0}", customerId);
                return true;
            }
            throw new AuthenticationFailedException(customerId);
        } catch (InvalidAccountException e) {
            throw new AuthenticationFailedException(customerId);
        }
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }

    public int getCustomerCount() { return customers.size(); }
}
