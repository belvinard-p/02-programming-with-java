package advancedoop.level1.exercise2;

import java.util.logging.Logger;

public class BusinessCustomer extends Customer{
    private static Logger
            logger = Logger.getLogger(BusinessCustomer.class.getName());

    private String taxId;
    private String businessName;

    public BusinessCustomer(String customerId, String name, String email, String registrationDate,
                            String taxId, String businessName) {
        super(customerId, name, email, registrationDate);
        this.taxId = taxId;
        this.businessName = businessName;
    }

    @Override
    public boolean authenticate() {
        if (taxId != null && !taxId.isEmpty()){
            logger.info("Business customer authenticated successfully.");
            return true;
        }
        logger.warning("Authentication failed: Tax ID is empty or null.");
        return false;
    }

    @Override
    public String getServiceLevel() {
        return "BUSINESS";
    }
}
