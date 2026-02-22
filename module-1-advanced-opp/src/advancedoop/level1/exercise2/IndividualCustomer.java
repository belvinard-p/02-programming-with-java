package advancedoop.level1.exercise2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class IndividualCustomer extends Customer{
    private static final Logger logger = Logger.getLogger(IndividualCustomer.class.getName());

    private String ssn;

    public IndividualCustomer(String customerId, String name, String email,
                              String registrationDate, String ssn) {
        super(customerId, name, email, registrationDate);
        this.ssn = ssn;
    }

    @Override
    public boolean authenticate() {
        if (ssn != null && !ssn.isEmpty()){
            logger.log(Level.INFO, "Authentication successful");
            return true;
        }
        logger.log(Level.WARNING, "Authentication failed: SSN is empty or null");
        return false;
    }

    @Override
    public String getServiceLevel() {
        return "STANDARD";
    }
}
