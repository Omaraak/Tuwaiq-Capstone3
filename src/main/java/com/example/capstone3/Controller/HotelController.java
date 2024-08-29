package com.example.capstone3.Controller;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Hotel;
import com.example.capstone3.Service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hotel")
public class HotelController {
    private final HotelService hotelService;

    @GetMapping("/get")
    public ResponseEntity getAllHotel() {
        return ResponseEntity.status(200).body(hotelService.getAllHotel());
    }

    @PostMapping("/add/{userId}/{stableID}/{horseID}")
    public ResponseEntity addHotel(@PathVariable Integer userId, @PathVariable Integer stableID, @PathVariable Integer horseID, @Valid @RequestBody Hotel instructor) {
        hotelService.addHotel(userId, stableID, horseID, instructor);
        return ResponseEntity.status(200).body("Hotel added ");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateHotel(@PathVariable Integer id,@Valid @RequestBody Hotel instructor) throws ApiException {
        hotelService.updateHotel(id, instructor);
        return ResponseEntity.status(200).body("Hotel updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteHotel(@PathVariable Integer id) throws ApiException {
        hotelService.deleteHotel(id);
        return ResponseEntity.status(200).body("Hotel deleted");
    }
}
