import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

public class VehicleRentalTest {

    @Test
    void testLicensePlate() {

        Car car1 = new Car("Toyota", "Camry", 2020);
        assertDoesNotThrow(() -> car1.setLicensePlate("AAA100"));

        Car car2 = new Car("Honda", "Civic", 2018);
        assertDoesNotThrow(() -> car2.setLicensePlate("ABC567"));

        Car car3 = new Car("Ford", "Focus", 2019);
        assertDoesNotThrow(() -> car3.setLicensePlate("ZZZ999"));

        Car wrongCar1 = new Car("Toyota", "Corolla", 2020);
        assertThrows(IllegalArgumentException.class,
                () -> wrongCar1.setLicensePlate(""));

        Car wrongCar2 = new Car("Toyota", "Corolla", 2020);
        assertThrows(IllegalArgumentException.class,
                () -> wrongCar2.setLicensePlate(null));

        Car wrongCar3 = new Car("Toyota", "Corolla", 2020);
        assertThrows(IllegalArgumentException.class,
                () -> wrongCar3.setLicensePlate("AAA1000"));

        Car wrongCar4 = new Car("Toyota", "Corolla", 2020);
        assertThrows(IllegalArgumentException.class,
                () -> wrongCar4.setLicensePlate("ZZZ99"));
    }


    @Test
    void testRentAndReturnVehicle() {

        Car car = new Car("Toyota", "Camry", 2020);
        car.setLicensePlate("AAA100");

        Customer customer = new Customer(1, "George");

        RentalSystem system = RentalSystem.getInstance();
        system.addVehicle(car);
        system.addCustomer(customer);

        assertEquals(Vehicle.VehicleStatus.Available, car.getStatus());

        assertTrue(system.rentVehicle(car.getLicensePlate(),
                                      customer.getCustomerName()));
        assertEquals(Vehicle.VehicleStatus.Rented, car.getStatus());

        assertFalse(system.rentVehicle(car.getLicensePlate(),
                                       customer.getCustomerName()));

        assertTrue(system.returnVehicle(car.getLicensePlate()));
        assertEquals(Vehicle.VehicleStatus.Available, car.getStatus());

        assertFalse(system.returnVehicle(car.getLicensePlate()));
    }

    @Test
    void testSingletonRentalSystem() throws Exception {

        Constructor<RentalSystem> constructor =
                RentalSystem.class.getDeclaredConstructor();

        assertEquals(Modifier.PRIVATE, constructor.getModifiers());

        RentalSystem a = RentalSystem.getInstance();
        RentalSystem b = RentalSystem.getInstance();

        assertNotNull(a);
        assertSame(a, b);
    }
}
