package shopping.car.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import shopping.car.models.Car;
import shopping.car.models.LoginInfoModel;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class AuthenticateController {

    @Autowired
    private UserService userService;

    @Autowired
    public AuthenticateController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Authenticate user")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully or failure authenticate user")
    })
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> authenticate(@RequestBody LoginInfoModel loginInfoModel) {
        Optional<User> user = userService.authenticate(loginInfoModel);
        
        Map<String, Object> result = new HashMap<String, Object>(); 
        
        if (user.isEmpty()) {
        	result.put("result", "failure");
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        result.put("result", "success");
        result.put("user", user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
};