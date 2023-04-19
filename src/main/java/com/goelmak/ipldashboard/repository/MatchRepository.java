package com.goelmak.ipldashboard.repository;

import com.goelmak.ipldashboard.model.Match;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MatchRepository extends CrudRepository<Match, Long> {

     List<Match> getByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageable);

    default List<Match> findLatestMatches(String team, int size) {
        return getByTeam1OrTeam2OrderByDateDesc(team, team, PageRequest.of(0, size));
    }

    @Query("SELECT m FROM Match m WHERE (m.team1 = :teamName OR m.team2 = :teamName) AND m.date BETWEEN :startDate AND :endDate ORDER BY m.date DESC")
    List<Match> getMatchesByTeamBetweenDates(@Param("teamName") String teamName, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
