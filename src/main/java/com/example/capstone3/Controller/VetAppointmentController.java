package com.example.capstone3.Controller;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.VetAppointment;
import com.example.capstone3.Service.VetAppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/vetAppointment")
public class VetAppointmentController {
    private final VetAppointmentService vetAppointmentService;

    @GetMapping("/get")
    public ResponseEntity getAllVetAppointment() {
        return ResponseEntity.status(200).body(vetAppointmentService.getAllVetAppointment());
    }

    @PostMapping("/add/{vet_id}/{horse_id}/{stable_id}")
    public ResponseEntity bookingAppointmentForHorse(@PathVariable Integer vet_id,@PathVariable Integer horse_id,@PathVariable Integer stable_id,@Valid @RequestBody VetAppointment vetAppointment) {
        vetAppointmentService.bookingAppointmentForHorse(vet_id,horse_id,stable_id,vetAppointment);
        return ResponseEntity.status(200).body("VetAppointment added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateVetAppointment(@PathVariable Integer id, @Valid @RequestBody VetAppointment vetAppointment) throws ApiException {
        vetAppointmentService.updateVetAppointment(id, vetAppointment);
        return ResponseEntity.status(200).body("VetAppointment updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteVetAppointment(@PathVariable Integer id) throws ApiException {
        vetAppointmentService.deleteVetAppointment(id);
        return ResponseEntity.status(200).body("VetAppointment deleted");
    }




}
