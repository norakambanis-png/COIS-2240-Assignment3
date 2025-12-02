public class Car extends Vehicle implements Rentable {

    public Car(String make, String model, int year) {
        super(make, model, year);
    }

    @Override
    public void rentVehicle() {
        setStatus(Vehicle.VehicleStatus.Rented);
        System.out.println("Car " + getLicensePlate() + " has been rented.");
    }

    @Override
    public void returnVehicle() {
        setStatus(Vehicle.VehicleStatus.Available);
        System.out.println("Car " + getLicensePlate() + " has been returned.");
    }
}
