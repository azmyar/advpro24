package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {
    @InjectMocks
    private  CarServiceImpl carService;

    @Mock
    private CarRepository carRepository;

    @Test
    void testCreate() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Lamborghini");
        car.setCarColor("Purple");
        car.setCarQuantity(100);
        Mockito.when(carRepository.create(car)).thenReturn(car);


        assertEquals(car, carService.create(car));
    }

    @Test
    void testFindAll() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Lamborghini");
        car.setCarColor("Purple");
        car.setCarQuantity(100);

        ArrayList<Car> cars = new ArrayList<>();
        cars.add(car);

        Iterator<Car> carIterator = cars.iterator();
        Mockito.when(carRepository.findAll()).thenReturn(carIterator);

        assertEquals(cars, carService.findAll());
    }

    @Test
    void testFindById() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Lamborghini");
        car.setCarColor("Purple");
        car.setCarQuantity(100);
        Mockito.when(carRepository.getProductById("eb558e9f-1c39-460e-8860-71af6af63bd6")).thenReturn(car);

        assertEquals(car, carService.getProductById("eb558e9f-1c39-460e-8860-71af6af63bd6"));
    }

    @Test
    void testEditCar() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Lamborghini");
        car.setCarColor("Purple");
        car.setCarQuantity(100);
        Mockito.when(carRepository.edit(car)).thenReturn(car);

        assertEquals(car, carService.edit(car));
    }

    @Test
    void testDeleteCar() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Lamborghini");
        car.setCarColor("Purple");
        car.setCarQuantity(100);
        Mockito.when(carRepository.delete(car)).thenReturn(car);

        assertEquals(car, carService.delete(car));
    }
}