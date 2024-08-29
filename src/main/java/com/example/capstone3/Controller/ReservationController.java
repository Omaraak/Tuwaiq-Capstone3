package com.example.capstone3.Controller;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Reservation;
import com.example.capstone3.Service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/get")
    public ResponseEntity getAllReservation() {
        return ResponseEntity.status(200).body(reservationService.getAllReservation());
    }

    @PostMapping("/add/{userID}/{instructorID}/{stableID}/{horseID}")
    public ResponseEntity addReservation(@PathVariable int userID, @PathVariable int instructorID, @PathVariable int stableID, @PathVariable int horseID, @Valid @RequestBody Reservation reservation) {
        reservationService.addReservation(userID, instructorID, stableID, horseID, reservation);
        return ResponseEntity.status(200).body("Reservation added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateReservation(@PathVariable Integer id, @Valid @RequestBody Reservation reservation) throws ApiException {
        reservationService.updateReservation(id, reservation);
        return ResponseEntity.status(200).body("Reservation updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteReservation(@PathVariable Integer id) throws ApiException {
        reservationService.deleteReservation(id);
        return ResponseEntity.status(200).body("Reservation deleted");
    }

    @PutMapping("/cancelReservation/{userID}/{reservationID}")
    public ResponseEntity cancelReservation(@PathVariable Integer userID, @PathVariable Integer reservationID) {
        reservationService.cancelReservation(userID, reservationID);
        return ResponseEntity.status(200).body("Reservation cancelled");
    }
}
