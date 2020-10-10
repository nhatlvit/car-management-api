package shopping.car.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import shopping.car.models.Car;
import shopping.car.service.car.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @ApiOperation(value = "Find all cars")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list")
    })
    @GetMapping(value = "/cars", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Car>> findAllCars() {
        List<Car> cars = carService.findAllCars();

        if (cars.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @ApiOperation(value = "Find car by car ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved Car"),
        @ApiResponse(code = 204, message = "Not found car by carID")
    })
    @GetMapping(value = "/car/{carId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> findCarById(
            @ApiParam(value = "CarID which car object will retrieve", required = true) @PathVariable("carId") Integer carId) {

        Optional<Car> car = carService.findCarById(carId);

        if (!car.isPresent()) {
            return new ResponseEntity<>(car.get(), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(car.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Create car")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created car successfully")
    })
    @PostMapping(value = "/car", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> createCar(
            @ApiParam(value = "Car object which will be added in database", required = true) @RequestBody Car car) {
        carService.save(car);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update car by carId")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Update car successfully"),
        @ApiResponse(code = 404, message = "Not found car")
    })
    @PutMapping(value = "/car/{carId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> updateCar(
            @ApiParam(value = "CarID of object which will be updated", required = true) @PathVariable("carId") Integer carId,
            @ApiParam(value = "Car object which will be updated", required = true)  @RequestBody Car car) {

        Optional<Car> currentCar = carService.findCarById(carId);

        if (!currentCar.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentCar.get().setCarName(car.getCarName());
        currentCar.get().setCarDescription(car.getCarDescription());
        currentCar.get().setCarCategory(car.getCarCategory());
        currentCar.get().setCarPrice(car.getCarPrice());

        carService.save(currentCar.get());
        return new ResponseEntity<>(currentCar.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete car by carId")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Deleted car successfully"),
        @ApiResponse(code = 404, message = "Not found car")
    })
    @DeleteMapping(value = "/car/{carId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> deleteCar(
    		@ApiParam(value = "CarID of object which will be deleted", required = true) @PathVariable("carId") Integer carId) {
        Optional<Car> car = carService.findCarById(carId);

        if (!car.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        carService.remove(car.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
};