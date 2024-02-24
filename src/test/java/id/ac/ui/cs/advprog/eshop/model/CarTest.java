package  id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CarTest {
    Car car;
    @BeforeEach
    void setUp(){
        this.car = new Car();
        this.car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.car.setCarName("Lamborghini");
        this.car.setCarColor("Purple");
        this.car.setCarQuantity(100);
    }
    @Test
    void testGetProductId() {
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", this.car.getCarId());
    }

    @Test
    void testGetProductName() {
        assertEquals("Lamborghini", this.car.getCarName());
    }

    @Test
    void testGetProductColor() {
        assertEquals("Purple", this.car.getCarColor());
    }

    @Test
    void testGetProductQuantity() {
        assertEquals(100, this.car.getCarQuantity());
    }
}
