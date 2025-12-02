public class RentalRecord {
    private String licensePlate;
    private String customerName;
    private String action;

    public RentalRecord(String licensePlate, String customerName, String action) {
        this.licensePlate = licensePlate;
        this.customerName = customerName;
        this.action = action;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAction() {
        return action;
    }
}
