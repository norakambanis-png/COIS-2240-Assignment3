import java.util.Scanner;

public class VehicleRentalApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RentalSystem rentalSystem = RentalSystem.getInstance();

        while (true) {
            System.out.println("\n1: Add Vehicle\n" + 
                               "2: Add Customer\n" + 
                               "3: Rent Vehicle\n" + 
                               "4: Return Vehicle\n" + 
                               "5: Display Available Vehicles\n" + 
                               "6: Show Rental History\n" + 
                               "0: Exit\n");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
            case 1:
                System.out.println("  1: Car\n" + 
                                   "  2: Minibus\n" + 
                                   "  3: Pickup Truck");
                int type = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter license plate: ");
                String plate = scanner.nextLine().toUpperCase();
                System.out.print("Enter make: ");
                String make = scanner.nextLine();
                System.out.print("Enter model: ");
                String model = scanner.nextLine();
                System.out.print("Enter year: ");
                int year = scanner.nextInt();
                scanner.nextLine();

                Vehicle vehicle;
                if (type == 1) {
                    vehicle = new Car(make, model, year);
                } 
                else if (type == 2) {
                    vehicle = new Minibus(make, model, year);
                } 
                else if (type == 3) {
                    vehicle = new PickupTruck(make, model, year);
                } 
                else {
                    vehicle = null;
                }

                if (vehicle != null){
                    vehicle.setLicensePlate(plate);
                    rentalSystem.addVehicle(vehicle);
                    System.out.println("Vehicle added successfully.");
                } else {
                    System.out.println("Vehicle not added successfully.");
                }
                break;
                

                case 2:
                    System.out.print("Enter customer ID: ");
                    int cid = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter name: ");
                    String cname = scanner.nextLine();

                    rentalSystem.addCustomer(new Customer(cid, cname));
                    System.out.println("Customer added successfully.");
                    break;
                    
                case 3:
                    rentalSystem.displayAvailableVehicles();

                    System.out.print("Enter license plate: ");
                    String rentPlate = scanner.nextLine().toUpperCase();

                    System.out.print("Enter customer name: ");
                    String customerName = scanner.nextLine();

                    boolean success = rentalSystem.rentVehicle(rentPlate, customerName);
                    if (!success) {
                        System.out.println("Could not rent vehicle.");
                    }
                    break;

                case 4:
                    System.out.print("Enter license plate: ");
                    String returnPlate = scanner.nextLine().toUpperCase();

                    boolean returned = rentalSystem.returnVehicle(returnPlate);
                    if (!returned) {
                        System.out.println("Could not return vehicle.");
                    }
                    break;
                    
                case 5:
                    rentalSystem.displayAvailableVehicles();
                    break;
                
                case 6:
                    System.out.println("Rental History:");
                    for (RentalRecord record : rentalSystem.getRentalHistory()) {
                        System.out.println(record.getLicensePlate() + " - " +
                                           record.getCustomerName() + " - " +
                                           record.getAction());
                    }
                    break;
                    
                case 0:
                    scanner.close();
                    System.exit(0);
            }
        }
    }
}
