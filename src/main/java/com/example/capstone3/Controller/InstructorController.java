package com.example.capstone3.Controller;

import com.example.capstone3.Model.Instructor;
import com.example.capstone3.Service.InstructorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/instructor")
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping("/get")
    public ResponseEntity getAllInstructor() {
        return ResponseEntity.status(200).body(instructorService.getAllInstructor());
    }

    @PostMapping("/add")
    public ResponseEntity addInstructor( @RequestBody Instructor instructor) {
        instructorService.addInstructor(instructor);
        return ResponseEntity.status(200).body("Instructor added ");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateInstructor(@PathVariable Integer id,@Valid @RequestBody Instructor instructor) {
       instructorService.updateInstructor(id, instructor);
        return ResponseEntity.status(200).body("Instructor updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteInstructor(@PathVariable Integer id) {
       instructorService.deleteInstructor(id);
        return ResponseEntity.status(200).body("Instructor deleted");
    }

    @GetMapping("/getInstructorReview/{instructorId}")
    public ResponseEntity getInstructorReview(@PathVariable Integer instructorId) {
        return ResponseEntity.status(200).body(instructorService.getInstructorReviews(instructorId));
    }

}
