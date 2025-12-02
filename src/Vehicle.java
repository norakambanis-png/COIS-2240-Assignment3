public abstract class Vehicle {
    public static final String Status = null;
	private String licensePlate;
    private String make;
    private String model;
    private int year;
    private VehicleStatus status;

    public enum VehicleStatus { Available, Held, Rented, UnderMaintenance, OutOfService }

    public Vehicle(String make, String model, int year) {
        this.make = capitalize(make);     
        this.model = capitalize(model);   

    	
    	if (make == null || make.isEmpty())
    		this.make = null;
    	else
    		this.make = make.substring(0, 1).toUpperCase() + make.substring(1).toLowerCase();
    	
    	if (model == null || model.isEmpty())
    		this.model = null;
    	else
    		this.model = model.substring(0, 1).toUpperCase() + model.substring(1).toLowerCase();
    	
        this.year = year;
        this.status = VehicleStatus.Available;
        this.licensePlate = null;
    }

    public Vehicle() {
        this(null, null, 0);
    }

    public void setLicensePlate(String plate) {
        if (!isValidPlate(plate)) {
            throw new IllegalArgumentException("Invalid license plate format.");
        }
        this.licensePlate = plate;
    }


    public void setStatus(VehicleStatus status) {
    	this.status = status;
    }
    
    private String capitalize(String input) {
        if (input == null || input.isEmpty())
            return null;
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }


    public String getLicensePlate() { return licensePlate; }

    public String getMake() { return make; }

    public String getModel() { return model;}

    public int getYear() { return year; }

    public VehicleStatus getStatus() { return status; }

    public String getInfo() {
        return "| " + licensePlate + " | " + make + " | " + model + " | " + year + " | " + status + " |";
    }
    
    private boolean isValidPlate(String plate) {
        if (plate == null || plate.isEmpty()) {
            return false;
        }

        return plate.matches("[A-Z]{3}[0-9]{3}");
    }


}
