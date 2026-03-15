package level4.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Customer {
    private static final Logger LOGGER = Logger.getLogger(Customer.class.getName());

    protected String customerId;
    protected String name;
    protected String email;
    protected String phone;
    protected List<Account> accounts;

    protected Customer(String customerId, String name, String email, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.accounts = new ArrayList<>();
    }

    public abstract boolean authenticate(String credential);

    public abstract String getServiceLevel();

    public void addAccount(Account account) {
        accounts.add(account);
        LOGGER.log(Level.INFO, "Account {0} added to customer {1}", new Object[]{account.getAccountId(), customerId});
    }

    public Account[] getAccounts() {
        return accounts.toArray(new Account[0]);
    }

    public String toFileFormat() {
        return customerId + "|" + name + "|" + email + "|" + phone + "|" + getServiceLevel();
    }

    @Override
    public String toString() {
        return "[" + getServiceLevel() + "] " + customerId + " - " + name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
