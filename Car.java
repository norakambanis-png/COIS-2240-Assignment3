public class Car extends Vehicle implements Rentable {

    public Car(String make, String model, int year) {
        super(make, model, year);
    }

    

    
    

    @Override
    public void rentVehicle() {
        setStatus(VehicleStatus.Rented);
        System.out.println("Car " + getLicensePlate() + " has been rented.");
    }

    @Override
    public void returnVehicle() {
        setStatus(VehicleStatus.Available);
        System.out.println("Car " + getLicensePlate() + " has been returned.");
    }
}