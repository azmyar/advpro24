package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class CarRepository {
    static int id = 0;
    private List<Car> carData = new ArrayList<>();
    public Car create(Car car){

        if (car.getCarQuantity() < 0){
            throw new IllegalArgumentException("Invalid Product Quantity");
        }

        if (car.getCarId() == null) car.setCarId(UUID.randomUUID().toString());
        carData.add(car);
        return car;

    }
    public Iterator<Car> findAll(){
        return carData.iterator();
    }

    public Car edit(Car car) {


        if (car.getCarQuantity() < 0){
            throw new IllegalArgumentException("Invalid Product Quantity");
        }

        Car existingCar = getProductById(car.getCarId());

        existingCar.setCarName(car.getCarName());
        existingCar.setCarColor(car.getCarColor());
        existingCar.setCarQuantity(car.getCarQuantity());

        return existingCar;
    }

    public Car getProductById(String carId) {
        for (Car car : carData) {
            if (car.getCarId().equals(carId)) {
                return car;
            }
        }
        return null;
    }
    public Car delete(Car car) {

        String carId = car.getCarId();

        for (Car item : carData) {
            if (item.getCarId().equals(carId)) {
                carData.remove(item);
                return item;
            }
        }

        throw new ArrayIndexOutOfBoundsException("Car Doesn't Exist");
    }
}

