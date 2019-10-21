package cars.carsproject.Repositories;

import cars.carsproject.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("select c from Car c where c.id = ?1")
    Car getCarById(long id);

}
