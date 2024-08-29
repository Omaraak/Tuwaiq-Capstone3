package com.example.capstone3.Controller;

import com.example.capstone3.Model.Vet;
import com.example.capstone3.Service.VetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/vet")
public class VetController {

    private final VetService vetService;

    @GetMapping("/get")
    public ResponseEntity getAllVets() {
        return ResponseEntity.status(HttpStatus.OK).body(
                vetService.getAllVets()
        );
    }

    @PostMapping("/add")
    public ResponseEntity addVet(@Valid @RequestBody Vet vet) {
        vetService.addVet(vet);
        return ResponseEntity.status(HttpStatus.OK).body(
                "Vet added successfully"
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateVet(@Valid @RequestBody Vet vet, @PathVariable Integer id) {
        vet.setId(id);
        vetService.updateVet(vet);
        return ResponseEntity.status(HttpStatus.OK).body(
                "Vet updated successfully"
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteVet(@PathVariable Integer id) {
        vetService.deleteVet(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                "Vet deleted successfully"
        );
    }

    @GetMapping("/near/{vet_id}/{stable_id}")
    public ResponseEntity nearLocation(@PathVariable Integer vet_id,@PathVariable Integer stable_id){
        return ResponseEntity.status(HttpStatus.OK).body(
                vetService.nearLocation(vet_id,stable_id)
        );
    }
    @PutMapping("/raise/{vet_id}")
    public ResponseEntity raiseSalary(@PathVariable Integer vet_id){
        vetService.raiseSalary(vet_id);
        return ResponseEntity.status(HttpStatus.OK).body(
                "Vet raised successfully"
        );
    }

    @GetMapping("/get/location/{location}")
    public ResponseEntity getVetByLocation(@PathVariable String location){
        return ResponseEntity.status(HttpStatus.OK).body(
                vetService.getVetByLocation(location)
        );
    }

    @GetMapping("/get/salary/{salary}")
    public ResponseEntity getVetBySalary(@PathVariable double salary){
        return ResponseEntity.status(HttpStatus.OK).body(
                vetService.getVetBySalary(salary)
        );
    }

    @GetMapping("/get/specialty/{specialty}")
    public ResponseEntity getVetBySpecialty(@PathVariable String specialty){
        return ResponseEntity.status(HttpStatus.OK).body(
                vetService.getVetBySpecialty(specialty)
        );
    }

    @GetMapping("/get/year/{year_of_experience}")
    public ResponseEntity getVetByYearOfExperience(@PathVariable Integer year_of_experience){
        return ResponseEntity.status(HttpStatus.OK).body(
                vetService.getVetByYear_of_experience(year_of_experience)
        );
    }
}
