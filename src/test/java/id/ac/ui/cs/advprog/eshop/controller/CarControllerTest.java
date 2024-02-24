package id.ac.ui.cs.advprog.eshop.controller;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.ui.Model;

@ExtendWith(MockitoExtension.class)
public class CarControllerTest {

    @InjectMocks
    CarController CarController;

    @Mock
    private CarServiceImpl carService;

    @Mock
    private Model model;

    @Test
    void testCreateCarPage() {
        String result = CarController.createCarPage(model);
        assertEquals("CreateCar", result);
    }

    @Test
    void testCreateCarPost() {
        Car car = new Car();
        String result = CarController.createCarPost(car);
        assertEquals("redirect:listCar", result);
    }

    @Test
    void testCarListPage() {
        String result = CarController.carListPage(model);
        assertEquals("CarList", result);
    }

    @Test
    void testEditCarPage() {
        String result = CarController.editCarPage("some-product-id", model);
        assertEquals("EditCar", result);
    }

    @Test
    void testEditCarPost() {
        Car car = new Car();
        String result = CarController.editCarPost(car);
        assertEquals("redirect:listCar", result);
    }

    @Test
    void testDeleteCar() {
        String result = CarController.deleteCar("some-product-id");
        assertEquals("redirect:listCar", result);
    }
}