package com.example.capstone3.Controller;

import com.example.capstone3.Model.Participate_in_tournament;
import com.example.capstone3.Service.Participate_in_tournamentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/participation")
public class Participate_in_tournamentController {

    private final Participate_in_tournamentService participateInTournamentService;

    @GetMapping("/get")
    public ResponseEntity getAllParticipations() {
        return ResponseEntity.status(HttpStatus.OK).body(
                participateInTournamentService.getAllParticipations()
        );
    }

    @PostMapping("/add")
    public ResponseEntity addParticipation(@Valid @RequestBody Participate_in_tournament participation) {
        participateInTournamentService.addParticipation(participation);
        return ResponseEntity.status(HttpStatus.OK).body(
                "Participation added successfully"
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateParticipation(@Valid @RequestBody Participate_in_tournament participation, @PathVariable Integer id) {
        participation.setId(id);
        participateInTournamentService.updateParticipation(participation);
        return ResponseEntity.status(HttpStatus.OK).body(
                "Participation updated successfully"
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteParticipation(@PathVariable Integer id) {
        participateInTournamentService.deleteParticipation(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                "Participation deleted successfully"
        );
    }
    // Set the placement of a participant
    @PutMapping("/{participateId}/set-placement")
    public ResponseEntity<String> setPlacement(@PathVariable Integer participateId, @RequestParam int placement) {
        participateInTournamentService.setPlacement(participateId, placement);
        return ResponseEntity.ok("Placement set successfully");
    }
    // Distribute prizes to top 8 participants in a tournament
    @PutMapping("/{tournamentId}/distribute-prizes")
    public ResponseEntity<String> distributePrizes(@PathVariable Integer tournamentId) {
        participateInTournamentService.distributePrizes(tournamentId);
        return ResponseEntity.ok("Prizes distributed successfully");
    }

}
