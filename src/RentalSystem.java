
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.FileWriter;
import java.io.File;
import java.util.Scanner;

public class RentalSystem {

    private static RentalSystem instance;

    private List<Vehicle> vehicles = new ArrayList<>();
    private Map<String, String> rentalRecords = new HashMap<>();
    private List<RentalRecord> rentalHistory = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();

    private RentalSystem() {
        loadData();
    }

    public static RentalSystem getInstance() {
        if (instance == null) {
            instance = new RentalSystem();
        }
        return instance;
    }

    public boolean returnVehicle(String licensePlate) {
        if (licensePlate == null) return false;

        String key = licensePlate.toUpperCase();
        String customerName = rentalRecords.get(key);

        for (Vehicle v : vehicles) {
            if (v.getLicensePlate().equalsIgnoreCase(licensePlate)
                    && v.getStatus() == Vehicle.VehicleStatus.Rented) {

                v.setStatus(Vehicle.VehicleStatus.Available);
                rentalRecords.remove(key);

                RentalRecord record = new RentalRecord(key, customerName, "RETURN");
                rentalHistory.add(record);
                saveRecord(record);

                return true;
            }
        }
        return false;
    }

    public boolean addVehicle(Vehicle vehicle) {
        if (vehicle == null) return false;

        for (Vehicle v : vehicles) {
            if (v.getLicensePlate() != null
                    && v.getLicensePlate().equalsIgnoreCase(vehicle.getLicensePlate())) {
                System.out.println("Vehicle with plate " + vehicle.getLicensePlate() + " already exists.");
                return false;
            }
        }

        vehicles.add(vehicle);
        saveVehicle(vehicle);
        return true;
    }

        
    

public boolean addCustomer(Customer customer) {
    if (customer == null) return false;

    for (Customer c : customers) {
        if (c.getCustomerId() == customer.getCustomerId()) {
            System.out.println("Customer with ID " + customer.getCustomerId() + " already exists.");
            return false;
        }
    }

    customers.add(customer);
    saveCustomer(customer);
    return true;
}

    

    public boolean rentVehicle(String licensePlate, String customerName) {
        if (licensePlate == null || customerName == null) return false;
        if (customerName.isEmpty()) return false;

        for (Vehicle v : vehicles) {
            if (v.getLicensePlate().equalsIgnoreCase(licensePlate)
                    && v.getStatus() == Vehicle.VehicleStatus.Available) {

                v.setStatus(Vehicle.VehicleStatus.Rented);
                String name = Character.toUpperCase(customerName.charAt(0))
                        + customerName.substring(1).toLowerCase();
                rentalRecords.put(licensePlate.toUpperCase(), name);

                RentalRecord record = new RentalRecord(licensePlate.toUpperCase(), name, "RENT");
                rentalHistory.add(record);
                saveRecord(record);

                return true;
            }
        }
        return false;
    }

    public void displayAvailableVehicles() {
        System.out.println("| Type | Plate | Make | Model | Year |");
        System.out.println("--------------------------------------");
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getStatus() == Vehicle.VehicleStatus.Available) {
                String type = vehicle.getClass().getSimpleName().toUpperCase();
                System.out.println("| " + type + " | "
                        + vehicle.getLicensePlate() + " | "
                        + vehicle.getMake() + " | "
                        + vehicle.getModel() + " | "
                        + vehicle.getYear() + " |");
            }
        }
    }

    private void saveVehicle(Vehicle v) {
        try {
            FileWriter fw = new FileWriter("vehicles.txt", true);
            fw.write(v.getLicensePlate() + "," + v.getMake() + "," +
                    v.getModel() + "," + v.getYear() + "\n");
            fw.close();
        } catch (Exception e) {
            System.out.println("Could not save vehicle.");
        }
    }

    private void saveCustomer(Customer c) {
        try {
            FileWriter fw = new FileWriter("customers.txt", true);
            fw.write(c.getCustomerId() + "," + c.getCustomerName() + "\n");

            fw.close();
        } catch (Exception e) {
            System.out.println("Could not save customer.");
        }
    }

    private void saveRecord(RentalRecord r) {
        try {
            FileWriter fw = new FileWriter("rental_records.txt", true);
            fw.write(r.getLicensePlate() + "," + r.getCustomerName() + "," +
                    r.getAction() + "\n");
            fw.close();
        } catch (Exception e) {
            System.out.println("Could not save record.");
        }
    }


    private void loadData() {
        loadVehiclesFromFile();
        loadCustomersFromFile();
        loadRecordsFromFile();
    }

    private void loadVehiclesFromFile() {
        try {
            File file = new File("vehicles.txt");
            if (!file.exists()) return;

            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                sc.nextLine(); 
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Could not load vehicles.");
        }
    }

    private void loadCustomersFromFile() {
        try {
            File file = new File("customers.txt");
            if (!file.exists()) return;

            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                sc.nextLine();
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Could not load customers.");
        }
    }

    private void loadRecordsFromFile() {
        try {
            File file = new File("rental_records.txt");
            if (!file.exists()) return;

            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                sc.nextLine();
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Could not load rental records.");
        }
    }
    
    public List<RentalRecord> getRentalHistory() {
        return rentalHistory;
    }

}
