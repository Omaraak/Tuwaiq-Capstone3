package com.example.capstone3.Controller;

import com.example.capstone3.Model.Horse;
import com.example.capstone3.Service.HorseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/horse")
public class HorseController {

    private final HorseService horseService;

    @GetMapping("/get")
    public ResponseEntity getAllHorses() {
        return ResponseEntity.status(HttpStatus.OK).body(
                horseService.getAllHorses()
        );
    }

    @PostMapping("/add")
    public ResponseEntity addHorse(@Valid @RequestBody Horse horse) {
        horseService.addHorse(horse);
        return ResponseEntity.status(HttpStatus.OK).body(
                "Horse added successfully"
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateHorse(@Valid @RequestBody Horse horse, @PathVariable Integer id) {
        horse.setId(id);
        horseService.updateHorse(horse);
        return ResponseEntity.status(HttpStatus.OK).body(
                "Horse updated successfully"
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteHorse(@PathVariable Integer id) {
        horseService.deleteHorse(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                "Horse deleted successfully"
        );
    }
    @GetMapping("/available")
    public ResponseEntity getAvailableHorses() {
        return ResponseEntity.status(HttpStatus.OK).body(
                horseService.getAvailableHorses()
        );
    }
    @GetMapping("/breed/{breed}")
    public ResponseEntity getHorsesByBreed(@PathVariable String breed) {
        return ResponseEntity.status(HttpStatus.OK).body(
                horseService.getHorsesByBreed(breed)
        );
    }
    @GetMapping("/age/{age}")
    public ResponseEntity getHorsesByAge(@PathVariable int age) {
        return ResponseEntity.status(HttpStatus.OK).body(
                horseService.getHorsesByAge(age)
        );
    }
    @GetMapping("/colour/{colour}")
    public ResponseEntity getHorsesByColour(@PathVariable String colour) {
        return ResponseEntity.status(HttpStatus.OK).body(
                horseService.getHorsesByColour(colour)
        );
    }
    @GetMapping("/{id}/isAvailable")
    public ResponseEntity isHorseAvailable(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                horseService.isHorseAvailable(id)
        );
    }
    @GetMapping("/qualified/{age}")
    public ResponseEntity isHorseQualified(@PathVariable Integer age){
        return ResponseEntity.status(HttpStatus.OK).body(
                horseService.isHorseQualified(age)
        );
    }
}
