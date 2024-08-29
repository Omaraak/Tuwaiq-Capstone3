package com.example.capstone3.Controller;

import com.example.capstone3.Model.Instructor;
import com.example.capstone3.Model.Organizers;
import com.example.capstone3.Service.OrganizersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/organizers")
public class OrganizersController {

    private final OrganizersService organizersService;

    @GetMapping("/get")
    public ResponseEntity getAllOrganizers() {
        return ResponseEntity.status(200).body(organizersService.getAllOrganizers());
    }

    @PostMapping("/add")
    public ResponseEntity addOrganizers(@Valid @RequestBody Organizers organizers) {
        organizersService.addOrganizers(organizers);
        return ResponseEntity.status(200).body("Organizers added ");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateOrganizers(@PathVariable Integer id,@Valid @RequestBody Organizers organizers) {
        organizersService.updateOrganizers(id,organizers);
        return ResponseEntity.status(200).body("Organizers updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrganizers(@PathVariable Integer id) {
        organizersService.deleteOrganizers(id);
        return ResponseEntity.status(200).body("Organizers deleted");
    }

}
