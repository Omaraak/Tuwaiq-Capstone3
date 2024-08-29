package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Participate_in_tournament;
import com.example.capstone3.Model.Tournament;
import com.example.capstone3.Repository.Participate_in_tournament_Repository;
import com.example.capstone3.Repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Participate_in_tournamentService {
    private final Participate_in_tournament_Repository participateInTournamentRepository;

    // Prize distribution ratios
    private static final double[] PRIZE_DISTRIBUTION = {
            0.4667, // 1st place
            0.25,   // 2nd place
            0.1467, // 3rd place
            0.08,   // 4th place
            0.04,   // 5th place
            0.0187, // 6th place
            0.0187, // 7th place
            0.0187  // 8th place
    };
    private final TournamentRepository tournamentRepository;

    public List<Participate_in_tournament> getAllParticipations() {
        return participateInTournamentRepository.findAll();
    }

    public void addParticipation(Participate_in_tournament participation) {
        participateInTournamentRepository.save(participation);
    }

    public void updateParticipation(Participate_in_tournament participation) {
        Participate_in_tournament existingParticipation = participateInTournamentRepository
                .findParticipate_in_tournamentById(participation.getId());

        if (existingParticipation == null) {
            throw new ApiException("Participation not found");
        }

        existingParticipation.setPlacement(participation.getPlacement());
        existingParticipation.setPrize(participation.getPrize());

        participateInTournamentRepository.save(existingParticipation);
    }

    public void deleteParticipation(Integer id) {
        Participate_in_tournament participation = participateInTournamentRepository
                .findParticipate_in_tournamentById(id);

        if (participation == null) {
            throw new ApiException("Participation not found");
        }

        participateInTournamentRepository.delete(participation);
    }

    public void distributePrizes(Integer tournamentId) {
        Tournament tournament = tournamentRepository.findTournamentById(tournamentId);
        if (tournament == null) {
            throw new ApiException("Tournament not found");
        }

        List<Participate_in_tournament> participants = participateInTournamentRepository
                .findTop8ByTournamentIdOrderByPlacementAsc(tournamentId);

        double prizePool = tournament.getPrize();

        for (Participate_in_tournament participation : participants) {
            int placement = participation.getPlacement();
            double prize = calculatePrize(prizePool, placement);
            participation.setPrize(prize);
            participateInTournamentRepository.save(participation);
        }
    }
    private double calculatePrize(double prizePool, int placement) {

        return prizePool / (placement * 2.0);
    }

    public void setPlacement(Integer participateId, int placement) {
        Participate_in_tournament participation = participateInTournamentRepository.findParticipate_in_tournamentById(participateId);

        if (participation == null) {
            throw new ApiException("Participation not found");
        }

        participation.setPlacement(placement);

        participateInTournamentRepository.save(participation);
    }


}
