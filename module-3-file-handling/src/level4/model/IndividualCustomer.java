package level4.model;

public class IndividualCustomer extends Customer {
    private final String password;

    public IndividualCustomer(String customerId, String name, String email, String phone, String password) {
        super(customerId, name, email, phone);
        this.password = password;
    }

    @Override
    public boolean authenticate(String credential) {
        return password.equals(credential);
    }

    @Override
    public String getServiceLevel() {
        return "STANDARD";
    }
}
