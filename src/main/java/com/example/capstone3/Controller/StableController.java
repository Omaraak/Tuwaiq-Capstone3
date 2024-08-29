package com.example.capstone3.Controller;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Stable;
import com.example.capstone3.Model.VetAppointment;
import com.example.capstone3.Service.StableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stable")
public class StableController {
    private final StableService stableService;

    @GetMapping("/get")
    public ResponseEntity getAllStable() {
        return ResponseEntity.status(200).body(stableService.getAllStable());
    }

    @PostMapping("/add")
    public ResponseEntity addStable(@Valid @RequestBody Stable stable) {
        stableService.addStable(stable);
        return ResponseEntity.status(200).body("Stable added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStable(@PathVariable Integer id, @Valid @RequestBody Stable stable) throws ApiException {
        stableService.updateStable(id, stable);
        return ResponseEntity.status(200).body("Stable updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStable(@PathVariable Integer id) throws ApiException {
        stableService.deleteStable(id);
        return ResponseEntity.status(200).body("Stable deleted");
    }

    @GetMapping("/get/horseAppointments/{horseId}")
    public ResponseEntity <List<VetAppointment>> getHorseAppointments(@PathVariable Integer horseId){
        return ResponseEntity.status(200).body(stableService.horseAppointments(horseId));
    }


    @PutMapping("/cancel/{vet_id}/{horse_id}/{stable_id}/{vetAppointment_id}")
    public ResponseEntity cancelAppointmentForHorse(@PathVariable Integer vet_id,@PathVariable Integer horse_id,@PathVariable Integer stable_id,@PathVariable Integer vetAppointment_id){
      stableService.cancelAppointmentForHorse(vet_id,horse_id,stable_id,vetAppointment_id);
      return ResponseEntity.status(200).body("Appointment cancelled successfully");
    }



}
