public class PickupTruck extends Vehicle implements Rentable {

    public PickupTruck(String make, String model, int year) {
        super(make, model, year);
    }

    

    @Override
    public void rentVehicle() {
        setStatus(VehicleStatus.Rented);
        System.out.println("Pickup Truck " + getLicensePlate() + " has been rented.");
    }

    @Override
    public void returnVehicle() {
        setStatus(VehicleStatus.Available);
        System.out.println("Pickup Truck " + getLicensePlate() + " has been returned.");
    }
}