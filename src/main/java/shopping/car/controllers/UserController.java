package shopping.car.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import shopping.car.models.Car;
import shopping.car.models.User;
import shopping.car.service.car.CarService;
import shopping.car.service.user.UserService;
import shopping.car.utils.EncodeUtils;

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
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Find all users")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list")
    })
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users = userService.findAllUsers();

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @ApiOperation(value = "Find user by userID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved user"),
        @ApiResponse(code = 204, message = "Not found user by userID")
    })
    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> findUserById(
            @ApiParam(value = "UserID which user object will retrieve", required = true) @PathVariable("userId") Integer userId) {

        Optional<User> user = userService.findUserById(userId);

        if (!user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Create User")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created user successfully")
    })
    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(
            @ApiParam(value = "User object which will be added in database", required = true) @RequestBody User user) {
        user.setPassword(EncodeUtils.encodePassword(user.getPassword()));
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update User by userId")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Update user successfully"),
        @ApiResponse(code = 404, message = "Not found user")
    })
    @PutMapping(value = "/user/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(
            @ApiParam(value = "UserID of object which will be updated", required = true) @PathVariable("userId") Integer userId,
            @ApiParam(value = "User object which will be updated", required = true) @RequestBody User user) {

        Optional<User> currentUser = userService.findUserById(userId);

        if (!currentUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentUser.get().setAddress(user.getAddress());
        currentUser.get().setEmail(user.getEmail());
        currentUser.get().setFirstName(user.getFirstName());
        currentUser.get().setLastName(user.getLastName());
        currentUser.get().setPhoneNumber(user.getPhoneNumber());

        userService.save(currentUser.get());
        return new ResponseEntity<>(currentUser.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete user by userId")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Deleted user successfully"),
        @ApiResponse(code = 404, message = "Not found user")
    })
    @DeleteMapping(value = "/user/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> deleteUser(
    		@ApiParam(value = "UserID of object which will be deleted", required = true) @PathVariable("userId") Integer userId) {
        Optional<User> user = userService.findUserById(userId);

        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userService.remove(user.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
};