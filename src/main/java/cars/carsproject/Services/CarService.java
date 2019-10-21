package cars.carsproject.Services;

import cars.carsproject.Repositories.CarRepository;
import cars.carsproject.models.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public String addCar(Car car) {
        Car result = carRepository.save(car);
        if (result == null) {
            return "Received error while adding new car to collection";
        }
        return "Adding " + result.getBrand() + " to car collection";
    }

    public List<Car> getCars() {
        return carRepository.findAll();
    }

    public void deleteCar(long id) {
        carRepository.deleteById(id);
    }

    public Car getCarById(long id) {
        return carRepository.getOne(id);
    }

    public String updateCar(Car car) {
        Car result = carRepository.save(car);
        if (result == null) {
            return "Received error while updating car to collection";
        }
        return "Updating " + result.getBrand() + " to car collection";
    }


    public Car apiAddCar(Car car) {
        return carRepository.save(car);
    }

}