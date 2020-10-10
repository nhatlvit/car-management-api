package shopping.car.service.car;

import java.util.List;
import java.util.Optional;

import shopping.car.models.Car;

public interface CarService {

    List<Car> findAllCars();

    Optional<Car> findCarById(Integer id);

    void save(Car car);

    void remove(Car car);
}