package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/car")
class CarController {

    @Autowired
    private CarServiceImpl carService;
    @GetMapping("/createCar")
    public String createCarPage(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "CreateCar";
    }
    @PostMapping("/createCar")
    public String createCarPost(@ModelAttribute Car car) {
        carService.create(car);
        return "redirect:listCar";
    }
    @GetMapping("/listCar")
    public String carListPage (Model model) {
        List<Car> allCars = carService.findAll();
        model.addAttribute("cars", allCars);
        return "CarList";
    }
    @GetMapping("/editCar")
    public String editCarPage(@RequestParam("carId") String carId, Model model){
        Car car = carService.getProductById(carId);
        model.addAttribute("car", car);
        return "EditCar";
    }
    @PostMapping("/editCar")
    public String editCarPost(@ModelAttribute Car car) {
        carService.edit(car);
        return "redirect:listCar";
    }

    @GetMapping("/deleteCar")
    public String deleteCar(@RequestParam("carId") String carId) {
        Car car = carService.getProductById(carId);
        carService.delete(car);
        return "redirect:listCar";
    }

}