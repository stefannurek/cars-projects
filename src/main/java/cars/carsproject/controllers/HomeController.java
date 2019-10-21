package cars.carsproject.controllers;

import cars.carsproject.Services.CarService;
import cars.carsproject.models.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {

    private CarService carService;

    public HomeController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("cars")
    public String getIndex(Model model, @RequestParam(value = "message", required = false) String resultMessage) {
        String titlePage = "Home page Cars";
        String welcome = "Welcome to my application car";
        model.addAttribute("welcome", welcome);
        model.addAttribute("titlePage", titlePage);
        model.addAttribute("resultMessage", resultMessage);
        model.addAttribute("cars", carService.getCars());
        return "index";
    }


    @PostMapping("/cars/add")
    public String addCar(@ModelAttribute Car carAdd) {

        Car car = new Car();
        car.setBrand(carAdd.getBrand());
        car.setModel(carAdd.getModel());
        car.setYear(carAdd.getYear());
        car.setDescribe(carAdd.getDescribe());
        String operationResult = carService.addCar(car);
        return "redirect:/cars?message=" + operationResult;
    }

    @GetMapping("/cars/delete")
    public String deleteCar(@RequestParam long id) {
     carService.deleteCar(id);
        return "redirect:/cars";
    }

    @GetMapping("/cars/update")
    public String updateCar(@RequestParam long id , Model model){

        Car car = carService.getCarById(id);
        if(car == null){
            return "Connot find car!";
        }
        model.addAttribute("car", car);
        return "update";
    }

    @PostMapping("/cars/update/confirm")
    public String updateCarConfirm(@ModelAttribute Car carAdd) {

        Car car = new Car();
        car.setId(carAdd.getId());
        car.setBrand(carAdd.getBrand());
        car.setModel(carAdd.getModel());
        car.setYear(carAdd.getYear());
        car.setDescribe(carAdd.getDescribe());
        String operationResult = carService.updateCar(car);
        return "redirect:/cars?message=" + operationResult;
    }


}