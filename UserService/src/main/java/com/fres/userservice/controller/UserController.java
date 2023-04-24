package com.fres.userservice.controller;


import com.fres.userservice.models.User;
import com.fres.userservice.services.UserService;
import com.fres.userservice.services.impl.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
//@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{userId}")
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        User user =userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    //Creating fall back method for circuitbreaker
    public  ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
        logger.info("Fallback is executed because service is down : ", ex.getMessage());
        User user = User.builder()
                .name("Dummy")
                .email("dummy@gmail.com")
                .about("This us is created dummy because some service is down")
                .userId("1234")
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(String userId){
        List<User> allUsers =userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }
}
