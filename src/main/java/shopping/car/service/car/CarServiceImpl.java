package shopping.car.service.car;

import shopping.car.models.Car;
import shopping.car.repository.CarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

	private CarRepository carRepository;

    @Autowired
    public CarServiceImpl (CarRepository carRepository){
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> findAllCars() {
        return (List<Car>) carRepository.findAll();
    }

    @Override
    public Optional<Car> findCarById(Integer id) {
        return carRepository.findById(id);
    }

    @Override
    public void save(Car car) {
    	carRepository.save(car);
    }

    @Override
    public void remove(Car car) {
    	carRepository.delete(car);
    }
}