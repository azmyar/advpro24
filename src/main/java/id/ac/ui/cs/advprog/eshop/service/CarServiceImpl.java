package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CarServiceImpl implements IService<Car> {
    @Autowired
    private CarRepository carRepository;
    @Override
    public Car create(Car car) {
        return carRepository.create(car);
    }
    @Override
    public Car getProductById (String carId) {
        return carRepository.getProductById(carId);
    }

    @Override
    public Car edit(Car car) {
        return carRepository.edit(car);
    }

    @Override
    public Car delete(Car car) {
        return carRepository.delete(car);
    }

    @Override
    public List<Car> findAll() {
        Iterator<Car> carIterator = carRepository.findAll();
        List<Car> allCar = new ArrayList<>();
        carIterator.forEachRemaining(allCar::add);
        return allCar;
    }

}