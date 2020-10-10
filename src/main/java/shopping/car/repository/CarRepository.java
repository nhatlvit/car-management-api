package shopping.car.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import shopping.car.models.Car;

@Repository
public interface CarRepository extends CrudRepository<Car, Integer> {

}