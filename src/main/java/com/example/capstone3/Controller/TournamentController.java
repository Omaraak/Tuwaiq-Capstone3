package com.example.capstone3.Controller;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Instructor;
import com.example.capstone3.Model.Tournament;
import com.example.capstone3.Service.Participate_in_tournamentService;
import com.example.capstone3.Service.TournamentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tournament")
public class TournamentController {

    private final TournamentService tournamentService;
    private final Participate_in_tournamentService participate_in_tournamentService;

    @GetMapping("/get")
    public ResponseEntity getAllTournament() {
        return ResponseEntity.status(200).body(tournamentService.getAllTournament());
    }

    @PostMapping("/add")
    public ResponseEntity addTournament(@Valid @RequestBody Tournament tournament) {
        tournamentService.addTournament(tournament);
        return ResponseEntity.status(200).body("Tournament added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateTournament(@PathVariable Integer id,@Valid @RequestBody Tournament tournament) throws ApiException {
        tournamentService.updateTournament(id, tournament);
        return ResponseEntity.status(200).body("Tournament updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTournament(@PathVariable Integer id) throws ApiException {
        tournamentService.deleteTournament(id);
        return ResponseEntity.status(200).body("Tournament deleted");
    }

    // Cancel Tournament
    @PutMapping("/cancel/{tournamentId}")
    public ResponseEntity cancelTournament(@PathVariable Integer tournamentId) {
        tournamentService.cancelTournament(tournamentId);
        return ResponseEntity.ok("Tournament cancelled successfully");
    }

    // Get Upcoming Tournaments
    @GetMapping("/upcoming")
    public ResponseEntity getUpcomingTournaments() {
        return ResponseEntity.ok(
                tournamentService.getUpcomingTournaments()
        );
    }

    // Reschedule Tournament
    @PutMapping("/reschedule/{tournamentId}")
    public ResponseEntity rescheduleTournament(
            @PathVariable Integer tournamentId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate newStartDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate newEndDate) {
        tournamentService.rescheduleTournament(tournamentId, newStartDate, newEndDate);
        return ResponseEntity.ok("Tournament rescheduled successfully");
    }

    // Check Tournament Capacity
    @GetMapping("/check-capacity/{tournamentId}")
    public ResponseEntity checkTournamentCapacity(@PathVariable Integer tournamentId) {
        int capacity = tournamentService.checkTournamentCapacity(tournamentId);
        return ResponseEntity.ok(capacity);
    }

    // Distribute Prizes to Top 8 Participants
    @PutMapping("/{tournamentId}/distribute-prizes")
    public ResponseEntity<String> distributePrizes(@PathVariable Integer tournamentId) {
        participate_in_tournamentService.distributePrizes(tournamentId);
        return ResponseEntity.ok("Prizes distributed successfully");
    }

    @GetMapping("/darkHorse/{tournamentID}")
    public ResponseEntity darkHorse(@PathVariable Integer tournamentID) {
        return ResponseEntity.ok(tournamentService.darkHorse(tournamentID));
    }

    @GetMapping("/favoriteToWin/{tournamentID}")
    public ResponseEntity favoriteToWin(@PathVariable Integer tournamentID) {
        return ResponseEntity.ok(tournamentService.favoriteToWin(tournamentID));
    }

    // Start Tournament
    @PutMapping("/start/{tournamentId}")
    public ResponseEntity startTournament(@PathVariable Integer tournamentId) {
        tournamentService.startTournament(tournamentId);
        return ResponseEntity.ok("Tournament started successfully");
    }

    // End Tournament
    @PutMapping("/end/{tournamentId}")
    public ResponseEntity endTournament(@PathVariable Integer tournamentId) {
        tournamentService.endTournament(tournamentId);
        return ResponseEntity.ok("Tournament ended successfully");
    }

}
