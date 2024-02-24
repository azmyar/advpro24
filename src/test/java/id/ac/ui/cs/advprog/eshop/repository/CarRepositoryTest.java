package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CarRepositoryTest {
    @InjectMocks
    CarRepository carRepository;
    @BeforeEach
    void setup() {

    }
    @Test
    void testCreateAndFind() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Lamborghini");
        car.setCarColor("Purple");
        car.setCarQuantity(100);
        carRepository.create(car);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();
        assertEquals(car.getCarId(), savedCar.getCarId());
        assertEquals(car.getCarName(), savedCar.getCarName());
        assertEquals(car.getCarColor(), savedCar.getCarColor());
        assertEquals(car.getCarQuantity(), savedCar.getCarQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneCar() {
        Car car1 = new Car();
        car1.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car1.setCarName("Lamborghini");
        car1.setCarColor("Purple");
        car1.setCarQuantity(100);
        carRepository.create(car1);

        Car car2 = new Car();
        car2.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd7");
        car2.setCarName("Bugatti");
        car2.setCarColor("Red");
        car2.setCarQuantity(50);
        carRepository.create(car2);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();
        assertEquals(car1.getCarId(), savedCar.getCarId());
        savedCar = carIterator.next();
        assertEquals(car2.getCarId(), savedCar.getCarId());
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testFindById() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Lamborghini");
        car.setCarColor("Purple");
        car.setCarQuantity(100);
        carRepository.create(car);

        Car savedCar = carRepository.getProductById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertEquals(car.getCarId(), savedCar.getCarId());
        assertEquals(car.getCarName(), savedCar.getCarName());
        assertEquals(car.getCarColor(), savedCar.getCarColor());
        assertEquals(car.getCarQuantity(), savedCar.getCarQuantity());
    }

    @Test
    void testFindById_IfCarNotFound() {
        carRepository.create(new Car());

        Car savedCar = carRepository.getProductById("randomCarId");
        assertNull(savedCar);
    }

    @Test
    void testEditCar(){
        // Create Car
        Car carInitial = new Car();
        carInitial.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        carInitial.setCarName("Lamborghini");
        carInitial.setCarColor("Purple");
        carInitial.setCarQuantity(100);
        carRepository.create(carInitial);

        // Edit Car
        Car carModified = new Car();
        carModified.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        carModified.setCarName("Ioniq");
        carModified.setCarColor("Black");
        carModified.setCarQuantity(101);
        carRepository.edit(carModified);

        // Check whether Car correctly changed
        Car testCar = carRepository.getProductById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", testCar.getCarId());
        assertEquals("Ioniq", testCar.getCarName());
        assertEquals("Black", testCar.getCarColor());
        assertEquals(101, testCar.getCarQuantity());
    }

    @Test
    void testEditCar_IfCarQuantityIsNegative(){
        // Create Car
        Car carInitial = new Car();
        carInitial.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        carInitial.setCarName("Lamborghini");
        carInitial.setCarColor("Purple");
        carInitial.setCarQuantity(100);
        carRepository.create(carInitial);

        // Insert Negative Quantity
        Car carModified = new Car();
        carModified.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        carModified.setCarName("Ioniq");
        carInitial.setCarColor("Black");
        carModified.setCarQuantity(-100);
        assertThrows(IllegalArgumentException.class, () -> carRepository.edit(carModified));
    }

    @Test
    void testCreateCar_IfCarQuantityIsNegative(){
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Lamborghini");
        car.setCarColor("Purple");
        car.setCarQuantity(-100);
        assertThrows(IllegalArgumentException.class, () -> carRepository.create(car));
    }

    @Test
    void testCreateCar_IfCarIdIsNull(){
        Car car = new Car();
        car.setCarId(null);
        car.setCarName("Lamborghini");
        car.setCarColor("Purple");
        car.setCarQuantity(100);
        carRepository.create(car);
        assertNotNull(car.getCarId());
    }

    @Test
    void testDeleteCar(){
        // Create Car
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Lamborghini");
        car.setCarColor("Purple");
        car.setCarQuantity(100);
        carRepository.create(car);

        // Delete Car
        assertEquals(
                "eb558e9f-1c39-460e-8860-71af6af63bd6",
                carRepository.delete(car).getCarId()
        );

        // Create Random Car
        carRepository.create(new Car());

        // Check whether the deleted Car still exist
        Car testCar = carRepository.getProductById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNull(testCar);
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> carRepository.delete(car));
    }

    @Test
    void testDeleteCar_IfCarNotFound(){

        // Create Random Car Without Inserting it to Repository
        Car car = new Car();

        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> carRepository.delete(car));
    }
}