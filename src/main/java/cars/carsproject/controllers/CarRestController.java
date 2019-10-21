package cars.carsproject.controllers;

import cars.carsproject.Services.CarService;
import cars.carsproject.models.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarRestController {

    private CarService carService;

    public CarRestController(CarService carService){
        this.carService=carService;
    }

    @GetMapping("/api/cars")
    public List<Car> getCars(){
        return carService.getCars();
    }


    @GetMapping("/api/cars/{id}")
    public Car getCars(@PathVariable long id){
        return carService.getCarById(id);
    }

    @PostMapping("/api/cars/add")
    public ResponseEntity<Car> addCar(@RequestBody Car car){
        Car newCar = carService.apiAddCar(car);
        if (newCar == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
