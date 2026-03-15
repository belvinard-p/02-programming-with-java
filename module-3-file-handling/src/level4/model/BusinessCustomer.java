package level4.model;

public class BusinessCustomer extends Customer {
    private final String password;
    private final String businessName;

    public BusinessCustomer(String customerId, String name, String email, String phone, String password, String businessName) {
        super(customerId, name, email, phone);
        this.password = password;
        this.businessName = businessName;
    }

    @Override
    public boolean authenticate(String credential) {
        return password.equals(credential);
    }

    @Override
    public String getServiceLevel() {
        return "BUSINESS";
    }

    public String getBusinessName() {
        return businessName;
    }
}
