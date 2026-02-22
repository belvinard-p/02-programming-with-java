package advancedoop.level1.exercise2;

import java.util.logging.Logger;

public class PremiumCustomer extends Customer{
    private static Logger logger
            = Logger.getLogger(PremiumCustomer.class.getName());

    private String membershipLevel;
    private String dedicatedBankerId;

    public PremiumCustomer(String customerId, String name, String email, String membershipLevel,
                           String dedicatedBankerId, String registrationDate) {
        super(customerId,name, email, registrationDate);
        this.membershipLevel = membershipLevel;
        this.dedicatedBankerId = dedicatedBankerId;
        logger.info("Premium customer created");
    }

    @Override
    public boolean authenticate() {

        return true;
    }

    @Override
    public String getServiceLevel() {
        return "PREMIUM";
    }
}
