package com.example.capstone3.Controller;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Knight;
import com.example.capstone3.Model.Participate_in_tournament;
import com.example.capstone3.Service.KnightService;
import com.example.capstone3.Service.Participate_in_tournamentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/knight")
public class KnightController {
    private final KnightService knightService;
    private final Participate_in_tournamentService participate_in_tournamentService;

    @GetMapping("/get")
    public ResponseEntity getAllKnight() {
        return ResponseEntity.status(200).body(knightService.getAllKnight());
    }

    @PostMapping("/add")
    public ResponseEntity addKnight(@Valid @RequestBody Knight knight) {
        knightService.addKnight(knight);
        return ResponseEntity.status(200).body("Knight added ");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateKnight(@PathVariable Integer id, @Valid @RequestBody Knight knight) throws ApiException {
        knightService.updateKnight(id, knight);
        return ResponseEntity.status(200).body("Knight updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteKnight(@PathVariable Integer id) throws ApiException {
        knightService.deleteKnight(id);
        return ResponseEntity.status(200).body("Knight deleted");
    }

    @PutMapping("/assign/{knight_id}/{horse_id}")
    public ResponseEntity assignKnightToHorse(@PathVariable Integer knight_id ,@PathVariable Integer horse_id) {

        knightService.assignHorseToKnight(horse_id,knight_id);
        return  ResponseEntity.status(HttpStatus.OK).body(
                "assign knight to Horse successfully"
        );
    }


    @PutMapping("/register-tournament/{knight_id}/{tournament_id}/{horse_id}")
    public ResponseEntity registerTournament(@PathVariable Integer knight_id, @PathVariable Integer tournament_id,@PathVariable Integer horse_id ) {

        knightService.registerTournament(knight_id,tournament_id,horse_id);
        return  ResponseEntity.status(HttpStatus.OK).body(
                "register tournament successfully"
        );
    }

    // Get Tournament History for Knight
    @GetMapping("/{knightId}/tournament-history")
    public ResponseEntity getTournamentHistoryForKnight(@PathVariable Integer knightId) {
        return ResponseEntity.ok(
                knightService.getTournamentHistoryForKnight(knightId)
        );
    }

    // Endpoint to get the average placement of a knight
    @GetMapping("/{knightId}/average-placement")
    public ResponseEntity<Double> getAveragePlacementForKnight(@PathVariable Integer knightId) {
        return ResponseEntity.ok(
                knightService.getAveragePlacementForKnight(knightId)
        );
    }
    // Endpoint to get the total earnings of a knight
    @GetMapping("/{knightId}/total-earnings")
    public ResponseEntity<Double> getTotalEarningsForKnight(@PathVariable Integer knightId) {
        return ResponseEntity.ok(
                knightService.getTotalEarningsForKnight(knightId)
        );
    }

}
