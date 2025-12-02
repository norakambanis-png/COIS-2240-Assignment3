public class Minibus extends Vehicle implements Rentable {

    public Minibus(String make, String model, int year) {
        super(make, model, year);
    }

    

    @Override
    public void rentVehicle() {
        setStatus(VehicleStatus.Rented);
        System.out.println("Minibus " + getLicensePlate() + " has been rented.");
    }

    @Override
    public void returnVehicle() {
        setStatus(VehicleStatus.Available);
        System.out.println("Minibus " + getLicensePlate() + " has been returned.");
    }
}
