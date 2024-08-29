package com.example.capstone3.Repository;

import com.example.capstone3.Model.Participate_in_tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface Participate_in_tournament_Repository extends JpaRepository<Participate_in_tournament,Integer> {

    Participate_in_tournament findParticipate_in_tournamentById(Integer id);
    List<Participate_in_tournament> findParticipate_in_tournamentByKnight_Id(Integer knightId);

    List<Participate_in_tournament> findParticipate_in_tournamentByHorse_Id(Integer horseId);

    @Query("SELECT p FROM Participate_in_tournament p WHERE p.tournament.id = ?1 ORDER BY p.placement ASC")
    List<Participate_in_tournament> findTop8ByTournamentIdOrderByPlacementAsc(Integer tournamentId);

}
