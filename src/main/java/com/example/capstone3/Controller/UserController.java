package com.example.capstone3.Controller;

import com.example.capstone3.Model.User;
import com.example.capstone3.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(
                userService.getAllUsers()
        );
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(
                "User added successfully"
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@Valid @RequestBody User user, @PathVariable Integer id) {
        user.setId(id);
        userService.updateUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(
                "User updated successfully"
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                "User deleted successfully"
        );
    }

    @PutMapping("/subscribe/{userID}")
    public ResponseEntity subscribeUser(@PathVariable Integer userID) {
        userService.subscribe(userID);
        return ResponseEntity.status(HttpStatus.OK).body("user subscribed successfully");
    }

    @PutMapping("/unsubscribe/{userID}")
    public ResponseEntity unsubscribeUser(@PathVariable Integer userID) {
        userService.unsubscribe(userID);
        return ResponseEntity.status(HttpStatus.OK).body("user unsubscribed successfully");
    }

    @PutMapping("/getSubscribedUsers")
    public ResponseEntity getSubscribedUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getSubscribedUsers());
    }

    @GetMapping("/getUserReservations/{userID}")
    public ResponseEntity getUserReservations(@PathVariable Integer userID) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserReservations(userID));
    }

    @GetMapping("/getUserReviews/{userID}")
    public ResponseEntity getUserReviews(@PathVariable Integer userID) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserReviews(userID));
    }

}
